package com.rong.common.consts;

public class DictionaryKey {

    //以下是会员事件
    public enum Keys {
        /**
         * 上传图片宽度
         */
        UPLOAD_PICTURE_WIDTH("PIC_WIDTH"),
        /**
         * 上传图片高度
         */
        UPLOAD_HEIGHT("PIC_HEIGHT"),
        /**
         * 会员登录尝试次数
         */
        NUMBER_OF_MEMBER_LOGIN_ATTEMPTS("DAY_MEM_TRY_LOGIN_COUNT"),
        /**
         * 会员每天可发送短信次数	//会员每天可以发多少次短信验证码（每种类型）
         */
        MEMBERS_CAN_SEND_SMS_NUMBER_OF_TIMES_PER_DAY("DAY_SEND_VERIFYCODE_COUNT"),
        /**
         * 发送验证码时间间隔	会员每次发送验证码间隔时间(单位秒每种类型)
         */
        SEND_CAPTCHA_TIME_INTERVAL("VERIFYCODE_FREQUENT_TIME"),
        /**
         * 前台是否显示短信内容	前台是否显示手机验证码：测试需要(1是，0否)
         */
        WHETHER_THE_MESSAGE_IS_DISPLAYED_AT_THE_FRONT_DESK("IS_SHOW_SMS_CONTENT"),
        /**
         * 是否真实发送短息	是否发送验证码：测试不需要发送（1:发送，0:不发送）
         */
        WHETHER_IT_IS_TRUE_TO_SEND_SHORT_MESSAGES("IS_SEND_SMS_CONTENT"),
        /**
         * 每天验证码尝试匹配次数	一天输入验证码错误多少次数不可再输入且不可再发送
         */
        NUMBER_OF_CAPTCHA_ATTEMPTS_PER_DAY("DAY_VERIFYCODE_TRY_COUNT"),
        /**
         * 身份校验通过有效期	身份校验通过有效期(秒钟，退出后清除)	注意存的是eToken 因为每个会员都是唯一的
         */
        VALIDITY_OF_IDENTITY_VERIFICATION("IDENTITY_VERIFY_VALITY"),
        /**
         * 订单频繁时间间隔	订单频繁次数时间间隔（单位：秒）
         */
        FREQUENT_ORDER_INTERVALS("ORDER_FREQUENT"),
        /**
         * 会员Token		服务器存为key（值为memberId），客户端（请求api的源存为值）
         */
        MEMBER_OF_THE_TOKEN("MEMBER_TOKEN"),
        /**
         * 后台用户Token
         */
        BACKGROUND_USER_TOKEN("ADMIN_TOKEN"),
        /**
         * 每天尝试密保问题次数
         */
        TRY_SECURITY_QUESTIONS_SEVERAL_TIMES_A_DAY("SECURITY_TRY_COUNT"),
        /**
         * 公共缓存次数保存时长
         */
        COMMON_CACHE_TIMES_DURATION("COMMON_SERVICE_TRY_COUNT"),
        /**
         * 后台用户名最短长度
         */
        MINIMUM_LENGTH_OF_BACKGROUND_USERNAME("ADM_NAME_MIN_LEN"),
        /**
         * 后台用户名最长长度
         */
        THE_LONGEST_LENGTH_OF_A_BACKGROUND_USERNAME("ADM_NAME_MAX_LEN"),
        /**
         * 后台用户密码最短长度
         */
        MINIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD("ADM_PWD_MIN_LEN"),
        /**
         * 后台用户密码最长长度
         */
        MAXIMUM_LENGTH_OF_BACKGROUND_USER_PASSWORD("ADM_PWD_MAX_LEN"),
        /**
         * 默认提现手续费
         */
        DEFAULT_WITHDRAWAL_FEE("DEFAULT_WITHDRAW_FEE"),

        /**
         * 会员用户名最短长度
         */
        MINIMUM_LENGTH_OF_MEMBER_USERNAME("MEM_NAME_MIN_LEN"),
        /**
         * 会员用户名最长长度
         */
        MAXIMUM_LENGTH_OF_MEMBER_USERNAME("MEM_NAME_MAX_LEN"),
        /**
         * 会员用户密码最短长度
         */
        MINIMUM_LENGTH_OF_MEMBER_PASSWORD("MEM_PWD_MIN_LEN"),
        /**
         * 会员用户密码最长长度
         */
        MAXIMUM_LENGTH_OF_MEMBER_PASSWORD("MEM_PWD_MAX_LEN"),
        /**
         * 会员昵称最短长度
         */
        MEMBER_NICKNAME_MINIMUM_LENGTH("MEM_NICKNAME_MIN_LEN"),
        /**
         * 会员昵称最长长度
         */
        MEMBER_NICKNAME_LONGEST_LENGTH("MEM_NICKNAME_MAX_LEN"),

        /**
         * 后台最大登录尝试次数
         */
        MAXIMUM_NUMBER_OF_BACKGROUND_LOGIN_ATTEMPTS("DAY_ADMIN_TRY_LOGIN_COUNT"),
        /**
         * 还剩几天密码过期提醒
         */
        THERE_ARE_STILL_A_FEW_DAYS_LEFT_FOR_PASSWORD_EXPIRATION_REMINDER("ADM_PWD_EXP_REMID_DAY"),
        /**
         * 修改密码后还剩多少天过期
         */
        HOW_MANY_DAYS_ARE_LEFT_AFTER_CHANGING_THE_PASSWORD("ADM_PWD_EDIT_EXP_DAY"),
        /**
         * 浏览器登录几次错误后需要验证码
         */
        THE_BROWSER_NEEDS_THE_CAPTCHA_AFTER_SEVERAL_LOGIN_ERRORS("MEM_TRY_LORR_COUNT"),
        /**
         * 接口日志保存
         */
        INTERFACE_LOG_SAVE("API_LOG_SAVE"),
        /**
         * 文件上传地址
         */
        FILE_UPLOAD_ADDRESS("FILE_UP_DIR"),
        /**
         * 图片上传保存时长
         */
        HOW_LONG_PICTURES_ARE_UPLOADED_AND_SAVED("IMG_UP_SAVE"),
        /**
         * 图片上传大小限制
         */
        IMAGE_UPLOAD_SIZE_LIMIT("IMG_UP_MAX_SIZE"),
        /**
         * 附件上传大小限制
         */
        ATTACHMENT_UPLOAD_SIZE_LIMIT("FILE_UP_MAX_SIZE"),
        /**
         * 后台日志保存
         */
        BACKGROUND_LOG_SAVING("ADM_LOG_SAVE"),
        /**
         * 文件服务器域名
         */
        FILE_SERVER_DOMAIN_NAME("FILE_HOST"),
        /**
         * 内容默认来源
         */
        DEFAULT_SOURCE_OF_CONTENT("CONTENT_FROM_DEFAULT"),
        /**
         * APIToken保存时长
         */
        APITOKEN_IS_KEPT_FOR_A_LONG_TIME("API_TOKEN_TIME"),
        /**
         * 每个IP最多可以同时存在TOKEN数
         */
        EACH_IP_CAN_HAVE_A_MAXIMUM_NUMBER_OF_TOKENS_AT_THE_SAME_TIME("API_TOKEN_MAX_COUNT"),
        /**
         * 企业资产认证默认信息积分
         */
        DEFAULT_INFORMATION_CREDITS_FOR_ENTERPRISE_ASSET_AUTHENTICATION("CREDITFILE_COMPANY_COMPANY_ASSETS_INTEGRAL"),
        /**
         * 企业注册资本默认信用积分
         */
        ENTERPRISE_REGISTERED_CAPITAL_DEFAULT_CREDIT_SCORE("CREDITFILE_COMPANY_REGISTERED_CAPITAL_INTEGRAL"),
        /**
         * 企业营业执照默认信用积分
         */
        DEFAULT_CREDIT_SCORE_OF_ENTERPRISE_BUSINESS_LICENSE("CREDITFILE_COMPANY_BUSINESS_LICENSE_INTEGRAL"),
        /**
         * 股票基金默认信用积分
         */
        STOCK_FUNDS_DEFAULT_CREDIT_SCORES("CREDITFILE_STOCK_FUND_INTEGRAL"),
        /**
         * 房产信息默认信用积分
         */
        REAL_ESTATE_INFORMATION_DEFAULT_CREDIT_SCORE("CREDITFILE_ESTATE_INFO_INTEGRAL"),
        /**
         * 车辆认证信息默认信用积分
         */
        VEHICLE_CERTIFICATION_INFORMATION_DEFAULT_CREDIT_SCORE("CREDITFILE_CAR_INFO_INTEGRAL"),
        /**
         * 个人驾照认证默认信用积分
         */
        DEFAULT_CREDIT_SCORE_FOR_INDIVIDUAL_DRIVERS_LICENSE_CERTIFICATION("CREDITFILE_PERSONAL_DRIVER_LICENSE_INTEGRAL"),
        /**
         * 个人工作年限认证默认信用积分
         */
        THE_DEFAULT_CREDIT_SCORE_FOR_THE_CERTIFICATION_OF_PERSONAL_WORKING_YEARS("CREDITFILE_PERSONAL_JOBS_YEARS_INTEGRAL"),
        /**
         * 个人学历认证默认信用积分
         */
        DEFAULT_CREDIT_SCORE_FOR_PERSONAL_EDUCATION_CERTIFICATION("CREDITFILE_PERSONAL_EDUCATION_LEVEL_INTEGRAL"),
        /**
         * 一年按多少天计算天息
         */
        A_YEAR_IS_CALCULATED_BY_THE_NUMBER_OF_DAYS("DAY_RATE_OF_YEAR"),
        /**
         * 距离多少天还款就提醒用户该准备还款了
         */
        HOW_MANY_DAYS_AWAY_TO_REMIND_THE_USER_TO_PREPARE_FOR_REPAYMENT("LIMIT_DAY_TO_REMIND_MEM_REPAY_DEBIT"),
        /**
         * P2P商户平台会员ID
         */
        P2P_MERCHANT_PLATFORM_MEMBER_ID("P2P_MERCH_MEMBER_ID"),
        /**
         * SESSION保存时长
         */
        SESSION_DURATION("SESSION_CACHE_PERIOD"),
        /**
         * 发送邮件服务器
         */
        SENDING_MAIL_SERVER("SEND_EMAIL_SERVER"),
        /**
         * 发送邮件邮箱账户
         */
        SEND_EMAIL_TO_EMAIL_ACCOUNT("SEND_EMAIL_USER_NAME"),
        /**
         * 发送邮件邮箱密码
         */
        SEND_EMAIL_ADDRESS_PASSWORD("SEND_EMAIL_PASSWORD"),
        /**
         * 发送邮件邮箱地址
         */
        SEND_EMAIL_ADDRESS("SEND_EMAIL_INFO"),
        /**
         * 接收邮件邮箱地址
         */
        RECEIVE_EMAIL_ADDRESS("RECEIVE_EMAIL_INFO"),
        /**
         * 新闻内容访问前缀
         */
        NEWS_CONTENT_ACCESS_PREFIX("NEWS_S3_STORE_S3_URL_PREFIX"),
        /**
         * 热点资讯栏目
         */
        HOT_NEWS_COLUMN("TOP_NEWS_CATE_ID"),
        /**
         * 通联资讯发布日期限制最近天数
         */
        ACCESS_INFORMATION_RELEASE_DATE_LIMITS_THE_NUMBER_OF_RECENT_DAYS("TONG_VNEWS_CONTENT_V1_EFFECTIVE_TIME_LIMIT_DAY"),
        /**
         * 三十天热门路演分类ID
         */
        _30_DAYS_HOT_ROAD_SHOW_CATEGORY_ID("THIRTY_DAY_HOT_CATEID"),


        /**
         * 系统字典_NOSQL
         */
        SYSTEMDICTIONARY_NOSQL("DICTIONARY"),//独立
        /**
         * 其他
         */
        OTHER("COMMON:SERVICE:OTHER"),
        /**
         * 前端角色拥有的资源
         */
        RESOURCES_OWNED_BY_THE_FRONT_END_ROLE("ROLE:HAS:RESOURCES");
        public String value;

        Keys(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 后台用户
     *
     * @author lether
     */
    public enum AdminServiceKeyType {
        /**
         * 后台用户尝试登陆次数
         */
        NUMBER_OF_BACKGROUND_USER_LOGIN_ATTEMPTS(0),
        /**
         * 验证码问题尝试次数
         */
        CAPTCHA_PROBLEM_NUMBER_OF_ATTEMPTS(1);
        public final int value;

        AdminServiceKeyType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 会员公共缓存次数保存 类型
     *
     * @author lether
     */
    public enum MemServiceKeyType {
        /**
         * 会员登录尝试次数
         */
        NUMBER_OF_MEMBER_LOGIN_ATTEMPTS(0),
        /**
         * 会员验证码问题尝试次数
         */
        NUMBER_OF_MEMBER_VERIFICATION_CODE_ATTEMPTS(1),
        /**
         * 后台用户登录尝试次数
         */
        NUMBER_OF_BACKGROUND_USER_LOGIN_ATTEMPTS(2),
        /**
         * 后台验证码问题尝试次数
         */
        NUMBER_OF_ATTEMPTS_OF_BACKGROUND_VERIFICATION_CODE_PROBLEM(3),
        /**
         * 每个IP最多可以同时存在TOKEN数
         */
        EACH_IP_CAN_HAVE_A_MAXIMUM_NUMBER_OF_TOKENS_AT_THE_SAME_TIME(4);
        public final int value;

        MemServiceKeyType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum AuthResourceType {
        FOREGROUND,
        BACKGROUND;
    }

    public enum IdentityVerifyType {
        /**
         * 手机验证码校验
         */
        MOBILE_PHONE_VERIFICATION_CODE_VERIFICATION(0),
        /**
         * 密保问题校验
         */
        SECURITY_PROBLEM_CHECK(1),
        /**
         * 密保卡校验
         */
        SECURITY_CARD_CHECK(2),
        /**
         * 支付密码校验
         */
        PAYMENT_PASSWORD_CHECK(3);
        public final int value;

        IdentityVerifyType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ViyCacheSubstrTopic {
        /**
         * 更新字典
         */
        UPDATE_THE_DICTIONARY("vcs_update_dictionary"),
        /**
         * 图片收集器
         */
        PICTURE_COLLECTOR("vcs_collection_upload_pic"),
        ;
        public final String value;

        ViyCacheSubstrTopic(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
