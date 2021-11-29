package com.slipper.common.utils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 自定义响应结果工具
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class R extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public R() {
        put("status", Constant.SUCCESS_STATUS);
        put("code", Constant.SUCCESS_CODE);
        put("message", Constant.SUCCESS_MESSAGE);
    }

    public static R success() {
        return new R();
    }
    public static R success(Object data) {
        R r = new R();
        r.put("data", data);
        return r;
    }
    public static R success(HashMap<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R error() {
        R r = new R();
        r.put("status", Constant.ERROR_STATUS);
        r.put("code", Constant.ERROR_CODE);
        r.put("message", Constant.ERROR_MESSAGE);
        return r;
    }

    public static R error(Integer code) {
        R r = new R();
        r.put("status", Constant.ERROR_STATUS);
        r.put("code", code);
        r.put("message", Constant.ERROR_MESSAGE);
        return r;
    }

    public static R error(String message) {
        R r = new R();
        r.put("status", Constant.ERROR_STATUS);
        r.put("code", Constant.ERROR_CODE);
        r.put("message", message);
        return r;
    }

    public static R error(Integer code, String message) {
        R r = new R();
        r.put("status", Constant.ERROR_STATUS);
        r.put("code", code);
        r.put("message", message);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
