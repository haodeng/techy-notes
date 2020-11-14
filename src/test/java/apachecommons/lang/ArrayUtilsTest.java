package apachecommons.lang;

import org.junit.Test;
import org.apache.commons.lang3.ArrayUtils;

import static org.junit.Assert.*;

public class ArrayUtilsTest {

    @Test
    public void test()
    {
        int[] array = {1, 2, 3};

        assertTrue(ArrayUtils.contains(array, 1));
        assertFalse(ArrayUtils.contains(array, 100));

        assertEquals(0, ArrayUtils.indexOf(array, 1));
        //-1, not found
        assertEquals(-1, ArrayUtils.indexOf(array, 100));

        //{1, 2, 3, 4}
        int[] newArray = ArrayUtils.add(array, 4);
        assertEquals(4, newArray.length);
        assertEquals(4, newArray[3]);

        //{1, 2, 3, 4, 5, 6}
        int[] newArray2 = ArrayUtils.addAll(array, 4, 5, 6);
        assertEquals(6, newArray2.length);

        //{1, 2}
        int[] subArray = ArrayUtils.subarray(array, 0, 2);
        assertEquals(2, subArray.length);
        assertEquals(2, subArray[1]);

        //{1, 2}
        int[] removedArray = ArrayUtils.remove(array, 0);
        assertEquals(2, removedArray.length);
        assertEquals(2, removedArray[0]);

        //{100, 101, 102, 1, 2, 3}
        int[] newArray3 = ArrayUtils.insert(0, array, 100, 101, 102);
        assertEquals(6, newArray3.length);
        assertEquals(100, newArray3[0]);
        assertEquals(101, newArray3[1]);
        assertEquals(102, newArray3[2]);
        assertEquals(1, newArray3[3]);

        Integer[] integers = new Integer[]{1, 2, 3};
        assertEquals(Integer.valueOf(2), ArrayUtils.get(integers, 1));
        //not found, default -1
        assertEquals(Integer.valueOf(-1), ArrayUtils.get(integers, 100, -1));

        //{1, 2, 3}
        int[] copiedArray = ArrayUtils.clone(array);

        //{1, 3, 2}
        ArrayUtils.swap(copiedArray, 1, 2);
        assertEquals(3, copiedArray.length);
        assertEquals(3, copiedArray[1]);
        assertEquals(2, copiedArray[2]);

        assertTrue(ArrayUtils.isSorted(array));
        assertFalse(ArrayUtils.isSorted(copiedArray));

        //{1, 2, 3}
        int[] copiedArray2 = ArrayUtils.clone(array);
        //{3, 2, 1}
        ArrayUtils.reverse(copiedArray2);
        assertEquals(3, copiedArray2.length);
        assertEquals(3, copiedArray2[0]);
        assertEquals(2, copiedArray2[1]);
        assertEquals(1, copiedArray2[2]);
    }
}
