package com.zlx.bpms.utils;

import com.zlx.bpms.constant.BpmsConstant;
import com.zlx.bpms.constant.RegexConstant;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Package: com.bpms.auth.util
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:日期工具
 */
public class DataTools {

    private static ThreadLocal<Integer> lastFormatIndex = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return -1;
        }
    };

    private static ThreadLocal<String> lastFormatString = new ThreadLocal<String>();
    private static ThreadLocal<Boolean> lastCachedResult = new ThreadLocal<Boolean>();

    /**
     * 以下模式用于 {@link #isDateFormat(int, String)}
     */
    private static final Pattern date_ptrn1 = Pattern.compile("^\\[\\$\\-.*?\\]");
    private static final Pattern date_ptrn2 = Pattern.compile("^\\[[a-zA-Z]+\\]");
    private static final Pattern date_ptrn3a = Pattern.compile("[yYmMdDhHsS]");
    private static final Pattern date_ptrn3b = Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/,. :\"\\\\]+0*[ampAMP/]*$");
    //  elapsed time patterns: [h],[m] and [s]
    private static final Pattern date_ptrn4 = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)\\]");


    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_FORMAT_CHINESE = "yyyy年M月d日";

    public static String DATE_FORMAT_NOT_CHINESE = "yyyyMMdd";

    public static boolean isCellDateFormatted(Cell cell) {
        if (cell == null) return false;
        boolean bDate = false;
        double d = cell.getNumericCellValue();
        if (isValidExcelDate(d)) {
            CellStyle style = cell.getCellStyle();
            if (style == null) return false;
            int i = style.getDataFormat();
            String f = style.getDataFormatString();
            bDate = isDateFormat(i, f);
        }
        return bDate;
    }

    private static boolean isDateFormat(int formatIndex, String formatString) {
        if (isInternalDateFormat(formatIndex)) {
            cache(formatString, formatIndex, true);
            return true;
        }

        //如果我们没有得到真正的字符串，甚至不要缓存它，因为我们总是可以很快找到它
        if (formatString == null || formatString.length() == 0) {
            return false;
        }

        //首先检查缓存
        if (isCached(formatString, formatIndex)) {
            return lastCachedResult.get();
        }

        String fs = formatString;
        StringBuilder sb = new StringBuilder(fs.length());
        for (int i = 0; i < fs.length(); i++) {
            char c = fs.charAt(i);
            if (i < fs.length() - 1) {
                char nc = fs.charAt(i + 1);
                if (c == '\\') {
                    switch (nc) {
                        case '-':
                        case ',':
                        case '.':
                        case ' ':
                        case '\\':
                            // skip current '\' and continue to the next char
                            continue;
                    }
                } else if (c == ';' && nc == '@') {
                    i++;
                    // skip ";@" duplets
                    continue;
                }
            }
            sb.append(c);
        }
        fs = sb.toString();

        //如果指示经过时间，则短路：[h]，[m]或[s]
        if (date_ptrn4.matcher(fs).matches()) {
            cache(formatString, formatIndex, true);
            return true;
        }

        //如果以[$ -...]开头，则可能是一个日期，但
        //谁知道开始的全部是什么
        fs = date_ptrn1.matcher(fs).replaceAll("");
        //如果开始于 [Black] 或 [Yellow],
        //  那可能是一个日期
        fs = date_ptrn2.matcher(fs).replaceAll("");

        //类似 dd/mm/yy;[red]dd/mm/yy
        if (fs.indexOf(';') > 0 && fs.indexOf(';') < fs.length() - 1) {
            fs = fs.substring(0, fs.indexOf(';'));
        }
        //确保其中有一些日期字母
        if (!date_ptrn3a.matcher(fs).find()) {
            return false;
        }
        //是否在任何情况下均由以下组成
        //  y m d h s - \ / , . : [ ] T
        // 跟随自 AM/PM
        boolean result = date_ptrn3b.matcher(fs).matches();
        cache(formatString, formatIndex, result);
        return result;
    }

    private static boolean isCached(String formatString, int formatIndex) {
        String cachedFormatString = lastFormatString.get();
        return cachedFormatString != null && formatIndex == lastFormatIndex.get()
                && formatString.equals(cachedFormatString);
    }

    private static boolean isInternalDateFormat(int format) {
        switch (format) {
            // 内部日期格式，如第427页中所述
            // Microsoft Excel开发人员工具包...
            case 0x0e:
            case 0x0f:
            case 0x10:
            case 0x11:
            case 0x12:
            case 0x13:
            case 0x14:
            case 0x15:
            case 0x16:
            case 0x2d:
            case 0x2e:
            case 0x2f:
                return true;
        }
        return false;
    }

    private static void cache(String formatString, int formatIndex, boolean cached) {
        lastFormatIndex.set(formatIndex);
        lastFormatString.set(formatString);
        lastCachedResult.set(Boolean.valueOf(cached));
    }

    private static boolean isValidExcelDate(double value) {
        return (value > -Double.MIN_VALUE);
    }


    /**
     * 根据年月日 得到时间字符串
     *
     * @param year       年
     * @param month      月
     * @param day        日
     * @param dateFormat 格式
     * @return 2019-11-01
     */
    public static String getTime(Object year, Object month, Object day, String dateFormat) {
        StringBuilder sb = new StringBuilder();
        if (StringTools.objNotNull(year)) {
            sb.append(year);
        }
        sb.append(BpmsConstant.STRAIGHTLINE);
        if (StringTools.objNotNull(month)) {
            if ((month + "").length() == 1){
                sb.append("0");
            }
            sb.append(month);
        }
        if (StringTools.objNotNull(day)) {
            sb.append(BpmsConstant.STRAIGHTLINE);
            sb.append(day);
        }
        Date date = stringToDate(sb.toString(),dateFormat);
        return DataTools.dateToString(date, dateFormat);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 根据自定义格式获取当前日期时间
     *
     * @param dateFormat 自定义格式
     * @return str
     */
    public static String getCurrentDateTime(String dateFormat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s str
     * @return str
     * @throws ParseException exception
     */
    public static String dateToStamp(String s) throws ParseException {
        String res = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long lt = new Long(s);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取两小时之前的时间戳(毫秒数)
     *
     * @return long
     */
    public static long getTwoHoursBeforeTimeStamp() {
        return System.currentTimeMillis() - 7200000;
    }

    /**
     * 将日期类型转换为字符串日期时间类型
     *
     * @param date date
     * @return time
     */
    public static String dateToDateTime(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 将字符串日期转换为日期格式
     *
     * @param datestr 字符串日期
     * @return date
     */
    public static Date stringToDate(String datestr) {

        if (StringTools.isEmpty(datestr)) {
            return null;
        }
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            date = stringToDate(datestr, DATE_FORMAT_NOT_CHINESE);
        }
        return date;
    }

    /**
     * 将字符串日期转换为日期格式
     * 自定義格式
     *
     * @param datestr 字符串日期
     * @return 日期格式
     */
    public static Date stringToDate(String datestr, String dateformat) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 将日期格式日期转换为字符串格式
     *
     * @param date 日期
     * @return str
     */
    public static String dateToString(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        datestr = df.format(date);
        return datestr;
    }


    /**
     * 判断是否为日期
     *
     * @param str
     * @return
     */
    public static boolean isDateTime(String str) {
        if (null == str) {
            return false;
        }
        return Pattern.matches(RegexConstant.DATETIMEREGEX, str);
    }

    /**
     * 将日期格式日期转换为字符串格式 自定義格式
     *
     * @param date       日期
     * @param dateformat 自定义格式
     * @return str
     */
    public static String dateToString(Date date, String dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 获取日期的DAY值
     *
     * @param date 输入日期
     * @return day
     */
    public static int getDayOfDate(Date date) {
        int d = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        d = cd.get(Calendar.DAY_OF_MONTH);
        return d;
    }

    /**
     * 获取日期的MONTH值
     *
     * @param date 输入日期
     * @return month
     */
    public static int getMonthOfDate(Date date) {
        int m = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        m = cd.get(Calendar.MONTH) + 1;
        return m;
    }

    /**
     * 获取日期的YEAR值
     *
     * @param date 输入日期
     * @return year
     */
    public static int getYearOfDate(Date date) {
        int y = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        y = cd.get(Calendar.YEAR);
        return y;
    }

    /**
     * 获取星期几
     *
     * @param date 输入日期
     * @return date
     */
    public static int getWeekOfDate(Date date) {
        int wd = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        wd = cd.get(Calendar.DAY_OF_WEEK) - 1;
        return wd;
    }

    /**
     * 获取输入日期的当月第一天
     *
     * @param date 输入日期
     * @return date
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }

    /**
     * 获得输入日期的当月最后一天
     *
     * @param date date
     */
    public static Date getLastDayOfMonth(Date date) {
        return addDay(getFirstDayOfMonth(addMonth(date, 1)), -1);
    }

    /**
     * 判断是否是闰年
     *
     * @param date 输入日期
     * @return 是true 否false
     */
    public static boolean isLeapYEAR(Date date) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int year = cd.get(Calendar.YEAR);

        if (year % 4 == 0 && year % 100 != 0 | year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据整型数表示的年月日，生成日期类型格式
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return date
     */
    public static Date getDateByYMD(int year, int month, int day) {
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, day);
        return cd.getTime();
    }

    /**
     * 获取年周期对应日
     *
     * @param date  输入日期
     * @param iyear 年数  負數表示之前
     * @return date
     */
    public static Date getYearCycleOfDate(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 获取月周期对应日
     *
     * @param date 输入日期
     * @param i    天数
     * @return date
     */
    public static Date getMonthCycleOfDate(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.MONTH, i);

        return cd.getTime();
    }

    /**
     * 计算 开始时间 到 结束时间 相差多少年
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 年数
     */
    public static int getYearByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR) - df.get(Calendar.YEAR);
    }

    /**
     * 计算 开始时间 到 结束时间 相差多少个月
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 月数
     */
    public static int getMonthByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR) * 12 + dt.get(Calendar.MONTH) -
                (df.get(Calendar.YEAR) * 12 + df.get(Calendar.MONTH));
    }

    /**
     * 计算 开始时间 到 结束时间 相差多少天
     *
     * @param fromDate 开始时间
     * @param toDate   结束时间
     * @return 天数
     */
    public static long getDayByMinusDate(Object fromDate, Object toDate) {

        Date f = conversionObjectToDate(fromDate);

        Date t = conversionObjectToDate(toDate);

        long fd = f.getTime();
        long td = t.getTime();

        return (td - fd) / (24L * 60L * 60L * 1000L);
    }

    /**
     * 计算年龄
     *
     * @param birthday 生日日期
     * @param calcDate 要计算的日期点
     * @return
     */
    public static int calcAge(Date birthday, Date calcDate) {

        int cYear = getYearOfDate(calcDate);
        int cMonth = getMonthOfDate(calcDate);
        int cDay = getDayOfDate(calcDate);
        int bYear = getYearOfDate(birthday);
        int bMonth = getMonthOfDate(birthday);
        int bDay = getDayOfDate(birthday);

        if (cMonth > bMonth || (cMonth == bMonth && cDay > bDay)) {
            return cYear - bYear;
        } else {
            return cYear - 1 - bYear;
        }
    }

    /**
     * 从身份证中获取出生日期
     *
     * @param idno 身份证号码
     * @return
     */
    public static String getBirthDayFromIdCard(String idno) {
        Calendar cd = Calendar.getInstance();
        if (idno.length() == 15) {
            cd.set(Calendar.YEAR, Integer.valueOf("19" + idno.substring(6, 8))
                    .intValue());
            cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(8, 10))
                    .intValue() - 1);
            cd.set(Calendar.DAY_OF_MONTH,
                    Integer.valueOf(idno.substring(10, 12)).intValue());
        } else if (idno.length() == 18) {
            cd.set(Calendar.YEAR, Integer.valueOf(idno.substring(6, 10))
                    .intValue());
            cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(10, 12))
                    .intValue() - 1);
            cd.set(Calendar.DAY_OF_MONTH,
                    Integer.valueOf(idno.substring(12, 14)).intValue());
        }
        return dateToString(cd.getTime());
    }

    /**
     * 在输入日期上增加（+）或减去（-）天数
     *
     * @param date 输入日期
     * @param iday 要增加或减少的天数
     */
    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.DAY_OF_MONTH, iday);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     *
     * @param date   输入日期
     * @param imonth 要增加或减少的月分数
     */
    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.MONTH, imonth);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）年份
     *
     * @param date  输入日期
     * @param iyear 要增加或减少的年数
     */
    public static Date addYear(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 將OBJECT類型轉換為Date
     *
     * @param date obj
     * @return date
     */
    public static Date conversionObjectToDate(Object date) {

        if (date != null && date instanceof Date) {
            return (Date) date;
        }

        if (date != null && date instanceof String) {
            return stringToDate((String) date);
        }

        return null;

    }

    /**
     * 通过日期获取年龄
     *
     * @param date 日期
     * @return age
     */
    public static long getAgeByBirthday(String date) {

        Date birthday = stringToDate(date, DATE_FORMAT);
        long sec = System.currentTimeMillis() - birthday.getTime();

        long age = sec / (1000 * 60 * 60 * 24) / 365;

        return age;
    }

}
