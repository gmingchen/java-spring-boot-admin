package com.slipper.service.aspect;

import com.google.gson.Gson;
import com.slipper.common.utils.HttpContextUtils;
import com.slipper.service.annotation.Log;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.log.operation.entity.OperationLogEntity;
import com.slipper.service.modules.log.operation.service.OperationLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 操作日志，切面处理类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Aspect
@Component
public class LogAspect {

	@Resource
	private OperationLogService operationService;

	@Pointcut("@annotation(com.slipper.service.annotation.Log)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		save(point, time, result);
		return result;
	}

	private void save(ProceedingJoinPoint joinPoint, long time, Object result) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		OperationLogEntity operationEntity = new OperationLogEntity();
		Log log = method.getAnnotation(Log.class);

		if(log != null){
			operationEntity.setOperation(log.value());
		}

		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		operationEntity.setMethodName(className + "." + methodName + "()");

		Object[] args = joinPoint.getArgs();
		// 设置请求参数
		if (args.length == 1) {
			operationEntity.setRequestData(new Gson().toJson(args[0]));
		} else if (args.length > 1) {
			operationEntity.setRequestData(new Gson().toJson(args));
		}
		// 设置响应参数
		operationEntity.setResponseData(new Gson().toJson(result));
		// 设置请求URL
		operationEntity.setUrl(HttpContextUtils.getRequestUrl());
		// 设置请求方式
		operationEntity.setMethod(HttpContextUtils.getMethod());
		// 设置IP地址
		operationEntity.setIp(HttpContextUtils.getIp());
		// 设置浏览器
		operationEntity.setBrowser(HttpContextUtils.getBrowser());
		// 设置操作系统
		operationEntity.setOperatingSystem(HttpContextUtils.getOperatingSystem());
		// 设置Agent
		operationEntity.setAgent(HttpContextUtils.getUserAgent());
		// 设置执行时长
		operationEntity.setTimes(time);
		// 设置操作者
		AdministratorRoleDTO administratorRoleDto = (AdministratorRoleDTO) SecurityUtils.getSubject().getPrincipal();
		operationEntity.setCreator(administratorRoleDto.getId());
		// 设置操作时间
		operationEntity.setCreatedAt(new Date());

		operationService.save(operationEntity);
	}
}
