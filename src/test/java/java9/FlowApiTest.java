package java9;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class FlowApiTest {
    CountDownLatch countDownLatch = new CountDownLatch(50);

    class MySubscriber implements Flow.Subscriber<Integer> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("Subscribed");
            this.subscription = subscription;
            this.subscription.request(1); //requesting data from publisher
            System.out.println("onSubscribe requested 1 item");
        }

        @Override
        public void onNext(Integer item) {
            System.out.println("Processing " + item);
            this.subscription.request(1);
            countDownLatch.countDown();
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Some error happened");
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("All Processing Done");
        }
    }

    @SneakyThrows
    @Test
    public void demo() {
        // Create Publisher
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // Register Subscriber
        MySubscriber subs = new MySubscriber();
        publisher.subscribe(subs);

        // [1, 50]
        IntStream.rangeClosed(1, 50).forEach(i -> publisher.submit(i));

        countDownLatch.await();
    }
}
