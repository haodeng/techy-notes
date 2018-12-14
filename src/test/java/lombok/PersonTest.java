package lombok;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonTest {

    @Test
    public void test(){
        Person p = new Person(
                "Hao",
                LocalDate.of(2020, 12, 12),
                new Address("1A", "Mars alley", "Lomlomba", "Umama", "Korkonkon Republic", "WE-DI-DI-290X-10"));

        Assert.assertEquals("Hao", p.getName());
        Assert.assertEquals("1A", p.getAddress().getHouseNumber());

        Assert.assertEquals("Person(name=Hao, dateOfBirth=2020-12-12, " +
                "address=Address(houseNumber=1A, street=Mars alley, city=Lomlomba, " +
                "state=Umama, country=Korkonkon Republic, zip=WE-DI-DI-290X-10))",
                p.toString());
    }
}