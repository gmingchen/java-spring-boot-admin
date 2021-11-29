package com.slipper.service.aspect;

import com.google.gson.Gson;
import com.slipper.common.utils.HttpContextUtils;
import com.slipper.service.annotation.OperationLog;
import com.slipper.service.modules.log.operation.entity.OperationLogEntity;
import com.slipper.service.modules.log.operation.service.OperationLogService;
import com.slipper.service.modules.system.admin.dto.AdminDto;
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
 * 系统日志，切面处理类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Aspect
@Component
public class OperationLogAspect {

	@Resource
	private OperationLogService operationLogService;

	@Pointcut("@annotation(com.slipper.service.annotation.OperationLog)")
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
		save(point, time);

		return result;
	}

	private void save(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		OperationLogEntity operationLogEntity = new OperationLogEntity();
		OperationLog OperationLog = method.getAnnotation(OperationLog.class);

		if(OperationLog != null){
			operationLogEntity.setOperation(OperationLog.value());
		}

		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		operationLogEntity.setClassName(className + "." + methodName + "()");

		Object[] args = joinPoint.getArgs();

		// 设置参数
		try {
			String params = new Gson().toJson(args);
			operationLogEntity.setParams(params);
		} catch (Exception e){
			// 无参异常捕获
		}
		// 设置请求URL
		operationLogEntity.setUrl(HttpContextUtils.getRequestUrl());
		// 设置请求方式
		operationLogEntity.setMethod(HttpContextUtils.getMethod());
		// 设置IP地址
		operationLogEntity.setIp(HttpContextUtils.getIp());
		// 设置Agent
		operationLogEntity.setAgent(HttpContextUtils.getUserAgent());
		// 设置用户信息
		AdminDto adminDto = (AdminDto) SecurityUtils.getSubject().getPrincipal();
		operationLogEntity.setCreator(adminDto.getId());
		operationLogEntity.setUsername(adminDto.getUsername());

		operationLogEntity.setTimes(time);
		operationLogEntity.setCreatedAt(new Date());

		operationLogService.save(operationLogEntity);
	}
}
