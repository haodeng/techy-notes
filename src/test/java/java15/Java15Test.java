package java15;

import org.junit.jupiter.api.Test;

public class Java15Test {
    @Test
    public void textBlock() {
        String html = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;

        System.out.println(html);
    }
}
