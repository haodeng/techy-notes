package java8datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;

/**
 * "OffsetDateTime, ZonedDateTime and Instant all store an instant on the time-line to nanosecond precision.
 *  Instant is the simplest, simply representing the instant.
 *
 *  OffsetDateTime adds to the instant the offset from UTC/Greenwich,
 * which allows the local date-time to be obtained.
 *
 *  ZonedDateTime adds full time-zone rules."
 */
public class OffsetDateTimeDemo {
    @Test
    public void now()
    {
        //2020-10-14T17:40:33.278+02:00
        System.out.println(OffsetDateTime.now());

        //2020-10-14T10:40:33.288-05:00
        System.out.println(OffsetDateTime.now(ZoneId.of("America/Chicago")));

        //2020-10-14T10:40:33.288-05:00
        System.out.println(OffsetDateTime.ofInstant(Instant.now(), ZoneId.of("America/Chicago")));
    }

    @Test
    public void constants()
    {
        //Strange year
        Assert.assertEquals("+999999999-12-31T23:59:59.999999999-18:00", OffsetDateTime.MAX.toString());
        Assert.assertEquals("-999999999-01-01T00:00+18:00", OffsetDateTime.MIN.toString());
    }

    @Test
    public void createOffsetDateTime()
    {
        ZoneOffset offset = ZoneOffset.of("+02:00");
        //add offset to local datetime
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.parse("2020-10-20T09:30:20"), offset);
        Assert.assertEquals("2020-10-20T09:30:20+02:00", offsetDateTime.toString());

        offsetDateTime = OffsetDateTime.of(LocalDate.parse("2020-10-20"),
                LocalTime.parse("09:30:20"),
                offset);
        Assert.assertEquals("2020-10-20T09:30:20+02:00", offsetDateTime.toString());

        offsetDateTime = OffsetDateTime.of(2020, 10, 20,
                9, 30, 20, 0,
                offset);
        Assert.assertEquals("2020-10-20T09:30:20+02:00", offsetDateTime.toString());

        offsetDateTime = OffsetDateTime.parse("2020-10-20T09:30:20+02:00");
        Assert.assertEquals("2020-10-20T09:30:20+02:00", offsetDateTime.toString());

        ZoneOffset utcOffset = ZoneOffset.UTC;
        offsetDateTime = OffsetDateTime.of(LocalDateTime.parse("2020-10-20T09:30:20"), utcOffset);
        //Z is for UTC
        Assert.assertEquals("2020-10-20T09:30:20Z", offsetDateTime.toString());
    }
}
