package com.slipper.shiro.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.R;
import com.slipper.common.utils.RequestBody;
import com.slipper.shiro.token.OAuth2Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuth2Filter extends AuthenticatingFilter {

    /**
     * 设置token TODO: 设置用户携带的token
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken(servletRequest);
        if (!StringUtils.isBlank(token)) {
            return new OAuth2Token(token);
        }
        return null;
    }

    /**
     * 判断是否允许访问 TODO: 可以拒绝所有请求 直接 return false
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 如果为 options 请求则允许访问
        String requestMethod = ((HttpServletRequest)request).getMethod();
        if (RequestMethod.OPTIONS.name().equals(requestMethod)) {
            return true;
        }
        return false;
    }

    /**
     * 不允许访问时的处理 TODO: 判断请求是否携带token
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken(servletRequest);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpServletResponse = getProcessedResponse(servletResponse);

            String json = JSONUtils.toJSONString(R.error(Constant.NOT_ALLOWED_CODE, Constant.NOT_ALLOWED));
            httpServletResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }

    /**
     * 登录成功时候的处理
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }

    /**
     * 登录失败时候的处理 TODO: token验证失败之后的处理
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = getProcessedResponse(response);

        String message = e.getMessage();
        if (StringUtils.isBlank(message)) {
            message = JSONUtils.toJSONString(R.error());
        }
        try {
            httpServletResponse.getWriter().print(message);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return false;
    }

    /**
     * 获取用户请求中携带的token
     * @param servletRequest
     * @return
     */
    private String getRequestToken(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        String token = httpServletRequest.getHeader(Constant.TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            token =httpServletRequest.getParameter(Constant.TOKEN_KEY);
        }
        return token;
    }

    /**
     * 封装通用响应体
     * @param servletResponse
     * @return
     */
    private HttpServletResponse getProcessedResponse (ServletResponse servletResponse) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Content-type", "application/json;charset=utf-8");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", RequestBody.getHeader("Origin"));
        return httpServletResponse;
    }

}
