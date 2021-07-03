package java9;

import org.junit.jupiter.api.Test;

public class InterfacePrivateMethodTest {

    interface MyTest {
        default void test1() {
            preTest();
            System.out.println("Test 1");
            postTest();
        }
        default void test2() {
            preTest();
            System.out.println("Test 2");
            postTest();
        }

        private void preTest() {
            System.out.println("Pre Test");
        }
        private void postTest() {
            System.out.println("Post Test");
        }

        void doSomething();
    }

    class MyTestImpl implements MyTest {

        @Override
        public void doSomething() {
            System.out.println("MyTestImpl doSomething");
        }
    }

    @Test
    public void demo() {
        MyTestImpl myTest = new MyTestImpl();
        myTest.test1();
        myTest.test2();
        myTest.doSomething();

        /**
         * Pre Test
         * Test 1
         * Post Test
         *
         * Pre Test
         * Test 2
         * Post Test
         *
         * MyTestImpl doSomething
         */

    }
}
