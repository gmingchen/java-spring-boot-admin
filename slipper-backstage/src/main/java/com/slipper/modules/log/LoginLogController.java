package com.slipper.modules.log;

import com.slipper.common.utils.R;
import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.Log;
import com.slipper.service.model.PageModel;
import com.slipper.service.modules.log.login.model.form.LoginLogPageDateForm;
import com.slipper.service.modules.log.login.service.LoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/log/login")
public class LoginLogController extends AbstractController {

    @Resource
    private LoginLogService loginLogService;

    /**
     * 分页
     *
     * @api {GET} /backstage/log/login/self/page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup LoginLog
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     current: '', // 当前页
     *     size: '' // 页面大小
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: [{
     *          id: '', // ID
     *          message: '', // 登录信息
     *          ip: '', // IP地址
     *          address: '', // 地址
     *          browser: '', // 浏览器
     *          operating_system: '', // 操作系统
     *          created_at: '', // 创建时间
     *     }]
     * }
     */
    @RequiresPermissions("self:loginLog:page")
    @GetMapping("/self/page")
    public R selfPage(PageModel pageModel) {
        return R.success(loginLogService.queryPage(pageModel, getAdministratorId()));
    }

    /**
     * 分页
     *
     * @api {GET} /backstage/log/login/page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup LoginLog
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     name: '', // 昵称 / 账户
     *     start: '', // 开始日期
     *     end: '' // 结束日期
     *     current: '', // 当前页
     *     size: '' // 页面大小
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: [{
     *          id: '', // ID
     *          message: '', // 登录信息
     *          response_data: '', // 响应参数
     *          ip: '', // IP地址
     *          address: '', // 地址
     *          browser: '', // 浏览器
     *          operating_system: '', // 操作系统
     *          agent: '', // 用户代理
     *          creator: '', // 创建者
     *          username: '', // 用户名
     *          nickname: '', // 昵称
     *          avatar: '', // 头像
     *          created_at: '', // 创建时间
     *     }]
     * }
     */
    @RequiresPermissions("loginLog:page")
    @GetMapping("/page")
    public R page(LoginLogPageDateForm pageDateForm) {
        return R.success(loginLogService.queryPage(pageDateForm, getEnterpriseId()));
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/log/login/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup LoginLog
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("删除登录日志")
    @RequiresPermissions("loginLog:delete")
    @PostMapping("/delete")
    public R delete() {
        loginLogService.delete(getEnterpriseId());
        return R.success();
    }

}
