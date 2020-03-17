package com.zlx.bpms.constant;

/**
 * @Package: com.zlx.bpms.constant
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:正则常量类
 */
public class RegexConstant {
    /**
     * 日期时间正则表达式   correspond   yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIMEREGEX = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";

    /**
     * 空格、回车符、换行符、制表符正则表达式
     */
    public static final String SPECIALSYMBOLREGEX = "\\s*|\t|\r|\n";

    /**
     * 全部为英文字母
     */
    public static final String ENGORDEC = "^[\\da-zA-Z]+$";

    /**
     * 手机号
     */
    public static final String PHONENUMBER = "^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$";

    /**
     * 数字
     */
    public static final String NUMBER = "[\u4e00-\u9fa5]";

    /**
     * 手机号码位数
     */
    public static final int NUMBEROFNUMBERS = 11;
}
