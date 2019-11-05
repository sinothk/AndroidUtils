package com.sinothk.android.utils;

//import org.apache.commons.lang3.time.DateUtils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * <pre>
 *     更新  ：
 *     作者  : 梁玉涛
 *     主页  : https://github.com/sinothk
 *     日期  : 2018/1/18
 *     功能  : ODateUtil
 * </pre>
 */
public class DateUtil {
    SimpleDateFormat sdf;

//    public void main(String[] args) {
//
//        System.out.println("通过日期对象获得日期字符串2:");
//        String dateStr1 = DateUtil.getDateStrByDate(new Date(), "yyyy-MM-dd HH:mm:ss"); // yyyy年MM月dd日
//        // HH时mm分ss秒
//        System.out.println("t = " + dateStr1);
//
//        System.out.println("通过日期字符串对象获得日期:");
//        Date date = DateUtil.getDateByDateStr("2014-09-18 22:55:33", "yyyy-MM-dd HH:mm:ss");
//        System.out.println("t = " + date.toString());
//    }

    public String getDateStrByDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 日期转换成字符串
     *
     * @param date       日期对象,如new Date()
     * @param dateFormat 日期格式字符串,形如:"yyyy-MM-dd HH:mm:ss"
     * @return 返回日期字符串, 日期格式使用自定义的格式
     */
    public String getDateStrByDate(Date date, String dateFormat) {

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String str = format.format(date);

        return str;
    }

    /**
     * 字符串转换成日期
     *
     * @param dateStr    日期字符串,形如:"2014-09-18 22:55:33"
     * @param dateFormat 日期字符串的格式,如第一个参数为"2014-09-18 22:55:33",那么dateFormat=
     *                   "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public Date getDateByDateStr(String dateStr, String dateFormat) {

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算两个日期之间相差的天数 = longDate - shortDate
     *
     * @param shortDate
     * @param longDate
     * @return
     */
    public int getTwoDateInterval(Date shortDate, Date longDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(shortDate);
        long time1 = cal.getTimeInMillis();

        cal.setTime(longDate);
        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     */
    /**
     * 推算几天前是几月几号？
     *
     * @param days
     * @return
     */
    public Date getDateBeforeToday(int days) {
        Date nowtime = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowtime);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - days);
        // 2014-09-23 - 7 => 2014-09-16号
        return calendar.getTime();
    }

    private final long ONE_MINUTE = 60000L;
    private final long ONE_HOUR = 3600000L;
    private final long ONE_DAY = 86400000L;
    private final long ONE_WEEK = 604800000L;

    /**
     * 通过日期算岁数
     *
     * @param birthday
     * @return
     */
    public int getAge(Date birthday) {
        if (birthday == null) {
            Random rand = new Random();
            int randNum = rand.nextInt(16) + 16;
            return randNum;
        }

        long day = (new Date().getTime() - birthday.getTime()) / (24 * 60 * 60 * 1000) + 1;

        int age = (int) (day / 365);

        return age == 0 ? 1 : age;
    }

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     * @param milliseconds 毫秒时间戳
     * @return unit时间戳
     */
    private long time2Unit(long milliseconds, TimeUnit unit) {
        switch (unit) {
            case MSEC:
                return milliseconds / MSEC;
            case SEC:
                return milliseconds / SEC;
            case MIN:
                return milliseconds / MIN;
            case HOUR:
                return milliseconds / HOUR;
            case DAY:
                return milliseconds / DAY;
        }
        return -1;
    }

    /******************** 时间相关常量 ********************/
    /**
     * 毫秒与毫秒的倍数
     */
    private final int MSEC = 1;
    /**
     * 秒与毫秒的倍数
     */
    private final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    private final int MIN = 60000;
    /**
     * 时与毫秒的倍数
     */
    private final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    private final int DAY = 86400000;

    public enum TimeUnit {
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY
    }

    /**
     * 获取当前时间
     *
     * @return 毫秒时间戳
     */
    public long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * <p>Date类型</p>
     *
     * @return Date类型时间
     */
    public Date getCurTimeDate() {
        return new Date();
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }


    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获取今天的时间的凌晨 到 23点
     */
    private String[] getDayStarAndEnd() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(cal.getTime()) + " 00:00:00";
        String endTime = sdf.format(cal.getTime()) + " 23:59:59";
        String[] starAndEnd = {startTime, endTime};
        return starAndEnd;
    }

    /**
     * @param oldTime 较小的时间
     * @param newTime 较大的时间 (如果为空   默认当前时间 ,表示和当前时间相比)
     * @return -1 ：同一天.    0：昨天 .   1 ：至少是前天.
     * @throws ParseException 转换异常
     * @author LuoB.
     */
    private int isYeaterday(Date oldTime, Date newTime) throws ParseException {
        if (newTime == null) {
            newTime = new Date();
        }
        //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = format.format(newTime);
        Date today = format.parse(todayStr);
        //昨天 86400000=24*60*60*1000 一天
        if ((today.getTime() - oldTime.getTime()) > 0 && (today.getTime() - oldTime.getTime()) <= 86400000) {
            return 0;
        } else if ((today.getTime() - oldTime.getTime()) <= 0) { //至少是今天
            return -1;
        } else { //至少是前天
            return 1;
        }
    }

    private boolean isToday(Date time) {//判断是不是今天
        try {
            Date nowTime = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = format.format(nowTime);
            Date today = format.parse(todayStr);
            long starttime = today.getTime();
            long endtime = today.getTime() + 86400000;
            if (starttime <= time.getTime() && time.getTime() <= endtime) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * =========================================================================================
     */

    public String friendlyAfterTime(Date time) {
        return friendlyAfterTime(time.getTime());
    }

    public String friendlyAfterTime(long time) {
        //获取time距离当前的秒数
        int ct = (int) ((System.currentTimeMillis() - time) / 1000);

        if (ct == 0) {
            return "刚刚";
        }

        if (ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        }
        if (ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = ct / 86400;
            return day + "天前";
        }
        if (ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

    /**
     * @param date
     * @return
     */
    public String getFriendlyDate(Date date) {
        if (date == null) date = new Date();

        Date now = new Date();

        long ys = DateUtils.truncate(now, Calendar.YEAR).getTime();
        long ds = DateUtils.truncate(now, Calendar.DAY_OF_MONTH).getTime();
        long yd = DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime();

        long nowTime = now.getTime();
        long dateTime = date.getTime();

        if (dateTime < ys) {
            // 不是同一年
            return new SimpleDateFormat("yyyy年MM月", Locale.getDefault()).format(date);
        } else {
            if ((ds - yd) == (48 * 60 * 60 * 1000)) {
                return new SimpleDateFormat("前天 HH:mm", Locale.getDefault()).format(date);
            }

            if ((ds - yd) == (24 * 60 * 60 * 1000)) {
                return new SimpleDateFormat("昨天 HH:mm", Locale.getDefault()).format(date);
            }

            if (dateTime < ds) {
                // 同一年，但不是昨天之前
                return new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(date);
            }

            if (ds == yd) { //同一天
                long differentTime = (nowTime - dateTime);

                if (differentTime < ONE_MINUTE) {
                    // 一分钟内
                    return "刚刚";
                } else if (differentTime < ONE_HOUR) {
                    // 一小时内
                    return (int) Math.floor(differentTime * 1d / (60 * 1000)) + "分钟前";

                } else if (differentTime < 24 * ONE_HOUR) {
                    // 一小时内到24小时
                    return (int) Math.floor(differentTime * 1d / (60 * 60 * 1000)) + "小时前";
                } else {
                    return new SimpleDateFormat("今天 HH:mm", Locale.getDefault()).format(date);
                }
            } else {
                // 日期异常或异常
                return new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(date);
            }
        }
    }

    @Deprecated
    public String getFriendlyDate2(Date date) {

        if (date == null) {
            date = new Date();
        }

        Date now = new Date();
        long ys = DateUtils.truncate(now, Calendar.YEAR).getTime();
        long ds = DateUtils.truncate(now, Calendar.DAY_OF_MONTH).getTime();
        long yd = DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime();

        long nowTime = now.getTime();
        long dateTime = date.getTime();

        if (dateTime < ys) {
            // 不是同一年
            return new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(date);
        }

        if ((ds - yd) == (48 * 60 * 60 * 1000)) {
            return new SimpleDateFormat("前天 HH:mm", Locale.getDefault()).format(date);
        }

        if ((ds - yd) == (24 * 60 * 60 * 1000)) {
            return new SimpleDateFormat("昨天 HH:mm", Locale.getDefault()).format(date);
        }

        if (dateTime < ds) {
            // 同一年，但不是昨天之前
            return new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(date);
        }

        if (nowTime - dateTime > 60 * 60 * 1000) {
            return new SimpleDateFormat("今天 HH:mm", Locale.getDefault()).format(date);
        }

        if (nowTime - dateTime > 60 * 1000) {
            return (long) Math.floor((nowTime - dateTime) * 1d / 60000) + "分钟前";
        }

        if (nowTime - dateTime >= 0) {
            return "刚刚";
        }

        // 日期异常
        return new SimpleDateFormat("MM月dd日", Locale.getDefault()).format(date);
    }

    public String getWeek(Date date) {
        Calendar c = Calendar.getInstance();

        c.setTime(date);

        String Week = "";

        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week = "日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week = "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week = "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week = "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week = "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week = "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week = "六";
        }

        return "星 期 " + Week;
    }

    /**
     * Java通过生日计算星座
     *
     * @param month
     * @param day
     * @return
     */
    public String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];
    }

    /**
     * 通过生日计算属相
     *
     * @param year
     * @return
     */
    public String getChineseYear(int year) {
        if (year < 1900) {
            return "未知";
        }
        int start = 1900;
        String[] years = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪"};
        return years[(year - start) % years.length];
    }

    private final static String[] constellationArr = new String[]{"摩羯座",
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "摩羯座"};

    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
}
