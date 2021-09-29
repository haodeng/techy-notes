package java16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Java16Test {

    @Test
    public void patternMatchingInstanceOf() {
        Object obj = "test";
        boolean result = false;
        if (obj instanceof String str && str.length() > 2) {
            result = true;
        }

        Assertions.assertTrue(result);
    }

    enum Gender {FEMALE, MALE}
    record Person(String name, int age, Gender gender) { }

    @Test
    public void testRecord() {
        Person person = new Person("Hao", 10, Gender.MALE);
        Assertions.assertEquals(person.name, "Hao");
        Assertions.assertEquals(person.age, 10);
        Assertions.assertEquals(person.gender, Gender.MALE);
    }

}
