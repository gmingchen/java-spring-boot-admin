package com.slipper.modules.home;

import com.slipper.common.utils.R;
import com.slipper.modules.AbstractController;
import com.slipper.service.modules.log.login.service.LoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 首页
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/home")
public class HomeController extends AbstractController {

    @Resource
    private LoginLogService loginLogService;

    /**
     * 获取最近30天登录量
     *
     * @api {GET} /backstage/home/visits visits
     * @apiDescription 获取当前管理员信息
     * @apiVersion 1.0.0
     * @apiGroup Home
     * @apiName visits
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: [{
     *          date: '', // 日期
     *          count: '' // 数量
     *     }]
     * }
     */
    @RequiresPermissions("home:visits")
    @GetMapping("/visits")
    public R queryVisits() {
        return R.success(loginLogService.queryVisits(30));
    }

}
