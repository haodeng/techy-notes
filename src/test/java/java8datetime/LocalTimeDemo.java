package java8datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public class LocalTimeDemo {
    @Test
    public void now()
    {
        System.out.println(LocalTime.now());
    }

    @Test
    public void constants()
    {
        Assert.assertEquals("23:59:59.999999999", LocalTime.MAX.toString());
        Assert.assertEquals("00:00", LocalTime.MIN.toString());
        Assert.assertEquals("00:00", LocalTime.MIDNIGHT.toString());
        Assert.assertEquals("12:00", LocalTime.NOON.toString());
    }

    @Test
    public void ofAndParse()
    {
        LocalTime time = LocalTime.parse("07:30");
        Assert.assertEquals("07:30", time.toString());
        time = LocalTime.parse("07:30:35");
        Assert.assertEquals("07:30:35", time.toString());

        time = LocalTime.parse("07:30", DateTimeFormatter.ISO_LOCAL_TIME);
        Assert.assertEquals("07:30", time.toString());
        time = LocalTime.parse("07:30+01:00", DateTimeFormatter.ISO_OFFSET_TIME);
        Assert.assertEquals("07:30", time.toString());

        time = LocalTime.of(7, 30);
        Assert.assertEquals("07:30", time.toString());
        time = LocalTime.of(7, 30, 35);
        Assert.assertEquals("07:30:35", time.toString());
        time = LocalTime.of(7, 30, 35, 100);
        Assert.assertEquals("07:30:35.000000100", time.toString());
    }

    @Test
    public void plusAndMinus()
    {
        LocalTime time = LocalTime.parse("07:30");
        Assert.assertEquals("08:30", time.plusHours(1).toString());
        Assert.assertEquals("08:30", time.plus(1, ChronoUnit.HOURS).toString());
        Assert.assertEquals("08:30", time.plus(Duration.ofHours(1)).toString());

        Assert.assertEquals("07:20", time.minusMinutes(10).toString());
    }

    @Test
    public void compare()
    {
        LocalTime time = LocalTime.parse("07:30");
        LocalTime time2 = LocalTime.parse("07:30");
        Assert.assertTrue(time.equals(time2));

        LocalTime time3 = LocalTime.parse("08:30");
        Assert.assertTrue(time.isBefore(time3));
        Assert.assertTrue(time3.isAfter(time));
    }

    @Test
    public void getHourMinuteSeconds()
    {
        LocalTime time = LocalTime.parse("19:30");
        Assert.assertEquals(19, time.getHour());
        Assert.assertEquals(30, time.getMinute());
        Assert.assertEquals(0, time.getSecond());
        Assert.assertEquals(0, time.getNano());

        Assert.assertEquals(19, time.get(ChronoField.HOUR_OF_DAY));
        Assert.assertEquals(7, time.get(ChronoField.HOUR_OF_AMPM));
        //This counts the AM/PM within the day, from 0 (AM) to 1 (PM).
        Assert.assertEquals(1, time.get(ChronoField.AMPM_OF_DAY));
        Assert.assertEquals(30, time.get(ChronoField.MINUTE_OF_HOUR));
        Assert.assertEquals(0, time.get(ChronoField.SECOND_OF_MINUTE));
        Assert.assertEquals(0, time.get(ChronoField.NANO_OF_SECOND));
    }

    @Test
    public void truncateTo()
    {
        LocalTime time = LocalTime.parse("19:30:40");
        Assert.assertEquals("19:30:40", time.truncatedTo(ChronoUnit.SECONDS).toString());
        Assert.assertEquals("19:30", time.truncatedTo(ChronoUnit.MINUTES).toString());
        Assert.assertEquals("19:00", time.truncatedTo(ChronoUnit.HOURS).toString());
        Assert.assertEquals("00:00", time.truncatedTo(ChronoUnit.DAYS).toString());
    }

    @Test
    public void atDate()
    {
        LocalTime time = LocalTime.parse("19:30:40");
        LocalDateTime dateTime = time.atDate(LocalDate.parse("2020-10-13"));

        Assert.assertEquals("2020-10-13T19:30:40", dateTime.toString());
    }

    @Test
    public void with()
    {
        LocalTime time = LocalTime.parse("19:30:40");
        Assert.assertEquals("19:30:01", time.withSecond(1).toString());
        Assert.assertEquals("19:10:40", time.withMinute(10).toString());
        Assert.assertEquals("08:30:40", time.withHour(8).toString());

        Assert.assertEquals("19:10:40", time.with(ChronoField.MINUTE_OF_HOUR, 10).toString());
    }
}
