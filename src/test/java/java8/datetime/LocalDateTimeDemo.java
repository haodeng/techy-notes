package java8.datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimeDemo {

    @Test
    public void now()
    {
        //eg: 2020-10-14T14:35:38.800
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void constants()
    {
        //Strange year
        Assert.assertEquals("+999999999-12-31T23:59:59.999999999", LocalDateTime.MAX.toString());
        Assert.assertEquals("-999999999-01-01T00:00", LocalDateTime.MIN.toString());
    }

    @Test
    public void ofAndParse()
    {
        LocalDateTime dateTime = LocalDateTime.of(2020, Month.OCTOBER, 20, 06, 30);
        Assert.assertEquals("2020-10-20T06:30", dateTime.toString());

        dateTime = LocalDateTime.of(2020, 10, 20, 06, 30);
        Assert.assertEquals("2020-10-20T06:30", dateTime.toString());

        dateTime = LocalDateTime.of(
                LocalDate.of(2020, 10, 20),
                LocalTime.of(6, 30));
        Assert.assertEquals("2020-10-20T06:30", dateTime.toString());

        dateTime = LocalDateTime.parse("2020-10-20T06:30");
        Assert.assertEquals("2020-10-20T06:30", dateTime.toString());

        dateTime = LocalDateTime.parse("2020-10-20T06:30:10.100");
        Assert.assertEquals("2020-10-20T06:30:10.100", dateTime.toString());
    }

    @Test
    public void plusAndMinus()
    {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-20T06:30");
        Assert.assertEquals("2020-10-20T06:35", dateTime.plusMinutes(5).toString());
        Assert.assertEquals("2020-10-20T07:30", dateTime.plusHours(1).toString());
        Assert.assertEquals("2020-10-19T06:30", dateTime.minusDays(1).toString());
    }

    @Test
    public void compare()
    {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-20T06:30");
        LocalDateTime dateTime2 = LocalDateTime.parse("2020-10-20T06:30");

        Assert.assertTrue(dateTime.equals(dateTime2));

        LocalDateTime dateTime3 = LocalDateTime.parse("2020-10-21T06:30");
        Assert.assertTrue(dateTime.isBefore(dateTime3));
        Assert.assertTrue(dateTime3.isAfter(dateTime));
    }

    @Test
    public void getField()
    {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-20T06:30:20");
        Assert.assertEquals(2020, dateTime.getYear());
        Assert.assertEquals(Month.OCTOBER, dateTime.getMonth());
        Assert.assertEquals(10, dateTime.getMonthValue());

        Assert.assertEquals(20, dateTime.getDayOfMonth());
        Assert.assertEquals(6, dateTime.getHour());
        Assert.assertEquals(30, dateTime.getMinute());
        Assert.assertEquals(20, dateTime.getSecond());
        Assert.assertEquals(0, dateTime.getNano());
    }

    @Test
    public void with()
    {
        LocalDateTime dateTime = LocalDateTime.parse("2020-10-20T06:30:20");
        Assert.assertEquals("2020-10-20T06:30:01", dateTime.withSecond(1).toString());
        Assert.assertEquals("2020-10-20T06:10:20", dateTime.withMinute(10).toString());
        Assert.assertEquals("2020-10-20T19:30:20", dateTime.withHour(19).toString());
        Assert.assertEquals("2020-10-01T06:30:20", dateTime.withDayOfMonth(1).toString());
        Assert.assertEquals("2020-12-20T06:30:20", dateTime.withMonth(12).toString());
        Assert.assertEquals("1900-10-20T06:30:20", dateTime.withYear(1900).toString());
    }
}
