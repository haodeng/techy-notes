package concurrent;

import lombok.SneakyThrows;

import java.util.stream.IntStream;

public class ThreadsTest {

    public static final int count = 0;

    static class TestRun implements Runnable
    {
        private int runId;
        public TestRun(int runId)
        {
            this.runId = runId;
        }

        @SneakyThrows
        @Override
        public void run() {
            while(true)
            {
                System.out.println(this.runId);
                Thread.sleep(runId * 1000);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        IntStream.range(1, 6)
                .forEach(i -> new Thread(new TestRun(i)).start());
    }
}
