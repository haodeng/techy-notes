package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StreamParallel {
    public static void main(String[] args) {
        System.out.println(String.format("Processors：%d", Runtime.getRuntime().availableProcessors()));

        // 1 million random numbers
        Random random = new Random();
        List<Integer> list = new ArrayList<>(1000_0000);

        for (int i = 0; i < 1000_0000; i++) {
            list.add(random.nextInt(100));
        }

        long prevTime = getCurrentTime();
        list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("Single thread：%d millis", getCurrentTime() - prevTime));

        prevTime = getCurrentTime();
        list.stream().parallel().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("parallel：%d millis", getCurrentTime() - prevTime));

        /*
        Example output of 12 cores Mac:
        Processors：12
        494841042
        Single thread：237
        494841042
        parallel：88
         */
    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
