package com.slipper.modules.log.operation;

import com.slipper.common.utils.R;
import com.slipper.service.modules.log.operation.service.OperationLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/log/operation")
public class OperationLogController {

    @Resource
    private OperationLogService operationLogService;

    /**
     * 分页列表
     *
     * @api {GET} /backstage/log/operation/page page
     * @apiDescription 操作日志分页列表
     * @apiVersion 1.0.0
     * @apiGroup OperationLog
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     current: 1, // 当前页
     *     size: 10, // 页面大小
     *     operation: "", // 操作类型
     *     ip: "", // ip
     *     start: "", // 开始日期
     *     end: "" // 结束日期
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     *     data: {
     *         current: 1, // 当前页
     *         size: 1, // 页面大小
     *         total: 1, // 总条数
     *         pages: 1, // 总页数
     *         list: [{
     *         	   id: '', // ID
     *             username: '', // 管理员账户
     *             operation: '', // 操作
     *             method: '', // 方法
     *             params: '', // 参数
     *             times: '', // 执行时长
     *             ip: '', // ip地址
     *             agent: '', // 用户代理
     *             creator: '', // 操作者
     *             created_at: ‘’ // 创建时间
     *         }]
     *     }
     * }
     */
    @GetMapping("/page")
    @RequiresPermissions("backstage:log:operation:page")
    public R get(@RequestParam Map<String, Object> params){
        return R.success(operationLogService.queryPage(params));
    }

    /**
     * 清理操作日志
     *
     * @api {POST} /backstage/log/operation/truncate truncate
     * @apiDescription 清理操作日志
     * @apiVersion 1.0.0
     * @apiGroup OperationLog
     * @apiName truncate
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     * }
     */
    @PostMapping("/truncate")
    @RequiresPermissions("backstage:log:operation:truncate")
    public R truncate(){
        operationLogService.truncate();
        return R.success();
    }

}
