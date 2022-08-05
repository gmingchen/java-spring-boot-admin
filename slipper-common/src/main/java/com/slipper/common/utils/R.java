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

    private static final String STATUS = "status";
    private static final String CODE = "code";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    public R() {
        put(STATUS, Constant.SUCCESS_STATUS);
        put(CODE, Constant.SUCCESS_CODE);
        put(MESSAGE, Constant.SUCCESS_MESSAGE);
    }

    public static R success() {
        return new R();
    }
    public static R success(Object data) {
        R r = new R();
        r.put(DATA, data);
        return r;
    }
    public static R success(HashMap<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R error() {
        R r = new R();
        r.put(STATUS, Constant.ERROR_STATUS);
        r.put(CODE, Constant.ERROR_CODE);
        r.put(MESSAGE, Constant.ERROR_MESSAGE);
        return r;
    }

    public static R error(Integer code) {
        R r = new R();
        r.put(STATUS, Constant.ERROR_STATUS);
        r.put(CODE, code);
        r.put(MESSAGE, Constant.ERROR_MESSAGE);
        return r;
    }

    public static R error(String message) {
        R r = new R();
        r.put(STATUS, Constant.ERROR_STATUS);
        r.put(CODE, Constant.ERROR_CODE);
        r.put(MESSAGE, message);
        return r;
    }

    public static R error(Integer code, String message) {
        R r = new R();
        r.put(STATUS, Constant.ERROR_STATUS);
        r.put(CODE, code);
        r.put(MESSAGE, message);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
