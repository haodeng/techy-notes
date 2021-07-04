package java10;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Arrays;

public class LocalVariableTest {
    /**
     * Local-Variable Type Inference is the biggest new feature in Java 10 for developers.
     * It adds type inference to declarations of local variables with initializers.
     * Local type inference can be used only in the following scenarios:
     *
     *  * Limited only to Local Variable with initializer
     *  * Indexes of enhanced for loop or indexes
     *  * Local declared in for loop
     *
     *  Local Variable Type Inference Limitations:
     *  * Cannot use ‘var’ on variables without initializer
     *  * Cannot be used for multiple variable definition
     *  * Null cannot be used as an initializer for var
     *  * Cannot have extra array dimension brackets
     *  * Poly expressions that have lambdas, method references, and array initializers, will trigger an error
     */
    @Test
    public void test() {
        var indexes = new Integer[] {1, 2, 3};
        for (var index : indexes) {
            System.out.println(index);
        }
    }

    interface HelloWorld {
        public void greet();
    }

    @Test
    public void testAnonymousClass() {
        var helloImpl = new HelloWorld() {
            @Override
            public void greet() {
                System.out.println("Anonymous hello");
            }
        };

        helloImpl.greet();
    }
}
