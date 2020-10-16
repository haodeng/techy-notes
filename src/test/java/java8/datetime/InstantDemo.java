package java8.datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class InstantDemo {

    /**
     * An Instant is a moment on the timeline in UTC,
     * a count of nanoseconds since the epoch of the first moment of 1970 UTC
     * (basically, see class doc for nitty-gritty details).
     * Since most of your business logic, data storage, and data exchange should be in UTC,
     * this is a handy class to be used often.
     */
    @Test
    public void now()
    {
        //2020-10-15T11:21:02.895Z, in UTC timezone
        System.out.println(Instant.now());
    }

    @Test
    public void constant()
    {
        //1970-01-01T00:00:00Z
        System.out.println(Instant.EPOCH);

        //+1000000000-12-31T23:59:59.999999999Z
        System.out.println(Instant.MAX);
        //-1000000000-01-01T00:00:00Z
        System.out.println(Instant.MIN);
    }

    @Test
    public void createInstant()
    {
        Instant instant = Instant.parse("2020-10-15T11:21:02.000Z");
        Assert.assertEquals("2020-10-15T11:21:02Z", instant.toString());

        instant = Instant.parse("2020-10-15T11:21:02Z");
        Assert.assertEquals("2020-10-15T11:21:02Z", instant.toString());
    }

    @Test
    public void convertToOtherDateTime()
    {
        Instant instant = Instant.parse("2020-10-15T11:21:02Z");

        //UTC instant to ZonedDateTime London, with all zone details
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Europe/London"));
        Assert.assertEquals("2020-10-15T12:21:02+01:00[Europe/London]", zonedDateTime.toString());

        //UTC instant to OffsetDateTime, only has offset info
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(1));
        Assert.assertEquals("2020-10-15T12:21:02+01:00", offsetDateTime.toString());
    }

    @Test
    public void plusAndMinus()
    {
        Instant instant = Instant.parse("2020-10-15T11:21:02Z");

        Assert.assertEquals("2020-10-15T11:21:02.100Z", instant.plusMillis(100).toString());
        Assert.assertEquals("2020-10-15T11:21:02.100Z", instant.plus(100, ChronoUnit.MILLIS).toString());

        Assert.assertEquals("2020-10-15T11:21:12Z", instant.plusSeconds(10).toString());
        Assert.assertEquals("2020-10-15T11:21:12Z", instant.plus(10, ChronoUnit.SECONDS).toString());

        //No plusMinutes, plusHours, plusDays method,
        // do not support plus week, month and year unit
        Assert.assertEquals("2020-10-15T11:22:02Z", instant.plus(1, ChronoUnit.MINUTES).toString());
        Assert.assertEquals("2020-10-15T12:21:02Z", instant.plus(1, ChronoUnit.HOURS).toString());
        Assert.assertEquals("2020-10-16T11:21:02Z", instant.plus(1, ChronoUnit.DAYS).toString());
    }

    @Test
    public void compare()
    {
        Instant instant = Instant.parse("2020-10-15T11:21:02Z");
        Instant instant2 = Instant.parse("2020-10-15T11:21:02Z");

        Assert.assertTrue(instant.equals(instant2));

        Instant instant3 = Instant.parse("2020-10-20T15:30:50Z");
        Assert.assertTrue(instant.isBefore(instant3));
        Assert.assertTrue(instant3.isAfter(instant));
    }

    /**
     * Calculates the amount of time until another instant in terms of the specified unit.
     */
    @Test
    public void until()
    {
        Instant instant = Instant.parse("2020-10-15T11:21:02Z");
        Instant instant2 = Instant.parse("2020-10-15T11:22:02Z");

        Assert.assertEquals(60 * 1000, instant.until(instant2, ChronoUnit.MILLIS));
        Assert.assertEquals(60, instant.until(instant2, ChronoUnit.SECONDS));
        Assert.assertEquals(1, instant.until(instant2, ChronoUnit.MINUTES));

        Instant instant3 = Instant.parse("2020-10-16T11:21:02Z");
        Assert.assertEquals(1, instant.until(instant3, ChronoUnit.DAYS));
        Assert.assertEquals(24, instant.until(instant3, ChronoUnit.HOURS));
        Assert.assertEquals(24 * 60, instant.until(instant3, ChronoUnit.MINUTES));
        Assert.assertEquals(24 * 60 * 60, instant.until(instant3, ChronoUnit.SECONDS));
        Assert.assertEquals(24 * 60 * 60 * 1000, instant.until(instant3, ChronoUnit.MILLIS));
    }

    /**
     * Only supports ChronoField:
     * MILLI_OF_SECOND, MICRO_OF_SECOND, NANO_OF_SECOND and INSTANT_SECONDS
     */
    @Test
    public void with()
    {
        Instant instant = Instant.parse("2020-10-15T11:21:02Z");

        /*
        This represents the concept of the sequential count of seconds where
        * 1970-01-01T00:00Z (ISO) is zero.
         */
        Assert.assertEquals("1970-01-01T00:00:01Z", instant.with(ChronoField.INSTANT_SECONDS, 1).toString());

        //Set millis, micro and nano of an instant
        Assert.assertEquals("2020-10-15T11:21:02.001Z", instant.with(ChronoField.MILLI_OF_SECOND, 1).toString());
        Assert.assertEquals("2020-10-15T11:21:02.000001Z", instant.with(ChronoField.MICRO_OF_SECOND, 1).toString());
        Assert.assertEquals("2020-10-15T11:21:02.000000001Z", instant.with(ChronoField.NANO_OF_SECOND, 1).toString());
    }
}
