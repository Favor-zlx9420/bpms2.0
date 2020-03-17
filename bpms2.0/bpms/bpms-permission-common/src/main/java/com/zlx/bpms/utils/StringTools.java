package com.zlx.bpms.utils;


import com.zlx.bpms.constant.RegexConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Package: com.bpms.auth.util
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:字符串工具
 */
public class StringTools extends StringUtils {

    private static Pattern EngOrDec = Pattern.compile(RegexConstant.ENGORDEC);

    private static Pattern PhoneNumber = Pattern.compile(RegexConstant.PHONENUMBER);

    private static Pattern NumberPattern = Pattern.compile(RegexConstant.NUMBER);


    /**
     * 判断是否为 AJAX 请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }


    /**
     * 判断字符串中是否全部为英文字母
     *
     * @param s
     * @return
     */
    public static boolean isLetter(String s) {
        Matcher matcher = EngOrDec.matcher(s);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断字符串是否为中文
     *
     * @param str
     * @return
     */
    public static boolean isChineseChar(String str) {
        boolean temp = false;
        Matcher m = NumberPattern.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

    /**
     * 判断是否是手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str) {
        //手机号应为11位数
        if (str.length() != RegexConstant.NUMBEROFNUMBERS) {
            return false;
        }
        return PhoneNumber.matcher(str).matches();
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {

        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取字符串随机值
     *
     * @return UUID
     */
    public static String getStringRandomData() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成指定数量随机数
     */
    public static String generateSpecifiedQuantityRandomData(int number) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < number; i++) {
            result += random.nextInt(10);
        }
        return result;
    }


    /**
     * 根据正则表达式替换字符串
     *
     * @param str   字符串
     * @param regex 正则
     * @return str
     */
    public static String replace(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        if (isNotEmpty(str)) {
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        }
        return null;
    }

    /**
     * 不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if ((str != null) && (str.length() > 0) && !"null".equals(str) && !"".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 具有对象长度
     *
     * @param obj 对象
     * @return true / false
     */
    public static boolean hasObjLength(Object obj) {
        return (obj != null && (obj + "").length() > 0 && !"null".equals((obj + "").toLowerCase()));
    }

    /**
     * 具有字符长度
     *
     * @param str 字符
     * @return true / false
     */
    public static boolean hasCharacterLength(String str) {
        return (str != null && str.length() > 0 && !"null".equals(str.toLowerCase()));
    }

    /**
     * 对象不为空
     */
    public static boolean objNotNull(Object value) {
        return hasObjLength(value);
    }

    /**
     * 对象为空
     */
    public static boolean objIsNull(Object value) {
        return !hasObjLength(value);
    }

    /**
     * 字符串不为空
     */
    public static boolean strNotNull(String value) {
        return hasCharacterLength(value);
    }

    /**
     * 更改字符大小写
     *
     * @param capitalize 是否大写
     * @param str        字符串
     * @return str
     */
    public static String changeCharacterCase(boolean capitalize, String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        StringBuffer sb = new StringBuffer(strLen);
        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (capitalize) {
                sb.append(Character.toUpperCase(str.charAt(i)));
            } else {
                sb.append(Character.toLowerCase(str.charAt(i)));
            }
        }
        //buf.append(str.substring(1));
        return sb.toString();
    }

    /**
     * 更改首字母大写
     *
     * @param capitalize 是否大写
     * @param str        字符串
     * @return str
     */
    private static String changeFirstCharacterCase(boolean capitalize,
                                                   String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(strLen);
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        } else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    /**
     * 将首字母变小写
     *
     * @param str 字符串
     * @return str
     */
    public static String lowerTheFirstCharacter(String str) {
        return changeFirstCharacterCase(false, str);
    }

    /**
     * 将首字母变大写
     *
     * @param str 字符串
     * @return str
     */
    public static String upperTheFirstCharacter(String str) {
        return changeFirstCharacterCase(true, str);
    }

    /**
     * 隐藏字符串
     *
     * @param str   字符串
     * @param begin 开始几个
     * @param end   结束几个
     * @return str
     * 手机号：前3后4
     * 身份证：前4后3
     * 银行卡：前4后4
     * 姓名 ： 前一后一
     */
    public static String toConceal(String str, int begin, int end) {
        if (!isNotEmpty(str)) {
            return str;
        } else {
            int length = str.length();
            int count = length - end - begin;
            StringBuffer sb = new StringBuffer(length);
            if (count > 0) {
                sb.append(str.substring(0, begin));
                for (int i = 0; i < count; i++) {
                    sb.append("*");
                }
                sb.append(str.substring(begin + count));
            } else {
                return null;
            }
            return sb.toString();
        }
    }

    /**
     * 是空的
     *
     * @param obj 对象
     * @return true / false
     */
    public static boolean isEmpty(String obj) {
        if (obj == null || "".equals(obj) || "null".equals(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 对象数组不为空
     *
     * @param objArr 对象数组
     * @return true / false
     */
    public static boolean isNotEmpty(Object[] objArr) {
        if ((objArr != null) && (objArr.length > 0)) {
            return true;
        }
        return false;
    }

    /**
     * unicode 字节码转换成 中文
     *
     * @param ascii
     * @return
     * @author 陈升平 2017-2-15
     */
    public static String decodeUnicode(String ascii) {
        char aChar;
        int len = ascii.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ascii.charAt(x++);
            if (aChar == '\\') {
                aChar = ascii.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ascii.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
}
