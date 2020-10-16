package java8.datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.MonthDay;
import java.time.YearMonth;

public class MonthDayAndYearMonthDemo {
    @Test
    public void now()
    {
        //--10-14
        System.out.println(MonthDay.now());

        //2020-10
        System.out.println(YearMonth.now());
    }

    @Test
    public void ofAndParse()
    {
        Assert.assertEquals("--10-20", MonthDay.of(10, 20).toString());
        Assert.assertEquals("2020-10", YearMonth.of(2020, 10).toString());

        Assert.assertEquals("--10-20", MonthDay.parse("--10-20").toString());
        Assert.assertEquals("2020-10", YearMonth.parse("2020-10").toString());
    }
}
