package guava.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

//https://github.com/google/guava/wiki/ImmutableCollectionsExplained
public class ImmutableCollectionsTest {

    @Test
    public void test_ImmutableSet()
    {
        ImmutableSet<String> colors = ImmutableSet.of(
                "red",
                "orange",
                "yellow",
                "green",
                "blue",
                "purple");

        Set<String> newSet = ImmutableSet.copyOf(colors); // defensive copy!
        Assert.assertEquals(colors.size(), newSet.size());

        ImmutableList<String> colorList = colors.asList();
        Assert.assertEquals(colors.size(), colorList.size());

        Set<String> set = ImmutableSet.of("a", "b", "c", "a", "d", "b");
        //will iterate over its elements in the order "a", "b", "c", "d".
        Assert.assertEquals(4, set.size());
    }

}
