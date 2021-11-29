package com.slipper.modules.login;

import com.slipper.common.utils.R;
import com.slipper.modules.AbstractController;
import com.slipper.service.modules.login.service.LoginService;
import com.slipper.service.modules.login.vo.LoginVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage")
public class LoginController extends AbstractController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @api {POST} /backstage/login
     * @apiDescription 登录
     * @apiVersion 1.0.0
     * @apiGroup Login
     * @apiName login
     * @apiParamExample 请求参数示例
     * {
     *     username: 1, // 帐号
     *     password: 1, // 密码
     *     uuid: 1, // UUID
     *     code: 1 // 验证码
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *         admin_id: 1, // 用户ID
     *         token: 1, // token
     *         expired_at: 1, // 过期时间
     *         updated_at: 1 // 更新时间
     *     }
     * }
     */
    @PostMapping("/login")
    public R login(@RequestBody @Validated LoginVo loginVo) {
        return R.success(loginService.login(loginVo));
    }

    /**
     * 登出
     *
     * @api {POST} /backstage/logout
     * @apiDescription 登录
     * @apiVersion 1.0.0
     * @apiGroup Login
     * @apiName logout
     * @apiParamExample 请求参数示例
     * {
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/logout")
    public R logout() {
        loginService.logout(getAdminId());
        return R.success();
    }

}
