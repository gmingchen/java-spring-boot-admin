package com.slipper.common.utils;

/**
 * 常量工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class Constant {
    /**
     * 后台登录 后台验证码 存储位置：1-数据库 2-Redis
     */
    public static final Integer STORAGE_MODE = 1;

    /**
     * token 键值
     */
    public static final String TOKEN_KEY = "token";

    /**
     * 状态码 异常信息
     */
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_STATUS = "success";
    public static final String SUCCESS_MESSAGE = "成功！";
    public static final int ERROR_CODE = 500;
    public static final String ERROR_STATUS = "error";
    public static final String ERROR_MESSAGE = "未知异常！";
    public static final int VERIFICATION_ERROR_CODE = 400;
    public static final String VERIFICATION_ERROR_MESSAGE = "校验异常！";
    public static final int NOT_ALLOWED_CODE = 401;
    public static final String NOT_ALLOWED_MESSAGE = "没有权限！";
    public static final int NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MESSAGE = "路径不存在！";
    public static final int METHOD_ERROR_CODE = 405;
    public static final String METHOD_ERROR_MESSAGE = "不支持该请求方法！";
    public static final int TOKEN_EXPIRE_CODE = 4001;
    public static final String TOKEN_EXPIRE_MESSAGE = "凭证已过期，请重新登录！";
    public static final int WARNING_CODE = 600;

    /**
     * 分页
     */
    public enum Page {
        /**
         * 当前页 key
         */
        CURRENT("current"),
        /**
         * 分页大小 key
         */
        SIZE("size");

        private final String value;

        Page(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum TimedTaskStatus {
        /**
         * 正常
         */
        NORMAL(1),
        /**
         * 暂停
         */
        PAUSE(0);

        private final int value;

        TimedTaskStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2),
        /**
         * iframe
         */
        IFRAME(3),
        /**
         * 外链
         */
        URL(4);

        private final int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
