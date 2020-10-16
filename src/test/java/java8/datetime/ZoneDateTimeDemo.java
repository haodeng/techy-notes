package java8.datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.util.Set;

public class ZoneDateTimeDemo {
    @Test
    public void now()
    {
        //2020-10-14T15:46:42.830+02:00[Europe/Copenhagen]
        //Default: take the local zone
        System.out.println(ZonedDateTime.now());

        //2020-10-14T08:46:42.839-05:00[America/Chicago]
        System.out.println(ZonedDateTime.now(ZoneId.of("America/Chicago")));

        //2020-10-14T15:46:42.830+02:00[Europe/Copenhagen]
        System.out.println(ZonedDateTime.now(Clock.systemDefaultZone()));

        //2020-10-14T08:46:42.839-05:00[America/Chicago]
        System.out.println(ZonedDateTime.now(Clock.system(ZoneId.of("America/Chicago"))));
    }

    @Test
    public void zones()
    {
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneIds);

        ZoneId zoneId = ZoneId.of("Europe/Paris");
        Assert.assertEquals("Europe/Paris", zoneId.toString());
    }

    @Test
    public void createZonedDateTime()
    {
        ZoneId zoneId = ZoneId.of("America/Vancouver");
        //Add zone to local java8.datetime
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.parse("2020-10-20T06:30"), zoneId);
        Assert.assertEquals("2020-10-20T06:30-07:00[America/Vancouver]", zonedDateTime.toString());

        //create from LocalDate and LocalTime, add zone to it
        zonedDateTime = ZonedDateTime.of(LocalDate.parse("2020-10-20"),
                LocalTime.parse("06:30"),
                zoneId);
        Assert.assertEquals("2020-10-20T06:30-07:00[America/Vancouver]", zonedDateTime.toString());

        zonedDateTime = ZonedDateTime.of(2020, 10, 20,
                6, 30, 0, 0,
                zoneId);
        Assert.assertEquals("2020-10-20T06:30-07:00[America/Vancouver]", zonedDateTime.toString());

        ZoneOffset offset = ZoneOffset.of("-07:00");
        //will validate offset and zoneId, if not match, throw DateTimeException
        ZonedDateTime.ofStrict(LocalDateTime.parse("2020-10-20T06:30"), offset, zoneId);
        Assert.assertEquals("2020-10-20T06:30-07:00[America/Vancouver]", zonedDateTime.toString());

        zonedDateTime = ZonedDateTime.parse("2020-10-20T06:30-07:00[America/Vancouver]");
        Assert.assertEquals("2020-10-20T06:30-07:00[America/Vancouver]", zonedDateTime.toString());
    }
}
