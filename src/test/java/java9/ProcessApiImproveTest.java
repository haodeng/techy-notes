package java9;

import org.junit.jupiter.api.Test;

public class ProcessApiImproveTest {
    @Test
    public void demo() {
        ProcessHandle currentProcess = ProcessHandle.current();
        System.out.println("Current Process Id: = " + currentProcess.pid());

        System.out.println(currentProcess.info().toString());
    }
}
