package java8datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateAndTimeFormattingDemo {
    @Test
    public void format()
    {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-10-20T10:30:20-07:00[America/Vancouver]");

        Assert.assertEquals("2020-10-20-07:00",
                zonedDateTime.format(DateTimeFormatter.ISO_DATE));
        Assert.assertEquals("2020-10-20T10:30:20-07:00[America/Vancouver]",
                zonedDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        Assert.assertEquals("2020/10/20T10:30:20",
                zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss")));

        //formatting style either as SHORT, LONG or MEDIUM as part of the formatting option
        Assert.assertEquals("10/20/20 10:30 AM",
                zonedDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

        Assert.assertEquals("Oct 20, 2020 10:30:20 AM", zonedDateTime
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

        Assert.assertEquals("October 20, 2020 10:30:20 AM PDT",
                zonedDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));

    }

    @Test
    public void withLocale()
    {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2020-10-20T10:30:20-07:00[America/Vancouver]");

        Assert.assertEquals("20-Oct-2020 10:30:20",
                zonedDateTime.format(DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Locale.UK)));

        Assert.assertEquals("2020-10-20 10:30:20",
                zonedDateTime.format(DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Locale.CHINA)));

        Assert.assertEquals("2020-10-20 10:30:20",
                zonedDateTime.format(DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Locale.US)));
    }
}
