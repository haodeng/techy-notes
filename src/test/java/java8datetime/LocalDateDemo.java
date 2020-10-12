package java8datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.ValueRange;

/**
 * LocalDate is mainly used when timezone are not required to be explicitly specified in the context
 */
public class LocalDateDemo {

    @Test
    public void now()
    {
        //Obtains the current date from the system clock in the default time-zone.
        System.out.println(LocalDate.now());
    }

    /**
     * The LocalDate representing a specific day, month and year can be obtained
     * using the “of” method or by using the “parse” method.
     */
    @Test
    public void ofAndParse()
    {
        LocalDate date = LocalDate.of(2020, 10, 11);
        System.out.println(date);
        //By default in DateTimeFormatter.ISO_LOCAL_DATE (yyyy-MM-dd) format
        Assert.assertEquals("2020-10-11", date.toString());
        Assert.assertEquals("2020-10-11", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        Assert.assertEquals("20201011", date.format(DateTimeFormatter.BASIC_ISO_DATE));

        date = LocalDate.parse("2020-10-12");
        Assert.assertEquals("2020-10-12", date.toString());

        date = LocalDate.parse("20201012", DateTimeFormatter.BASIC_ISO_DATE);
        Assert.assertEquals("2020-10-12", date.toString());
    }

    @Test
    public void withChronoField()
    {
        LocalDate date = LocalDate.parse("2020-10-12");

        LocalDate secondDayOfMonth = date.withDayOfMonth(2);
        Assert.assertEquals("2020-10-02", secondDayOfMonth.toString());
        secondDayOfMonth = date.with(ChronoField.DAY_OF_MONTH, 2);
        Assert.assertEquals("2020-10-02", secondDayOfMonth.toString());
    }

    @Test
    public void plusAndMinus()
    {
        LocalDate date = LocalDate.parse("2020-10-12");

        //plus(1, ChronoUnit.DAYS) or plusDays
        LocalDate nextDate = date.plus(1, ChronoUnit.DAYS);
        Assert.assertEquals("2020-10-13", nextDate.toString());
        nextDate = date.plusDays(1);
        Assert.assertEquals("2020-10-13", nextDate.toString());

        LocalDate preDate = date.minusDays(1);
        Assert.assertEquals("2020-10-11", preDate.toString());

        LocalDate preMonthSameday = date.minusMonths(1);
        Assert.assertEquals("2020-09-12", preMonthSameday.toString());

        LocalDate nextYearSameday = date.plusYears(1);
        Assert.assertEquals("2021-10-12", nextYearSameday.toString());
    }

    @Test
    public void beforeAndAfter()
    {
        LocalDate date = LocalDate.parse("2020-10-12");

        LocalDate nextDate = date.plusDays(1);
        Assert.assertTrue(date.isBefore(nextDate));
        Assert.assertFalse(date.isAfter(nextDate));
    }

    @Test
    public void getChronoField()
    {
        LocalDate date = LocalDate.parse("2020-10-12");
        DayOfWeek monday = date.getDayOfWeek();
        Assert.assertEquals(DayOfWeek.MONDAY, monday);
        Assert.assertEquals(1, date.get(ChronoField.DAY_OF_WEEK));

        int dayOfMonth = date.getDayOfMonth();
        Assert.assertEquals(12, dayOfMonth);
        Assert.assertEquals(12, date.get(ChronoField.DAY_OF_MONTH));
    }

    @Test
    public void withTemporalAdjusters()
    {
        LocalDate date = LocalDate.parse("2020-10-12");
        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        Assert.assertEquals("2020-10-01", firstDayOfMonth.toString());

        LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        Assert.assertEquals("2020-10-19", nextMonday.toString());

        LocalDate firstMondayInThisMonth = date.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        Assert.assertEquals("2020-10-05", firstMondayInThisMonth.toString());
    }

    @Test
    public void ranges()
    {
        LocalDate date = LocalDate.parse("2020-10-12");

        ValueRange yearRange = date.range(ChronoField.DAY_OF_YEAR);
        Assert.assertEquals("1 - 366", yearRange.toString());

        ValueRange monthRange = date.range(ChronoField.DAY_OF_MONTH);
        Assert.assertEquals("1 - 31", monthRange.toString());
    }

    @Test
    public void toLocalDateTime()
    {
        LocalDate date = LocalDate.parse("2020-10-12");
        LocalDateTime dateTime = date.atTime(LocalTime.of(20, 10, 5));
        Assert.assertEquals("2020-10-12T20:10:05", dateTime.toString());

        dateTime = date.atTime(20,  10, 5);
        Assert.assertEquals("2020-10-12T20:10:05", dateTime.toString());

        //This returns a LocalDateTime formed from this date at the time of
        //     * midnight, 00:00, at the start of this date.
        LocalDateTime beginningOfDay = date.atStartOfDay();
        Assert.assertEquals("2020-10-12T00:00", beginningOfDay.toString());
    }

    @Test
    public void otherUtilityMethods()
    {
        LocalDate date = LocalDate.parse("2020-10-12");

        Assert.assertTrue(date.isLeapYear());
        Assert.assertFalse(LocalDate.parse("2021-10-11").isLeapYear());

        Assert.assertEquals(31, date.lengthOfMonth());
        Assert.assertEquals(366, date.lengthOfYear());
    }
}
