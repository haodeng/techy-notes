package java8.datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class PeriodAndDurationDemo {
    @Test
    public void addByPeriodDuration()
    {
        LocalDateTime localDateTime = LocalDateTime.parse("2020-10-20T10:30:31");

        Assert.assertEquals("2020-10-20T10:30:31.100", localDateTime.plus(Duration.ofMillis(100)).toString());
        Assert.assertEquals("2020-10-20T10:30:32", localDateTime.plus(Duration.ofSeconds(1)).toString());
        Assert.assertEquals("2020-10-20T10:31:31", localDateTime.plus(Duration.ofMinutes(1)).toString());
        Assert.assertEquals("2020-10-20T11:30:31", localDateTime.plus(Duration.ofHours(1)).toString());

        Assert.assertEquals("2020-10-20T10:30:30", localDateTime.minus(Duration.ofSeconds(1)).toString());

        Assert.assertEquals("2020-10-21T10:30:31", localDateTime.plus(Period.ofDays(1)).toString());
        Assert.assertEquals("2020-10-27T10:30:31", localDateTime.plus(Period.ofWeeks(1)).toString());
        Assert.assertEquals("2020-11-20T10:30:31", localDateTime.plus(Period.ofMonths(1)).toString());
        Assert.assertEquals("2021-10-20T10:30:31", localDateTime.plus(Period.ofYears(1)).toString());
    }

    @Test
    public void between()
    {
        LocalDate localDate = LocalDate.parse("2020-10-20");
        LocalDate localDate2 = LocalDate.parse("2021-10-25");

        //localdate to localdate2 is 1 year, 0 month and 5 days
        Period between = Period.between(localDate, localDate2);
        Assert.assertEquals(5, between.getDays());
        Assert.assertEquals(0, between.getMonths());
        Assert.assertEquals(1, between.getYears());

        LocalDateTime localDateTime = LocalDateTime.parse("2020-10-20T10:30:35");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2020-10-20T10:30:30");

        Duration durationBetween = Duration.between(localDateTime, localDateTime2);
        Assert.assertEquals(-5, durationBetween.getSeconds());

        LocalDateTime localDateTime3 = LocalDateTime.parse("2020-10-20T11:30:35");
        durationBetween = Duration.between(localDateTime, localDateTime3);
        Assert.assertEquals(3600, durationBetween.getSeconds());

        Assert.assertEquals(3600, ChronoUnit.SECONDS.between(localDateTime, localDateTime3));
        Assert.assertEquals(60, ChronoUnit.MINUTES.between(localDateTime, localDateTime3));
        Assert.assertEquals(1, ChronoUnit.HOURS.between(localDateTime, localDateTime3));
        Assert.assertEquals(0, ChronoUnit.DAYS.between(localDateTime, localDateTime3));
    }
}
