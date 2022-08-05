package com.slipper.modules.log;

import com.slipper.common.utils.R;
import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.Log;
import com.slipper.service.model.PageModel;
import com.slipper.service.modules.log.operation.model.form.OperationLogPageDateForm;
import com.slipper.service.modules.log.operation.service.OperationLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/log/operation")
public class OperationLogController extends AbstractController {

    @Resource
    private OperationLogService operationService;

    /**
     * 分页
     *
     * @api {GET} /backstage/log/operation/self/page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup OperationLog
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
     *          operation: '', // 操作
     *          ip: '', // IP地址
     *          address: '', // 地址
     *          browser: '', // 浏览器
     *          operating_system: '', // 操作系统
     *          created_at: '', // 创建时间
     *     }]
     * }
     */
    @RequiresPermissions("self:operationLog:page")
    @GetMapping("/self/page")
    public R selfPage(PageModel pageModel) {
        return R.success(operationService.queryPage(pageModel, getAdministratorId()));
    }

    /**
     * 分页
     *
     * @api {GET} /backstage/log/operation/page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup OperationLog
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     operation: '', // 操作
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
     *          operation: '', // 操作
     *          url: '', // 请求URL
     *          method: '', // 请求方式
     *          request_data: '', // 请求参数
     *          response_data: '', // 响应参数
     *          ip: '', // IP地址
     *          address: '', // 地址
     *          browser: '', // 浏览器
     *          operating_system: '', // 操作系统
     *          agent: '', // 用户代理
     *          times: '', // 执行时长
     *          method_name: '', // 完整方法名
     *          creator: '', // 创建者
     *          username: '', // 用户名
     *          nickname: '', // 昵称
     *          avatar: '', // 头像
     *          created_at: '', // 创建时间
     *     }]
     * }
     */
    @RequiresPermissions("operationLog:page")
    @GetMapping("/page")
    public R page(OperationLogPageDateForm pageDateForm) {
        return R.success(operationService.queryPage(pageDateForm, getEnterpriseId()));
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/log/operation/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup OperationLog
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
    @Log("删除操作日志")
    @RequiresPermissions("operationLog:delete")
    @PostMapping("/delete")
    public R delete() {
        operationService.delete(getEnterpriseId());
        return R.success();
    }

}
