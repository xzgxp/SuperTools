package top.danny.tools.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: DateUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-11 14:36:11
 */
public class DateUtilTest {

    /**
     * 时间格式
     */
    @Test
    public void dateFormatTest(){

    }

    /**
     * 时间-字符串转换
     */
    @Test
    public void dateTransformTest(){
        //字符串转date
        String strDate1="2017-08-01 12:01:02";
        Date date1=DateUtil.parseDateNewFormat(strDate1);
        System.out.println(date1);

        //date转字符串
        Date date2=DateUtil.getNowDate();
        String strDate2=DateUtil.getNewFormatDateString(date2);
        System.out.println(strDate2);
    }

    /**
     * 时间加减
     */
    @Test
    public void addTimeTest() {
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",下一秒的现在：" + formatDate(DateUtil.addSeconds(DateUtil.getNowDate(), 1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",下一分钟的现在：" + formatDate(DateUtil.addMinutes(DateUtil.getNowDate(), 1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",下个小时的现在：" + formatDate(DateUtil.addHours(DateUtil.getNowDate(), 1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",明天的现在：" + formatDate(DateUtil.addDays(DateUtil.getNowDate(), 1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",下周的现在：" + formatDate(DateUtil.addWeeks(DateUtil.getNowDate(), 1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",下个月的现在：" + formatDate(DateUtil.addMonths(DateUtil.getNowDate(), 1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",明年的现在：" + formatDate(DateUtil.addYears(DateUtil.getNowDate(), 1)));

        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",上一秒的现在：" + formatDate(DateUtil.addSeconds(DateUtil.getNowDate(), -1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",上一分钟的现在：" + formatDate(DateUtil.addMinutes(DateUtil.getNowDate(), -1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",上个小时的现在：" + formatDate(DateUtil.addHours(DateUtil.getNowDate(), -1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",昨天的现在：" + formatDate(DateUtil.addDays(DateUtil.getNowDate(), -1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",上周的现在：" + formatDate(DateUtil.addWeeks(DateUtil.getNowDate(), -1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",上个月的现在：" + formatDate(DateUtil.addMonths(DateUtil.getNowDate(), -1)));
        System.out.println("现在时间：" + formatDate(DateUtil.getNowDate()) + ",去年的现在：" + formatDate(DateUtil.addYears(DateUtil.getNowDate(), -1)));

    }

    public String formatDate(Date date) {
        return DateUtil.getNewFormatDateString(date);
    }
}
