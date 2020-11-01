package maps;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 高度注意各种Map类集合Key/Value能不能存储null值的情况
 *
 * Map	    Key	        Value
 * HashMap	Nullable	Nullable
 * ConcurrentHashMap	NotNull	NotNull
 * TreeMap	NotNull	Nullable
 * 由于HashMap的干扰，很多人认为ConcurrentHashMap是可以置入null值。同理，Set中的value实际是Map中的key。
 */
public class NullableKeyValues {
    @Test
    public void hashMap()
    {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        hashMap.put("key", null);

        Assert.assertNull(hashMap.get(null));
        Assert.assertNull(hashMap.get("key"));
    }

    @Test
    public void concurrentHashMap()
    {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        Assert.assertThrows(NullPointerException.class, () -> {
            concurrentHashMap.put(null, null);
        });

        Assert.assertThrows(NullPointerException.class, () -> {
            concurrentHashMap.put("key", null);
        });
    }

    @Test
    public void treeMap()
    {
        Map<String, String> treeMap = new TreeMap<>();
        Assert.assertThrows(NullPointerException.class, () -> {
            treeMap.put(null, null);
        });

        treeMap.put("key", null);
        Assert.assertNull(treeMap.get("key"));
    }
}
