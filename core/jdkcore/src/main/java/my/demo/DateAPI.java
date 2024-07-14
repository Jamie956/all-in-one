package my.demo;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateAPI {
    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void test() {
        long milli = 1714277679721L;
        Instant instant = Instant.ofEpochMilli(milli);
        LocalDateTime someDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        //时间戳（毫秒）
        long toMilli = someDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Assert.assertEquals(milli, toMilli);
        long toMilli2 = someDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Assert.assertEquals(milli, toMilli2);
        long toMilli3 = Timestamp.valueOf(someDateTime).getTime();
        Assert.assertEquals(milli, toMilli3);

        long toMilli4 = instant.toEpochMilli();
        Assert.assertEquals(milli, toMilli4);

        //日期格式化
        String formatDate = someDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
        Assert.assertEquals("2024-04-28", formatDate);

        //2024-10-04
        LocalDate localDate = LocalDate.ofEpochDay(20000);

        //是否闰年
        boolean leapYear = localDate.isLeapYear();
        Assert.assertTrue(leapYear);

        //LocalDate转换成Date
        Date from = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Assert.assertEquals(localDate.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)), new SimpleDateFormat(DATE_FORMATTER).format(from));

        //当前 LocalDateTime
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        //LocalDateTime 格式化
        String formatLocalDateTime = someDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
        Assert.assertEquals("2024-04-28", formatLocalDateTime);

        //格式化转 LocalDate
        LocalDate parseLocalDate = LocalDate.parse("2024-10-04", DateTimeFormatter.ofPattern(DATE_FORMATTER));
        Assert.assertEquals(localDate.toEpochDay(), parseLocalDate.toEpochDay());

        //月第一天
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        Assert.assertEquals("2024-10-01", firstDayOfMonth.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)));
        //月最后一天
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        Assert.assertEquals("2024-10-31", lastDayOfMonth.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)));
        //周最后一天
        LocalDate dayOfWeek = localDate.minusWeeks(0).with(DayOfWeek.SUNDAY);
        Assert.assertEquals("2024-10-06", dayOfWeek.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)));
        //周第一天
        LocalDate dayOfWeek2 = localDate.minusWeeks(0).with(DayOfWeek.MONDAY);
        Assert.assertEquals("2024-09-30", dayOfWeek2.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)));

        //星期几
        int value = localDate.getDayOfWeek().getValue();
        Assert.assertSame(5, value);

        //起始时间
        LocalDateTime start = LocalDateTime.of(localDate, LocalTime.MIN);
        Assert.assertEquals("2024-10-04 00:00:00", start.format(DateTimeFormatter.ofPattern(DATETIME_FORMATTER)));

        //年的最后一天
        LocalDate last = localDate.with(TemporalAdjusters.lastDayOfYear());
        Assert.assertEquals("2024-12-31", last.format(DateTimeFormatter.ofPattern(DATE_FORMATTER)));

    }
}