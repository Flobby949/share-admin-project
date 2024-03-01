package top.ssy.share.admin.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ycshang
 * localdateTime
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(LocalDateTime date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(LocalDateTime date, String pattern) {
        if(date != null){
            DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期解析
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回Date
     */
    public static LocalDateTime parse(String date, String pattern) {
        return LocalDateTime.parse(date,DateTimeFormatter.ofPattern(pattern));
    }
}
