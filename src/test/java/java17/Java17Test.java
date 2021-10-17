package java17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Java17Test {

  @Test
  public void textBlock() {
    String block = """
        hi,
        this is
        a
        block""";

    Assertions.assertEquals("hi,\n"
        + "this is\n"
        + "a\n"
        + "block", block);
  }

  record MyRecord(String firstName, String lastName) {

  }


  @Test
  public void testRecord() {
    MyRecord myRecord = new MyRecord("Hao", "Deng");
    Assertions.assertEquals("Hao", myRecord.firstName);
    Assertions.assertEquals("Deng", myRecord.lastName);
  }

}
