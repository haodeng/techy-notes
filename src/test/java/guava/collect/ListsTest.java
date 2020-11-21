package guava.collect;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class ListsTest {

    @Test
    public void test_newList()
    {
        List<String> list = Lists.asList("1", new String[]{});
        Assert.assertEquals(1, list.size());

        //empty and null can be added
        List<String> list2 = Lists.asList("1", new String[]{"2", "3", "", null});
        assertEquals(5, list2.size());
        list2.forEach(System.out::println);

        //Create array list from iterable
        Set<String> set = Sets.newHashSet("1", "2", "3");
        List<String> list3 = Lists.newArrayList(set);
        assertEquals(3, list3.size());

        List<String> list4 = Lists.newArrayList("1", "2", "3");
        assertTrue(list4 instanceof ArrayList);
        assertEquals(3, list4.size());

        List<String> list5 = Lists.newLinkedList(set);
        assertTrue(list5 instanceof LinkedList);
        assertEquals(3, list5.size());
    }

    @Test
    public void test_reverse()
    {
        List<String> list = Lists.newArrayList("1", "2", "3");
        List<String> reversedList = Lists.reverse(list);

        assertEquals(3, reversedList.size());
        assertEquals("3", reversedList.get(0));
        assertEquals("2", reversedList.get(1));
        assertEquals("1", reversedList.get(2));
    }

    @Test
    public void test_partition()
    {
        List<String> list = Lists.newArrayList("1", "2", "3", "4", "5", "6");
        List<List<String>> subLists = Lists.partition(list, 2);

        //[[1,2], [3,4], [5,6]]
        assertEquals(3, subLists.size());
        assertEquals(2, subLists.get(0).size());
        assertEquals(2, subLists.get(1).size());
        assertEquals(2, subLists.get(2).size());

        List<List<String>> subLists2 = Lists.partition(list, 4);
        //[[1,2,3,4], [5,6]]
        assertEquals(2, subLists2.size());
        assertEquals(4, subLists2.get(0).size());
        assertEquals(2, subLists2.get(1).size());
    }
}
