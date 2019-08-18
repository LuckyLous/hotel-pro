package us.luckylu.dev.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author lu
 * @create 2019-01-10 17:09
 */
public class DateTimeUtil {

    public static final String GENERAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String SPECIAL_DATE_TIME_TO_MINUTE_PATTERN = "yyyy/MM/dd HH:mm";

    public static final String GENERAL_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 固定格式化时间
     */
    public static String formatDateTime(LocalDateTime dateTime){
        return format(dateTime, GENERAL_DATE_TIME_PATTERN);
    }

    /**
     * 固定格式化日期
     */
    public static String formatDate(LocalDateTime dateTime){
        return format(dateTime, GENERAL_DATE_PATTERN);
    }

    /**
     * 格式化日期时间
     */
    public static String format(LocalDateTime dateTime, String pattern){
        if(dateTime == null){
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(LocalDate date, String pattern){
        return format(date.atStartOfDay(), pattern);
    }

    /**
     * 获取一个月的第一天
     */
    public static LocalDate firstDayOfMonth(LocalDate date){
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取一个月的最后一天
     */
    public static LocalDate lastDayOfMonth(LocalDate date){
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDateTime firstDayOfMonth(LocalDateTime dateTime){
        return dateTime.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDateTime lastDayOfMonth(LocalDateTime dateTime){
        return dateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取一天的开始时间
     */
    public static LocalDateTime getDayStart(LocalDateTime dateTime) {
        return dateTime.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间
     */
    public static LocalDateTime getDayEnd(LocalDateTime dateTime) {
        return dateTime.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

    /**
     * 获取时间戳
     * @param dateTime 时间日期
     * @return 时间戳 - 毫秒
     */
    public static Long toStamp(LocalDateTime dateTime){
        // dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli(); 定义时区
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Long toStamp(LocalDate date){
        return toStamp(date.atStartOfDay());
    }

    /**
     * 由时间戳转换为日期时间
     * @param millisTime
     * @return
     */
    public static LocalDateTime toDateTime(Long millisTime){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millisTime), ZoneId.systemDefault());
    }

    /**
     * 由时间戳转换为日期
     * @param millisTime
     * @return
     */
    public static LocalDate toDate(Long millisTime){
        return toDateTime(millisTime).toLocalDate();
    }

    /**
     * 返回时间差
     * @param start
     * @param end
     * @return
     */
    public static Period differTime(LocalDateTime start, LocalDateTime end){
        return Period.between(start.toLocalDate(), end.toLocalDate());
    }

    /**
     * 返回时间之差
     * @param start
     * @param end
     * @return
     */
    public static Long differTimeStamp(LocalDateTime start, LocalDateTime end) {
        return toStamp(end) - toStamp(start);
    }

    /**
     * 返回时间之差，若前者小于后者，返回0
     */
    public static Long differTimeStampMayZero(LocalDateTime start, LocalDateTime end) {
        Long result = differTimeStamp(start, end);
        return result < 0 ? 0L : result;
    }
}
