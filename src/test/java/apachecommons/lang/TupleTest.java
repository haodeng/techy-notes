package apachecommons.lang;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.junit.Test;

import static org.junit.Assert.*;

public class TupleTest {

    @Test
    public void test_ImmutablePair()
    {
        ImmutablePair<String, Long> pair = ImmutablePair.of("Count", 100L);

        assertEquals("Count", pair.left);
        assertEquals(Long.valueOf(100), pair.right);

        assertEquals("Count", pair.getKey());
        assertEquals(Long.valueOf(100), pair.getValue());

        ImmutablePair<String, Long> nullPair = ImmutablePair.nullPair();
        assertNull(nullPair.left);
        assertNull(nullPair.right);
    }

    @Test
    public void test_MutablePair()
    {
        MutablePair<String, Long> pair = MutablePair.of("Count", 100L);
        assertEquals("Count", pair.left);
        assertEquals(Long.valueOf(100), pair.right);

        pair.setLeft("New Count");
        pair.setRight(200L);
        assertEquals("New Count", pair.left);
        assertEquals(Long.valueOf(200), pair.right);
    }

    @Test
    public void test_ImmutableTriple()
    {
        ImmutableTriple<String, Integer, Boolean> triple = ImmutableTriple.of("Test", 10, Boolean.FALSE);
        assertEquals("Test", triple.left);
        assertEquals(Integer.valueOf(10), triple.middle);
        assertEquals(Boolean.FALSE, triple.right);
    }

    @Test
    public void test_MutableTriple()
    {
        MutableTriple<String, Integer, Boolean> triple = MutableTriple.of("Test", 10, Boolean.FALSE);
        assertEquals("Test", triple.left);
        assertEquals(Integer.valueOf(10), triple.middle);
        assertEquals(Boolean.FALSE, triple.right);

        triple.setLeft("Test 2");
        triple.setMiddle(20);
        triple.setRight(Boolean.TRUE);
        assertEquals("Test 2", triple.left);
        assertEquals(Integer.valueOf(20), triple.middle);
        assertEquals(Boolean.TRUE, triple.right);
    }
}
