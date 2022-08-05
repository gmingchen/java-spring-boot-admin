package com.slipper.shiro.realm;

import com.alibaba.druid.support.json.JSONUtils;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.R;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.token.entity.TokenEntity;
import com.slipper.shiro.service.ShiroService;
import com.slipper.shiro.token.AuthToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Shiro 授权领域
 * TODO:  注意 敲黑板 （BeanPostProcessor） 不可以直接注入服务层到 shiro 不然会导致 事务注解失效
 * TODO:  所以这边写个 shiro 服务层 依旧注意 shiro 服务层里面也是不可以注入服务层
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Resource
    ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

    /**
     * 身份验证（凭证验证-token） TODO: 验证token是否有效
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 用户请求携带的token凭证
        String token = (String)authenticationToken.getPrincipal();

        TokenEntity tokenEntity = shiroService.queryTokenByToken(token);
        if (tokenEntity == null || tokenEntity.getExpiredAt().getTime() < System.currentTimeMillis()) {
            // 凭证不正确异常
            String message = JSONUtils.toJSONString(R.error(Constant.TOKEN_EXPIRE_CODE, Constant.TOKEN_EXPIRE_MESSAGE));
            throw new IncorrectCredentialsException(message);
        }

        AdministratorRoleDTO administratorRoleDto = shiroService.queryAdministratorById(tokenEntity.getAdministratorId());
        if (administratorRoleDto == null) {
            // 账户锁定异常
            String message = JSONUtils.toJSONString(R.error(Constant.WARNING_CODE, "账户不存在，请联系管理员！"));
            if (administratorRoleDto.getStatus() == 0) {
                message = JSONUtils.toJSONString(R.error(Constant.WARNING_CODE, "账户已被冻结，请联系管理员！"));
            }
            throw new LockedAccountException(message);
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(administratorRoleDto, token, getName());

        return simpleAuthenticationInfo;
    }

    /**
     * 授权验证（权限验证-permission）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户信息
        AdministratorRoleDTO administratorRoleDto = (AdministratorRoleDTO)principalCollection.getPrimaryPrincipal();

        // 获取权限
        Set<String> permissions = shiroService.queryPermissionByAdministrator(administratorRoleDto);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

}
