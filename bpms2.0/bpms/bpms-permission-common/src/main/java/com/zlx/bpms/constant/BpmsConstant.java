package com.zlx.bpms.constant;

/**
 * @Package: com.zlx.bpms.constant
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:常量类
 */
public class BpmsConstant {

    /**
     * 返回报文头 json格式，编码 utf8
     */
    public static final String JSON_UTF8 = "application/json;charset=utf-8";

    /**
     * 直线 用于日期拼接
     */
    public static final String STRAIGHTLINE = "-";

    /**
     * 月初
     */
    public static final String FIRSTMONTHDAY = "01";

    /**
     * jwt 密钥
     */
    public static final String PASSWORD = "201314";


    /**
     * 国家名
     */
    public static final String COUNTRYNAME = "CHN";


    /**
     * 设备名
     */
    public static final String DEVICENAME = "MOB";


    /**
     * 通知消息
     */
    public static class NotificationsMessage {

        /**
         * 验证码不能为空
         */
        public static final String VALIDATE_CODE_NOT_NULL = "验证码不能为空！";

        /**
         * 验证码不存在
         */
        public static final String VALIDATE_CODE_DOES_NOT_EXITS = "验证码不存在，请刷新重试！";

        /**
         * 验证码已过期
         */
        public static final String VALIDATE_CODE_IS_EXPIRED = "验证码已过期，请刷新重试！";

        /**
         * 验证码不匹配
         */
        public static final String VALIDATE_CODE_DOES_NOT_MATCH = "验证码不正确，请重新输入！";


        /**
         * 用户不存在
         */
        public static final String USER_DOES_NOT_EXITS = "用户不存在！";

        /**
         * 用户名或密码错误
         */
        public static final String USER_NAME_OR_PASSWORD_ERROR = "用户名或密码错误！";

        /**
         * 用户已被锁定
         */
        public static final String USER_IS_LOCKED = "用户已被锁定！";

        /**
         * 用户不可用
         */
        public static final String USER_UNAVAILABLE = "用户不可用！";

        /**
         * 用户账户已过期
         */
        public static final String USER_ACCOUNT_EXPIRED = "用户账户已过期！";

        /**
         * 用户密码已过期
         */
        public static final String USER_PASSWORD_HAS_EXPIRED = "用户密码已过期！";


    }

    /**
     * 魔鬼数字
     */
    public static class TheNumberDevil {

        public static final int MINUS_ONE = -1;

        public static final int ZERO = 0;

        public static final int ONE = 1;

        public static final int TWO = 2;

        public static final int THREE = 3;

        public static final int FOUR = 4;
    }

    public static class BodyConstant {
        /**
         * 成功
         */
        public static final Integer SUCCESS = 0;
        /**
         * 登录失败
         */
        public static final Integer WARN = 400;
        /**
         * 异常 失败
         */
        public static final Integer FAIL = 500;

        /**
         * 无权访问
         */
        public static final Integer NOACCESS = 300;
        /**
         * 未登录
         */
        public static final Integer NOTLOGGEDIN = 211;
        /**
         * 未认证
         */
        public static final Integer UNAUTHORIZED = 401;
        /**
         * 超频
         */
        public static final Integer OVERCLOCKING = 666;
    }

    public static class RequestConstant {

        /**
         * 无权访问链接
         */
        public static final String ACCESS_DENY_URL = "/noAccess";
        /**
         * 登录链接
         */
        public static final String LOGIN_URL = "/login";

        /**
         * 主页链接
         */
        public static final String HOME_URL = "/";

        /**
         * 首页链接
         */
        public static final String INDEX_URL = "/index";

        /**
         * 认证确认链接
         */
        public static final String OAUTH_CONFIRM_URL = "/oauth/confirm_access";

        /**
         * 认证否决链接
         */
        public static final String OAUTH_ERROR_URL = "/oauth/error";

        /**
         * 图形验证码存入session的key值
         */
        public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    }
}
