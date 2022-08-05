package com.slipper.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Http 请求工具
 *
 * @author slipper
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class HttpUtils {
    /**
     * 请求成功状态码
     */
    private static final Integer SUCCESS_STATUS = 200;
    /**
     * get 请求方法
     */
    private static final String GET = "GET";
    /**
     * post 请求方法
     */
    private static final String POST = "POST";
    /**
     * 连接超时时长
     */
    private static final Integer CONNECT_TIMEOUT = 15000;
    /**
     * 读取超时时长
     */
    private static final Integer READ_TIMEOUT = 60000;
    /**
     * form 内容类型
     */
    private static final String FORM = "application/x-www-form-urlencoded";
    /**
     * json 内容类型
     */
    private static final String JSON = "application/json";

    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @return
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> requestProperty) {
        String result = null;
        String time = System.currentTimeMillis() + "";
        params.put("_", time);
        url += "?" + mapToUrlParam(params);
        HttpURLConnection connection = null;
        try {
            connection = connect(url, GET, CONNECT_TIMEOUT, READ_TIMEOUT, requestProperty, null);
            result = then(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @return
     */
    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, new HashMap<>());
    }
    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @return
     */
    public static String doGet(String url, Object params, Map<String, String> requestProperty) {
        String json = JSONObject.toJSONString(params);
        Map map = JSONObject.parseObject(json);
        return doGet(url, map, requestProperty);
    }
    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @return
     */
    public static String doGet(String url, Object params) {
        String json = JSONObject.toJSONString(params);
        Map map = JSONObject.parseObject(json);
        return doGet(url, map);
    }
    /**
     * get 请求
     * @param url 接口url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<>(), new HashMap<>());
    }
    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doGet(String url, Map<String, String> params, Map<String, String> requestProperty, Class<T> target) {
        T result = null;
        String response = doGet(url, params, requestProperty);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }
    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doGet(String url, Map<String, String> params, Class<T> target) {
        T result = null;
        String response = doGet(url, params);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }
    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doGet(String url, Object params, Map<String, String> requestProperty, Class<T> target) {
        T result = null;
        String response = doGet(url, params, requestProperty);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }
    /**
     * get 请求
     * @param url 接口url
     * @param params 请求参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doGet(String url, Object params, Class<T> target) {
        T result = null;
        String response = doGet(url, params);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }
    /**
     * get 请求
     * @param url 接口url
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doGet(String url, Class<T> target) {
        T result = null;
        String response = doGet(url);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }

    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> requestProperty) {
        String result = null;
        String paramsStr = JSONObject.toJSONString(params);
        HttpURLConnection connection = null;
        try {
            connection = connect(url, POST, CONNECT_TIMEOUT, READ_TIMEOUT, requestProperty, paramsStr);
            result = then(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, new HashMap<>());
    }
    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @return
     */
    public static String doPost(String url, Object params, Map<String, String> requestProperty) {
        String json = JSONObject.toJSONString(params);
        Map map = JSONObject.parseObject(json);
        return doPost(url, map, requestProperty);
    }
    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, Object params) {
        String json = JSONObject.toJSONString(params);
        Map map = JSONObject.parseObject(json);
        return doPost(url, map);
    }
    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doPost(String url, Map<String, String> params, Map<String, String> requestProperty, Class<T> target) {
        T result = null;
        String response = doPost(url, params, requestProperty);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }
    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doPost(String url, Map<String, String> params, Class<T> target) {
        T result = null;
        String response = doPost(url, params);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }
    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @param requestProperty 请求头参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doPost(String url, Object params, Map<String, String> requestProperty, Class<T> target) {
        T result = null;
        String response = doPost(url, params, requestProperty);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }
    /**
     * post 请求
     * @param url 接口url
     * @param params 请求参数
     * @param target 响应结果类型
     * @param <T>
     * @return
     */
    public static<T> T doPost(String url, Object params, Class<T> target) {
        T result = null;
        String response = doPost(url, params);
        if (StringUtils.isNotBlank(response)) {
            result = JSONObject.parseObject(response, target);
        }
        return result;
    }

    /**
     * 请求 api 并返回 connection
     * @param url 接口url
     * @param method 请求方式
     * @param connectTimeout 连接超时时间
     * @param readTimeout 读取超时时间
     * @param requestProperty 请求头参数
     * @param params 请求参数
     * @return
     * @throws IOException
     */
    private static HttpURLConnection connect(String url, String method, Integer connectTimeout, Integer readTimeout,
                                             Map<String, String> requestProperty, String params) throws IOException {
        // 通过远程url连接对象打开连接
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        // 设置请求方式
        connection.setRequestMethod(method);
        // 设置连接服务器超时时间 单位毫秒
        connection.setConnectTimeout(connectTimeout);
        // 设置读取远程返回数据超时时间 单位毫秒
        connection.setReadTimeout(readTimeout);
        // 设置请求头
        if (requestProperty != null) {
            if (method.equals(POST)) {
                requestProperty.put("Content-Type", JSON);
            }
            for (String key: requestProperty.keySet()) {
                connection.setRequestProperty(key, requestProperty.get(key));
            }
        }
        if (method.equals(POST)) {
            // 设置是否向服务器发送数据 默认值为：true
            connection.setDoInput(true);
            // 设置是否读取服务器发送的数据 默认值为：false
            connection.setDoOutput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
        }
        // 设置请求参数
        if(StringUtils.isNotBlank(params)) {
            connection.getOutputStream().write(params.getBytes());
        }
        // 发送请求
        connection.connect();

        return connection;
    }

    /**
     * 处理请求结果
     * @param connection
     * @return
     */
    private static String then(HttpURLConnection connection) throws IOException {
        String result = null;
        if (connection.getResponseCode() == SUCCESS_STATUS) {
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            try {
                inputStreamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String temp;
                // 循环遍历一行一行读取数据
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp);
                    stringBuilder.append("\r\n");
                }
                result = stringBuilder.toString();
            } finally {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                connection.disconnect();
            }

        }
        return result;
    }

    /**
     * Map 转 Url 参数
     * @param params
     * @return
     */
    private static String mapToUrlParam (Map<String, String> params) {
        String result = "";
        if (params != null) {
            for (String key: params.keySet()) {
                if (StringUtils.isNotBlank(result)) {
                    result += "&";
                }
                result += key + "=" + params.get(key);
            }
        }
        return result;
    }

    private HttpUtils() {}
}