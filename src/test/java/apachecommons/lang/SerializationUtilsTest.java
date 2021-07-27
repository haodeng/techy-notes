package apachecommons.lang;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class SerializationUtilsTest {
    @AllArgsConstructor
    @Data
    static class Person implements Serializable {
        private String name;
        private int age;
        Address address;
    }

    @AllArgsConstructor
    @Data
    static class Address implements Serializable {
        private String addr1;
        private String addr2;
        private String zip;
    }

    @Test
    public void test_deepCopy() {
        Address address = new Address("addr1", "addr2", "2000");
        Person person = new Person("test", 10, address);
        Person copiedPerson = (Person) SerializationUtils.clone(person);

        Assertions.assertEquals(copiedPerson, person);
        Assertions.assertEquals("test", copiedPerson.getName());
        Assertions.assertEquals(10, copiedPerson.getAge());

        Address copiedAddress = copiedPerson.getAddress();
        Assertions.assertEquals(copiedAddress, address);
        Assertions.assertEquals("addr1", copiedAddress.getAddr1());
        Assertions.assertEquals("addr2", copiedAddress.getAddr2());
        Assertions.assertEquals("2000", copiedAddress.getZip());
    }
}
