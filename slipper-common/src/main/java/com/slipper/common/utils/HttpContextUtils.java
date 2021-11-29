package com.slipper.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http 内容
 *
 * @author slipper
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getDomain(){
		HttpServletRequest request = getHttpServletRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

	public static String getIp() {
		HttpServletRequest request = getHttpServletRequest();
		String ip = request.getHeader("X-Forwarded-For");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		return request.getRemoteAddr();
	}

	public static String getOrigin(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader("Origin");
	}

	public static String getUserAgent(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader("User-Agent");
	}

	public static String getMethod(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getMethod();
	}

	public static String getRequestUrl(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getRequestURI();
	}

	public static Map<String, Object> getParams(){
		HttpServletRequest request = getHttpServletRequest();
		Map<String, Object> map = new HashMap<>();
		request.getParameterMap().forEach((key, value) -> map.put(key, value[0]));

		// 获取HttpServletRequest 中Request PayLoad 格式入参数据
		/*StringBuilder sb = new StringBuilder();
		JSONObject builderJson = null;
		try(BufferedReader reader = request.getReader();) {
			char[]buff = new char[1024];
			int len;
			while((len = reader.read(buff)) != -1) {
				sb.append(buff,0, len);
			}
			String str = sb.toString();
			builderJson = JSONObject.parseObject(str);

		}catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(builderJson.toString());*/

		return map;
	}
}
