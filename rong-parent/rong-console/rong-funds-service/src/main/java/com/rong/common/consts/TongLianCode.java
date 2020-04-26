package com.rong.common.consts;

/**
 * 通联常量以及枚举类
 */
public class TongLianCode {

    /**
     * 通联机构号
     */
    public static final String inst_id = "80090000";
    /**
     * 银行编码
     */
    public static final String bank_id = "03040000";
    /**
     * 卡bin规则：123455+13位数字且不以9结尾
     */
    public static final String card_bin = "123455";
    /**
     * 供应商编码
     */
    public static final String supply_inst_code = "000000324";
    /**
     * 宝宝产品编码
     */
    public static final String product_code_cash_acct = "000709";
    /**
     * 通联版本号
     */
    public static final String ver_num = "1.00";

    /**
     * 通联业务码
     */
    public enum ProcessingCode {
        open_account(1087, "开户"),
        verify_code(3010, "验证码");

        private final int value;
        private final String desc;

        ProcessingCode(int value, String desc) {
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
     * 开户状态（开户获取验证码时，设置状态为1；开户上送验证码成功后，设置状态为0）
     */
    public enum openAccountStatus {
        open_account_wait(1, "开户等待状态"),
        open_account_success(0, "开户成功状态");

        private final int value;
        private final String desc;

        openAccountStatus(int value, String desc) {
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
     * 通联开户：签约类型
     */
    public enum SignType {
        webpage_sign(1, "网页签约"),
        shortcut_sign(2, "快捷签约"),
        trust_sign(3, "信任签约"),
        supply_sign(4, "供应商开户"),
        company_sign(6, "企业开户");

        private final int value;
        private final String desc;

        SignType(int value, String desc) {
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
