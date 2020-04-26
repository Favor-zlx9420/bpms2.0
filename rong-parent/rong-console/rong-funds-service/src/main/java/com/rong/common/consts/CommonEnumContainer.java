package com.rong.common.consts;

/**
 * creator : whh-lether
 * date    : 2018/11/26 9:40
 * desc    :
 **/
public class CommonEnumContainer {

    /**
     * 删除位
     *
     * @author lether
     */
    public enum Deltag {
        /**
         * 正常删除
         */
        NORMAL(Boolean.FALSE, "正常"),
        /**
         * 已删除
         */
        DELETED(Boolean.TRUE, "已删除"),
        ;
        private final boolean value;
        private final String desc;

        Deltag(boolean value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public boolean getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 默认相关
     *
     * @author lether
     */
    public enum Default {
        /**
         * 否
         */
        DENY(false, "否"),
        /**
         * 是
         */
        RIGHT(true, "是"),
        ;
        private final boolean value;
        private final String desc;

        Default(boolean value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public boolean getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum State {
        /**
         * 拥有资源无效
         */
        INVALID(0, "无效"),
        /**
         * 拥有资源正常
         */
        NORMAL(1, "正常"),
        /**
         * 拥有其他资源
         */
        Other(99, "其他");
        private final int value;
        private final String desc;

        State(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 是否
     *
     * @author lether
     */
    public enum YesOrNo {
        /**
         * 否
         */
        DENY(false, "否"),
        /**
         * 是
         */
        RIGHT(true, "是"),
        ;
        private final boolean value;
        private final String desc;

        YesOrNo(boolean value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public boolean getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum JdbcType {
        /**
         * mysql
         */
        MYSQL(0, "mysql"),
        /**
         * oracle
         */
        ORACLE(1, "oracle"),
        /**
         * sqlServer
         */
        SQLSERVER(2, "sqlserver"),
        /**
         * 其他
         */
        OTHER(99, "other");
        private final int value;
        private final String desc;

        JdbcType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 执行结果返回码
     *
     * @author lether
     */
    public enum ResultStatus {
        /**
         * 操作异常
         */
        ABNORMAL_OPERATION("400", "操作异常，请联系管理员"),
        /**
         * 未登陆
         */
        NOT_LOG_IN("401", "您未登录或者登录状态已过期"),
        /**
         * 用户名或密码错误
         */
        WRONG_USERNAME_OR_PASSWORD("402", "用户名或密码错误"),
        /**
         * 无权限
         */
        WITHOUT_PERMISSION("403", "您无此操作权限"),
        /**
         * 查询不存在   如数据库中不存在等
         */
        QUERY_DOES_NOT_EXIST("404", "查询不存在"),
        /**
         * 请求异常
         */
        PAGE_REQUEST_EXCEPTION("405", "页面请求异常"),
        /**
         * 接口不存在
         */
        INTERFACE_DOES_NOT_EXIST("406", "请求接口不存在"),
        /**
         * 数据重复    如数据库中不存在等
         */
        REPEATING_DATA("407", "已存在重复数据"),
        /**
         * 参数不全
         */
        PARAMETER_IS_NOT_COMPLETE("408", "参数不全"),
        /**
         * 用户不存在
         */
        THE_USER_DOES_NOT_EXIST("409", "用户不存在"),
        /**
         * 用户已禁用
         */
        USER_DISABLED("501", "用户已禁用"),
        /**
         * 正常
         */
        NORMAL("200", "success"),
        /**
         * 令牌无效
         */
        TOKEN_IS_INVALID("300", "Token无效"),
        /**
         * 接口令牌无效
         */
        INTERFACE_TOKEN_INVALID("301", "Token无效"),
        /**
         * 参数不符合要求
         */
        THE_PARAMETERS_DO_NOT_MEET_THE_REQUIREMENTS("302", "参数不符合要求"),
        /**
         * 请求次数过多
         */
        TOO_MANY_REQUESTS("303", "请求次数过多"),
        /**
         * 不再使用该记录
         */
        THIS_RECORD_IS_NO_LONGER_USED("304", "不再使用该录"),
        /**
         * 数据状态不正确
         */
        THE_DATA_STATE_IS_INCORRECT("305", "数据状态不正确"),
        /**
         * 余额不足
         */
        THE_BALANCE_IS_INSUFFICIENT("306", "余额不足了哟"),

        /**
         * 用户没有开户
         */
        USER_NOT_OPEN_ACCOUNT("307", "用户没有开户"),

        /**
         * 用户信息入库失败
         */
        USER_INFO_WRITE_FAILED("308", "用户信息入库失败"),

        /**
         * 其他
         */
        OTHER("509", "其他");
        private final String value;
        private final String desc;

        ResultStatus(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 平台
     *
     * @author lether 2016年03月19日12:05:42
     */
    public enum Platform {
        /**
         * 连连支付
         */
        ALL_PAYMENT(0, "连连支付"),
        /**
         * 平安托管
         */
        PING_AN_TRUST(1, "平安托管"),
        /**
         * 其他
         */
        OTHER(9, "其他");
        private final int value;
        private final String desc;

        Platform(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 银行卡类型
     *
     * @author lether 2016年03月19日12:05:42
     */
    public enum BankCardType {
        /**
         * 借记卡
         */
        DEBIT_CARD(0, "借记卡"),
        /**
         * 信用卡
         */
        CREDIT_CARD(1, "信用卡"),
        /**
         * 其他
         */
        OTHER(9, "其他");
        private final int value;
        private final String desc;

        BankCardType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }


    /**
     * @author : lether
     * @date : 2018/11/18 19:03
     **/
    public enum MqChannel {
        /**
         * 收益评估
         */
        BENEFITS_EVALUATION("c_coin_deal_profit_valuation", "c_coin_deal_profit_valuation"),
        /**
         * 下单
         */
        PLACE_AN_ORDER("c_coin_deal_order_info", "c_coin_deal_order_info"),
        /**
         * 更新用户账户余额
         */
        UPDATE_USER_ACCOUNT_BALANCES("c_coin_spot_account", "c_coin_spot_account"),
        /**
         * 更新系统配置信息
         */
        UPDATE_SYSTEM_CONFIGURATION_INFORMATION("update_sys_info_", "update_sys_info_"),
        /**
         * 检查订单状态并撤单
         */
        CHECK_THE_ORDER_STATUS_AND_CANCEL_THE_ORDER("c_check_order_state_and_cancel", "c_check_order_state_and_cancel"),
        /**
         * 期现套利下单
         */
        FUTURE_ARBITRAGE_ORDER("c_coin_cash_future_order_info", "c_coin_cash_future_order_info"),
        /**
         * 期期套利下单
         */
        TIME_ARBITRAGE_ORDER("c_coin_future_future_order_info", "c_coin_future_future_order_info"),
        ;
        private final String value;
        private final String desc;

        MqChannel(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 内容类型相关
     *
     * @author lether
     */
    public enum HtmlType {
        /**
         * 单选下拉框
         */
        RADIO_DROP_DOWN_BOX(0, "单选下拉框"),
        /**
         * 多选下拉框
         */
        SELECT_MULTIPLE_DROP_DOWN_BOXES(1, "多选下拉"),
        /**
         *
         */
        SINGLE_LINE_TEXT_BOX(2, "单行文本框"),
        /**
         * 多行文本框
         */
        MULTI_LINE_TEXT_BOX(3, "多行文本框"),
        /**
         * 单选框
         */
        RADIOBUTTON(4, "单选框"),
        /**
         * 多选框
         */
        CHECKBOX(5, "多选框"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        HtmlType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 备忘录状态
     */
    public enum AdmMemorandumState {
        Unprocessed(0, "未处理"),
        Completed(1, "已完成"),
        Expired(2, "已失效"),
        ;
        private final int value;
        private final String desc;

        AdmMemorandumState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 日志状态
     *
     * @author lether
     */
    public enum LogState {
        /*
         *   异常
         */
        ABNORMAL(0, "异常"),
        /**
         * 正常
         */
        NORMAL(1, "正常"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        LogState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 消息状态：各种消息可共享状态
     */
    public enum MsgState {
        Unread(0, "未读"),
        HaveRead(1, "已读"),
        Expired(2, "已过期"),
        ;
        private final int value;
        private final String desc;

        MsgState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 用户登录模式
     *
     * @author lether
     */
    public enum UserLoginMode {
        /**
         * Cookie登录
         */
        COOKIES_TO_LOG_IN(0, "Cookie登录"),
        /**
         * 用户名密码
         */
        USERNAME_PASSWORD(1, "用户名密码"),
        /**
         * token登录
         */
        TOKEN_LOGIN(2, "token登录"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        UserLoginMode(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 证件类型
     *
     * @author lether
     */
    public enum IdType {
        /**
         * 身份证
         */
        IDENTITYCARD(0, "身份证"),
        /**
         * 驾照
         */
        DRIVINGLICENSE(1, "驾照"),
        /**
         * 护照
         */
        PASSPORT(2, "护照"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        IdType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 性别
     *
     * @author lether
     */
    public enum Gender {
        /**
         * 先生
         */
        SIR(0, "先生"),
        /**
         * 女士
         */
        LADY(1, "女士"),
        /**
         * 隐藏
         */
        CONCEAL(2, "隐藏"),
        /**
         * 其他
         */
        OTHER(99, "其他");
        private final int value;
        private final String desc;

        Gender(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum TripartiteMessageCodeType {
        /**
         * 短信文本
         */
        SMS_TEXT(1),
        /**
         * 短信语音
         */
        SMS_VOICE(2),
        /**
         * 短信图形码
         */
        SMS_GRAPHIC_CODE(3),
        /**
         * email文本
         */
        EMAIL_TEXT(4),
        /**
         * email语音
         */
        EMAIL_VOICE(5),
        /**
         * email图形码
         */
        EMAIL_GRAPHICS_CODE(6);
        private int value;

        TripartiteMessageCodeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * 手机验证码类型：业务类型
     *
     * @author lether
     */
    public enum TripartiteMessageContentType {
        /**
         * 注册  需要数据库中国中不存在     不需要登录
         */
        REGISTER(1),
        /**
         * 验证手机号码  需要用户没有手机且库中无此手机 －－ 需要登录
         */
        VERIFY_PHONE_NUMBER(2),
        /**
         * 解除手机号码  需要用户已绑定手机 －－ 需要登录
         */
        CANCEL_MOBILE_PHONE_NUMBER(3),
        /**
         * 身份验证    需要数据库中存在手机  －－ 需要登录：校验过后修改其他信息就不需要重新校验了
         */
        AUTHENTICATION(4),
        /**
         * 找回密码    需要数据库中存在该手机－－不需要登录
         */
        RETRIEVE_PASSWORD(5),
        /**
         * 设置交易密码  需要数据库中存在该手机－－不需要登录
         */
        SET_TRANSACTION_PASSWORD(6);
        private int value;

        TripartiteMessageContentType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }


    public enum AccountOperaType {
        Plus(0),
        Subtract(1),
        ;
        private int value;

        AccountOperaType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum CommOperaType {
        ADD(1),
        REMOVE(-1),
        ;
        private int value;

        CommOperaType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }


    public enum CustomerUserState {
        /**
         * 未处理
         */
        UNTREATED(0),
        /**
         * 已处理
         */
        PROCESSED(1),
        ;
        private int value;

        CustomerUserState(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * @author lether
     */
    public enum OrgType {
        /**
         * 私募
         */
        PRIVATE_PLACEMENT(0, "私募"),
        /**
         * 信托
         */
        TRUST(1, "信托"),
        /**
         * 公募
         */
        PUBLIC_PLACEMENT(2, "公募"),
        ;
        private final int value;
        private final String desc;

        OrgType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * @author lether
     */
    public enum ProductType {
        /**
         * 私募
         */
        PRIVATE_PLACEMENT(0, "私募"),
        /**
         * 信托
         */
        TRUST(1, "信托"),
        /**
         * 公募
         */
        PUBLIC_PLACEMENT(2, "公募"),
        ;
        private final int value;
        private final String desc;

        ProductType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 评论对象类型
     *
     * @author lether
     */
    public enum CommentType {
        /**
         * 机构
         */
        ORGANIZATION(0, "机构"),
        /**
         * 基金经理
         */
        FUND_MANAGER(1, "基金经理"),
        /**
         * 产品
         */
        PRODUCT(2, "产品"),
        /**
         * 路演
         */
        ROAD_SHOW(3, "路演");
        private final int value;
        private final String desc;

        CommentType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 预约对象类型   --- 这里因为改动量比较大，暂时不把这种重复性的东西提取出去，后期需要进行整改
     *
     * @author lether
     */
    public enum ReservationType {
        /**
         * 机构
         */
        ORGANIZATION(0, "机构"),
        /**
         * 基金经理
         */
        FUND_MANAGER(1, "基金经理"),
        /**
         * 产品
         */
        PRODUCT(2, "产品"),
        /**
         * 路演
         */
        ROAD_SHOW(3, "路演");
        private final int value;
        private final String desc;

        ReservationType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 预约状态 --- 这里因为改动量比较大，暂时不把这种重复性的东西提取出去，后期需要进行整改
     *
     * @author lether
     */
    public enum ReservationDealStatus {
        /**
         * 未处理
         */
        UNTREATED(0, "未处理"),
        /**
         * 已处理
         */
        PROCESSED(1, "已处理");
        private final int value;
        private final String desc;

        ReservationDealStatus(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 处理状态 --- 这里因为改动量比较大，暂时不把这种重复性的东西提取出去，后期需要进行整改
     *
     * @author lether
     */
    public enum DealStatus {
        /**
         * 未处理
         */
        UNTREATED(0, "未处理"),
        /**
         * 已处理
         */
        PROCESSED(1, "已处理");
        private final int value;
        private final String desc;

        DealStatus(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 处理状态
     *
     * @author lether
     */
    public enum DealResult {
        /**
         * 未处理
         */
        UNTREATED(0, "未处理"),
        /**
         * 已同意
         */
        HAS_AGREED_TO(1, "已同意"),
        /**
         * 目标拒绝
         */
        TARGET_REFUSED_TO(2, "目标拒绝");
        private final int value;
        private final String desc;

        DealResult(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * @author lether
     */
    public enum CustomerAuditState {
        /**
         * 待审核
         */
        TO_AUDIT(0, "待审核"),
        /**
         * 通过审核
         */
        GET_APPROVED(1, "通过审核"),
        /**
         * 再次提交
         */
        TO_SUBMIT_AGAIN(2, "再次提交"),
        /**
         * 未通过审核
         */
        NOT_APPROVED(-1, "未通过审核");
        private final int value;
        private final String desc;

        CustomerAuditState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 投资者认证结果
     *
     * @author lether
     */
    public enum InvestorQualifiedResult {
        C1("保守型"),
        C2("谨慎型"),
        C3("稳健型"),
        C4("积极型"),
        C5("激进型"),
        ;
        private final String value;

        InvestorQualifiedResult(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static InvestorQualifiedResult getByScore(int score) {
            if (score <= 7) {
                return C1;
            } else if (score <= 15) {
                return C2;
            } else if (score <= 23) {
                return C3;
            } else if (score <= 31) {
                return C4;
            }
            return C5;
        }
    }

    /**
     * 投资者认证选项分数
     *
     * @author lether
     */
    public enum InvestorQualifiedScore {
        A(0),
        B(1),
        C(2),
        D(3),
        E(4),
        ;
        private final int value;

        InvestorQualifiedScore(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum OnlineState {
        /**
         * 未在线
         */
        NOT_ONLINE(0),
        /**
         * 在线
         */
        ON_LINE(1);
        private final int value;

        OnlineState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 评论对象类型
     *
     * @author lether
     */
    public enum StoreUserType {
        /**
         * 客服
         */
        SERVICE(0, "客服"),
        /**
         * 基金经理
         */
        FUND_MANAGER(1, "基金经理"),
        /**
         * 机构代理
         */
        AGENT_FOR(2, "机构代理");
        private final int value;
        private final String desc;

        StoreUserType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }


    /**
     * 客服服务记录审核状态
     *
     * @author lether
     */
    public enum CustomerServiceAuditResult {
        /**
         * 待审核
         */
        TO_AUDIT(0, "待审核"),
        /**
         * 属实
         */
        VERIFIED(1, "属实"),
        /**
         * 非实
         */
        NOT_REAL(-1, "非实");
        private final int value;
        private final String desc;

        CustomerServiceAuditResult(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 用户名片自定义模块排版
     *
     * @author lether
     */
    public enum UserCardModuleType {
        /**
         *  个人简介
         */
        INDIVIDUAL_RESUME(0, "个人简介"),
        /**
         * 工作经历
         */
        WORK_EXPERIENCE(1, "工作经历"),
        /**
         * 教育经历
         */
        EDUCATION_EXPERIENCE(2, "教育经历"),
        /**
         *  个人荣誉
         */
        PERSONAL_HONOR(3, "个人荣誉"),
        /**
         *  我的产品
         */
        MY_PRODUCT(4, "我的产品"),
        /**
         * 我的相册
         */
        MY_PHOTO_ALBUMS(5, "我的相册"),
        /**
         * 自定义模块
         */
        CUSTOM_MODULE(6, "自定义模块"),
        ;
        private final int value;
        private final String desc;

        UserCardModuleType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 用户名片交换状态
     *
     * @author lether
     */
    public enum UserCardSwapState {
        /**
         *  未申请交换
         */
        NO_EXCHANGE_REQUEST(-1, "未申请交换"),

        /**
         * 已申请交换对方未处理
         */
        APPLICATION_FOR_EXCHANGE_HAS_NOT_BEEN_PROCESSED(0, "已申请交换对方未处理"),
        /**
         * 双方已同意
         */
        BOTH_PARTIES_HAVE_AGREED(1, "双方已同意"),
        /**
         * 对方已拒绝交换要求
         */
        THE_EXCHANGE_REQUEST_HAS_BEEN_REFUSED(2, "对方已拒绝交换要求"),
        /**
         * 对方已申请您未处理
         */
        THE_APPLICATION_HAS_NOT_BEEN_PROCESSED_BY_YOU(3, "对方已申请您未处理"),
        /**
         * 您主动拒绝交换要求
         */
        YOU_VOLUNTARILY_REFUSE_THE_EXCHANGE_REQUEST(4, "您主动拒绝交换要求"),
        ;
        private final int value;
        private final String desc;

        UserCardSwapState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 广告位置
     *
     * @author lether
     */
    public enum AdvertisePosition {
        /**
         * 广告位置0
         */
        advertising_position_0(0, "广告位置0"),
        /**
         * 广告位置1
         */
        ADVERTISING_POSITION_1(1, "广告位置1"),
        /**
         * 广告位置2
         */
        ADVERTISING_POSITION_2(2, "广告位置2"),
        /**
         * 广告位置3
         */
        ADVERTISING_POSITION_3(3, "广告位置3"),
        /**
         * 广告位置4
         */
        ADVERTISING_POSITION_4(4, "广告位置4"),
        /**
         * 广告位置5
         */
        ADVERTISING_POSITION_5(5, "广告位置5"),
        /**
         * 广告位置6
         */
        ADVERTISING_POSITION_6(6, "广告位置6"),
        /**
         * 广告位置7
         */
        ADVERTISING_POSITION_7(7, "广告位置7"),
        /**
         * 广告位置8
         */
        ADVERTISING_POSITION_8(8, "广告位置8"),
        /**
         * 广告位置9
         */
        ADVERTISING_POSITION_9(9, "广告位置9"),
        /**
         * 广告位置10
         */
        ADVERTISING_POSITION_10(10, "广告位置10"),
        /**
         * 广告位置11
         */
        ADVERTISING_POSITION_11(11, "广告位置11"),
        /**
         * 广告位置12
         */
        ADVERTISING_POSITION_12(12, "广告位置12"),
        ;
        private final int value;
        private final String desc;

        AdvertisePosition(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 预约对象类型
     *
     * @author lether
     */
    public enum ReservationUserType {
        /**
         * 普通用户
         */
        DOMESTIC_CONSUMER(0, "普通用户"),
        /**
         * 合格投资者
         */
        QUALIFIED_INVESTOR(1, "合格投资者"),
        /**
         * 金融从业者
         */
        FINANCIAL_PRACTITIONER(2, "金融从业者");
        private final int value;
        private final String desc;

        ReservationUserType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDesc(int value) {
            for (CommonEnumContainer.ReservationUserType ele : values()) {
                if (ele.getValue() == value) return ele.getDesc();
            }
            return null;
        }
    }


    public enum MessageType {
        /**
         * 评论审核结果
         */
        COMMENT_ON_AUDIT_RESULTS,
        /**
         * 回复审核结果
         */
        REPLY_TO_AUDIT_RESULTS,
        /**
         * 路演审核结果
         */
        ROAD_SHOW_AUDIT_RESULTS,
        /**
         * 客服服务记录
         */
        CUSTOMER_SERVICE_RECORD
    }

    public enum MessageViewState {
        /**
         * 未查看
         */
        DID_NOT_SEE,
        /**
         * 已查看
         */
        HAVE_TO_SEE
    }

    public enum NewsState {
        /**
         * 隐藏
         */
        HIDE(0, "隐藏"),
        /**
         * 显示
         */
        SHOW(1, "显示");
        private final int value;
        private final String desc;

        NewsState(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }


}
