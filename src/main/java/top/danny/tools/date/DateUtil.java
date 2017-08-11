package top.danny.tools.date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 11:01:57
 * 【依赖】commons-lang.commons-lang
 */
public class DateUtil extends org.apache.commons.lang.time.DateUtils {

    public final static long ONE_DAY_SECONDS = 86400;

    /*
     * private static DateFormat dateFormat = null; private static DateFormat
     * longDateFormat = null; private static DateFormat dateWebFormat = null;
     */
    public final static String shortFormat = "yyyyMMdd";

    public final static String longFormat = "yyyyMMddHHmmss";

    public final static String webFormat = "yyyy-MM-dd";

    public final static String timeFormat = "HHmmss";

    public final static String monthFormat = "yyyyMM";

    public final static String chineseDtFormat = "yyyy年MM月dd日";

    public final static String newFormat = "yyyy-MM-dd HH:mm:ss";

    public final static String newFormat2 = "yyyy-MM-dd HH:mm";

    public final static String newFormat3 = "yyyy-MM-dd HH";

    public final static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    static {
        /*
         * dateFormat = new SimpleDateFormat(shortFormat);
		 * dateFormat.setLenient(false); longDateFormat = new
		 * SimpleDateFormat(longFormat); longDateFormat.setLenient(false);
		 * dateWebFormat = new SimpleDateFormat(webFormat);
		 * dateWebFormat.setLenient(false);
		 */
    }

    public static long ONE_DAY_MILL_SECONDS = 86400000;

    public static Date getNowDate() {
        return new Date();
    }

    public static Date getDate(long millsecord) {
        return new Date(millsecord);
    }

    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    public static void main(String[] args) {
        Date n = new Date();
        Date mm = formatDate(n, "yyyy-MM-dd");
        System.out.println(mm.toString());
    }

    //日期格式转换，返回日期类型
    public static Date formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        ParsePosition pos = new ParsePosition(0);
        Date newDate = formatter.parse(dateString, pos);
        return newDate;
    }

    //日期格式转换，返回String
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    public static String formatByLong(long date, String format) {
        return new SimpleDateFormat(format).format(new Date(date));
    }

    public static String formatByString(String date, String format) {
        if (StringUtils.isNotBlank(date)) {
            return new SimpleDateFormat(format).format(new Date(NumberUtils
                    .toLong(date)));
        }
        return StringUtils.EMPTY;
    }

    public static String formatShortFormat(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(shortFormat).format(date);
    }

    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTime(String sDate, String format)
            throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
            throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    public static Date parseDateNewFormat(String sDate) {
        Date d = parseDateHelp(sDate, newFormat);
        if (null != d) {
            return d;
        }
        d = parseDateHelp(sDate, newFormat2);
        if (null != d) {
            return d;
        }
        d = parseDateHelp(sDate, newFormat3);
        if (null != d) {
            return d;
        }
        d = parseDateHelp(sDate, webFormat);
        if (null != d) {
            return d;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(newFormat);
            return dateFormat.parse(sDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    private static Date parseDateHelp(String sDate, String format) {
        if ((sDate != null) && (sDate.length() == format.length())) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }

    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * @param date1
     * @param secs
     * @return
     */

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     *
     * @param hourStr
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     *
     * @param
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得新的日期
     *
     * @param date1 日期
     * @param days  天数
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * 取得从当前开始多少天后的新日期
     *
     * @param days 天数
     * @return 新的日期
     */
    public static Date addDaysFromNow(long days) {
        return addSeconds(new Date(System.currentTimeMillis()), days
                * ONE_DAY_SECONDS);
    }

    public static String getTomorrowDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    public static String getFullDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FULL_DATE_FORMAT);
        return getDateString(date, dateFormat);
    }


    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    public static String getYesterDayDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     *
     * @param date
     * @return
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis()
                - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔毫秒数（日期1-日期2）
     *
     * @param one
     * @param two
     * @return 间隔毫秒数
     */
    public static long getDiffMilliseconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis());
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数
     *
     * @param one
     * @param two
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (24 * 60 * 60 * 1000);
    }

    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidShortDateFormat(String strDate,
                                                 String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    public static String convert(String dateString, DateFormat formatIn,
                                 DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    public static String convert2WebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * @param date1
     * @param date2
     * @param
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2,
                                          DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String formatTimeRange(Date startDate, Date endDate,
                                         String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range
                / org.apache.commons.lang.time.DateUtils.MILLIS_PER_DAY;
        long hour = (range % org.apache.commons.lang.time.DateUtils.MILLIS_PER_DAY)
                / org.apache.commons.lang.time.DateUtils.MILLIS_PER_HOUR;
        long minute = (range % org.apache.commons.lang.time.DateUtils.MILLIS_PER_HOUR)
                / org.apache.commons.lang.time.DateUtils.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     *
     * @return
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 得到系统当前的时间
     *
     * @return
     */
    public static String currentTime(String format) {
        if (StringUtils.isBlank(format)) {
            return format(new Date(), newFormat);
        } else {
            return format(new Date(), format);
        }
    }

    /**
     * Returns true if endDate is after startDate or if startDate equals
     * endDate. Returns false if either value is null. If equalOK, returns true
     * if the dates are equal.
     **/
    public static boolean isValidDateRange(Date startDate, Date endDate,
                                           boolean equalOK) {
        // false if either value is null
        if (startDate == null || endDate == null) {
            return false;
        }

        if (equalOK) {
            // true if they are equal
            if (startDate.equals(endDate)) {
                return true;
            }
        }

        // true if endDate after startDate
        if (endDate.after(startDate)) {
            return true;
        }

        return false;
    }

    // 用在日历系统中，表示今天，明天，昨天
    public static boolean isToday(long time) {
        return isSameDay(new Date(time), new Date());
    }

    public static boolean isYesterday(long time) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(System.currentTimeMillis());

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) + 1 == cal2
                .get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isTomorrow(long time) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(System.currentTimeMillis());

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) - 1 == cal2
                .get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 检查指定的时间与当前时间的间隔是否大于interval
     *
     * @param time
     * @param interval
     * @return
     */
    public static boolean compareWithNow(long time, long interval) {
        if ((System.currentTimeMillis() - time) > interval) {
            return true;
        }
        return false;
    }

    /**
     * 当前时间与指定时间比，还有几天
     *
     * @param
     * @param
     * @return
     */
    public static long getDiffDaysWithNow(long target) {
        long t1 = target - System.currentTimeMillis();
        if (t1 < 0) {
            return -1;
        }
        return t1 / (24 * 60 * 60 * 1000);
    }

    /**
     * <pre>
     * 指定时间据当前时间已过去多少天
     * 不足的一天的天数不算入结果
     * 如 2.99天--->2天
     * </pre>
     *
     * @param target
     * @return
     */
    public static long getPastDaysWithNow(long target) {
        long t1 = System.currentTimeMillis() - target;
        if (t1 < 0) {
            return -1;
        }
        return t1 / (24 * 60 * 60 * 1000);
    }

    /**
     * <pre>
     * 输入时间和当前时间比较
     * 多于24小时，--> X天
     * 多于1小时， --> X小时
     * 多于1分钟， --> X分钟
     * 多于1秒， --> X秒
     * 小于1秒， --> 0
     * 如果输入时间比当前时间小，--> 0
     * </pre>
     *
     * @param target
     * @return
     */
    public static String getDynamicLeftTime(long target) {
        long t1 = target - System.currentTimeMillis();
        if (t1 < 0) {
            return "0";
        }
        long days = t1 / (24 * 60 * 60 * 1000);
        if (days > 0) {
            return days + "天";
        }
        long hours = t1 / (60 * 60 * 1000);
        if (hours > 0) {
            return hours + "小时";
        }
        long minutes = t1 / (60 * 1000);
        if (minutes > 0) {
            return minutes + "分钟";
        }
        long seconds = t1 / (1000);
        if (seconds > 0) {
            return seconds + "秒";
        }
        return "0";
    }

    public static String getDynamicPassTime(long target) {
        String meaningfulTimeStr = null;
        long curTime = System.currentTimeMillis();
        long timeGap = (curTime - target) / 1000;
        if (timeGap > 60 * 60 * 24 * 2) {
            // 超过昨天前，显示日期
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date targetDate = new Date(target);
            meaningfulTimeStr = formater.format(targetDate);
        } else if (timeGap > 60 * 60 * 24 && timeGap <= 60 * 60 * 24 * 2) {// 小于2天，显示昨天
            meaningfulTimeStr = "昨天";
        } else if (timeGap > 60 * 60 && timeGap <= 60 * 60 * 24) { // 小于一天，显示x小时前
            Integer hourNum = (int) (timeGap / (60 * 60));
            meaningfulTimeStr = hourNum + "小时前";
        } else if (timeGap > 60 * 5 && timeGap <= 60 * 60) { // 小于一小时，显示x分钟前
            Integer minNum = (int) (timeGap / 60);
            meaningfulTimeStr = minNum + "分钟前";
        } else if (timeGap <= 60 * 5) { // 小于5分钟，显示刚刚
            meaningfulTimeStr = "刚刚";
        }

        return meaningfulTimeStr;

    }

    /**
     * 获取前一天日期
     *
     * @param date
     * @return
     */
    public static Date getBeforeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取下一天
     *
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }


}
