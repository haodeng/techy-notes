package guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

// RateLimiter can only be used in throttling of standalone applications.
public class RateLimiterTest {

    /**
     * Output:
     * get 1 tokens: 0.0s
     * get 1 tokens: 0.99742s
     * get 1 tokens: 0.994721s
     * get 1 tokens: 0.996052s
     * get 1 tokens: 0.999095s
     * get 1 tokens: 0.998881s
     * get 1 tokens: 0.994907s
     * get 1 tokens: 0.995209s
     * get 1 tokens: 0.99548s
     * get 1 tokens: 0.99966s
     */
    @Test
    public void test_oneTokenPerSec() {
        // at rate 1 token/s
        RateLimiter r = RateLimiter.create(1);

        int count = 0;
        while (count < 10) {
            System.out.println("get 1 tokens: " + r.acquire() + "s");
            count++;
        }
    }

    /**
     * Output:
     * get 5 tokens: 0.0s
     * get 1 tokens: 4.998276s
     * get 1 tokens: 0.995724s
     * get 1 tokens: 0.997914s
     * get 1 tokens: 0.997027s
     * get 1 tokens: 0.994803s
     */
    @Test
    public void test_oneTokenPerSecBurst() {
        // at rate 1 token/s
        RateLimiter r = RateLimiter.create(1);

        int count = 0;
        System.out.println("get 5 tokens: " + r.acquire(5) + "s");
        count += 5;
        while (count < 10) {
            System.out.println("get 1 tokens: " + r.acquire() + "s");
            count++;
        }
    }
}
