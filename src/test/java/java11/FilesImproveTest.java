package java11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesImproveTest {
    @Test
    public void test_writeAndReadString() throws IOException {
        String content = "Created by Hao";
        Path tempPath = Files.writeString(Files.createTempFile("test", ".txt"), content);
        System.out.println(tempPath);

        Assertions.assertEquals(content, Files.readString(tempPath));
    }
}
