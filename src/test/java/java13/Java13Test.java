package java13;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Java13Test {

    enum TopThreeFamilyName {LEE, WONG, SUN}

    @Test
    public void switchYieldStatement() {
        TopThreeFamilyName familyName = TopThreeFamilyName.LEE;
        String hello = switch (familyName) {
            case LEE -> {
                System.out.println("Lee");
                yield "Hi, " + familyName.name();
            }
            case WONG -> {
                System.out.println("Hmm...");
                yield "Hello, " + familyName.name();
            }
            case SUN -> "Hej, " + familyName.name();
            default -> throw new IllegalStateException("Unexpected value: " + familyName);
        };
        Assertions.assertEquals("Hi, LEE", hello);
    }
}
