package maps;

import io.timeandspace.smoothie.SmoothieMap;
import net.openhft.chronicle.map.ChronicleMap;
import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jol.info.GraphLayout;

import java.sql.Timestamp;
import java.util.*;

public class MapFootprintTest {
    @Test
    public void test_putGetRemove()
    {
        Map<String, Object> testMap = SmoothieMap.<String, Object>newBuilder()
                .expectedSize(10)
                .build();

        testMap.put("key1", 1);
        testMap.put("key2", 2L);
        testMap.put("key3", true);
        testMap.put("key4", "4");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        testMap.put("key5", timestamp);

        Assert.assertEquals(testMap.get("key1"), 1);
        Assert.assertEquals(testMap.get("key2"), 2L);
        Assert.assertEquals(testMap.get("key3"), true);
        Assert.assertEquals(testMap.get("key4"), "4");
        Assert.assertEquals(testMap.get("key5"), timestamp);

        Object value1 = testMap.remove("key1");
        Assert.assertEquals(value1, 1);
        Assert.assertNull(testMap.get("key1"));
        Assert.assertNull(testMap.remove("key1"));
    }

    /**
     * SmoothieMap has less footprint than HashMap
     */
    @Test
    public void evaluate_HashMap()
    {
        List<Map<String, Object>> rows = new ArrayList<>();

        //100000 rows of HashMap
        for (int i = 0; i < 100000; i++)
        {
            Map<String, Object> map = new HashMap<>(20);
            for (int k = 0; k < 20; ++k)
            {
                map.put(k + "", k);
            }

            rows.add(map);
        }

        /**
         * java.util.ArrayList@18eed359d footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *    2000000        24  48000000   [C
         *          1    426856    426856   [Ljava.lang.Object;
         *     100000       144  14400000   [Ljava.util.HashMap$Node;
         *         20        16       320   java.lang.Integer
         *    2000000        24  48000000   java.lang.String
         *          1        24        24   java.util.ArrayList
         *     100000        48   4800000   java.util.HashMap
         *    2000000        32  64000000   java.util.HashMap$Node
         *    6200022           179627200   (total)
         */
        System.out.println(GraphLayout.parseInstance(rows).toFootprint());
    }

    @Test
    public void evaluate_TreeMap()
    {
        List<Map<String, Object>> rows = new ArrayList<>();

        //100000 rows of TreeMap
        for (int i = 0; i < 100000; i++)
        {
            Map<String, Object> map = new TreeMap<>();
            for (int k = 0; k < 20; ++k)
            {
                map.put(k + "", k);
            }

            rows.add(map);
        }

        /**
         * java.util.ArrayList@3b22cdd0d footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *    2000000        24  48000000   [C
         *          1    426856    426856   [Ljava.lang.Object;
         *         20        16       320   java.lang.Integer
         *    2000000        24  48000000   java.lang.String
         *          1        24        24   java.util.ArrayList
         *     100000        48   4800000   java.util.TreeMap
         *    2000000        40  80000000   java.util.TreeMap$Entry
         *    6100022           181227200   (total)
         */
        System.out.println(GraphLayout.parseInstance(rows).toFootprint());
    }

    @Test
    public void evaluate_SmoothieMap()
    {
        List<Map<String, Object>> rows = new ArrayList<>();
        //100000 rows of SmoothieMap
        for (int i = 0; i < 100000; i++)
        {
            Map<String, Object> map = SmoothieMap.<String, Object>newBuilder()
                    .expectedSize(20)
                    .build();;
            for (int k = 0; k < 20; ++k)
            {
                map.put(k + "", k);
            }

            rows.add(map);
        }

        /**
         * java.util.ArrayList@72245a7dd footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *    2000000        24  48000000   [C
         *     100000        24   2400000   [I
         *     100001        28   2826856   [Ljava.lang.Object;
         *     100000       416  41600000   io.timeandspace.smoothie.InterleavedSegments$IntermediateCapacitySegment
         *     100000        64   6400000   io.timeandspace.smoothie.SmoothieMap
         *         20        16       320   java.lang.Integer
         *    2000000        24  48000000   java.lang.String
         *          1        24        24   java.util.ArrayList
         *    4400022           149227200   (total)
         */
        System.out.println(GraphLayout.parseInstance(rows).toFootprint());
    }

    @Test
    public void evaluate_ChronicleMap()
    {
        List<Map<String, Object>> rows = new ArrayList<>();

        //100000 rows of ChronicleMap
        for (int i = 0; i < 100000; i++)
        {
            ChronicleMap<String, Object> map = ChronicleMap
                    .of(String.class, Object.class)
                    .name("test-map")
                    .averageKey("1")
                    .averageValue(1)
                    .entries(20)
                    .create();
            for (int k = 0; k < 20; ++k)
            {
                map.put(k + "", k);
            }

            rows.add(map);
        }

        /**
         * java.util.ArrayList@617e6ac5d footprint:
         *      COUNT       AVG       SUM   DESCRIPTION
         *     500112        72  36008880   [B
         *     302574        82  25051776   [C
         *        240        21      5112   [Ljava.lang.Class;
         *          1       144       144   [Ljava.lang.ClassValue$Entry;
         *     400099        49  19637208   [Ljava.lang.Object;
         *         13        34       448   [Ljava.lang.String;
         *          2        40        80   [Ljava.lang.Thread;
         *          1        32        32   [Ljava.lang.ThreadGroup;
         *          3    349584   1048752   [Ljava.lang.ThreadLocal$ThreadLocalMap$Entry;
         *          1        24        24   [Ljava.lang.annotation.ElementType;
         *          1        32        32   [Ljava.lang.annotation.RetentionPolicy;
         *         16        24       384   [Ljava.lang.reflect.Constructor;
         *        117        43      5048   [Ljava.lang.reflect.Field;
         *         52        36      1920   [Ljava.lang.reflect.Method;
         *          6        24       144   [Ljava.lang.reflect.Parameter;
         *          2        24        48   [Ljava.lang.reflect.TypeVariable;
         *          1        16        16   [Ljava.security.CodeSigner;
         *         17        16       272   [Ljava.security.Principal;
         *          2        32        64   [Ljava.security.ProtectionDomain;
         *          1        16        16   [Ljava.security.cert.Certificate;
         *        109        80      8784   [Ljava.util.HashMap$Node;
         *          2        64       128   [Ljava.util.Hashtable$Entry;
         *         66        80      5280   [Ljava.util.WeakHashMap$Entry;
         *          3      5648     16944   [Ljava.util.concurrent.ConcurrentHashMap$Node;
         *          1        16        16   [Lsun.instrument.TransformerManager$TransformerInfo;
         *          2        20        40   [Lsun.reflect.generics.tree.ClassTypeSignature;
         *          3        24        72   [Lsun.reflect.generics.tree.FieldTypeSignature;
         *          2        24        48   [Lsun.reflect.generics.tree.FormalTypeParameter;
         *          6        17       104   [Lsun.reflect.generics.tree.TypeArgument;
         *          1       384       384   com.intellij.rt.execution.application.AppMainV2$1
         *          1        16        16   com.sun.proxy.$Proxy0
         *          5        16        80   com.sun.proxy.$Proxy1
         *          1        16        16   com.sun.proxy.$Proxy2
         *          1        16        16   com.sun.proxy.$Proxy3
         *          5        16        80   com.sun.proxy.$Proxy4
         *          5        16        80   com.sun.proxy.$Proxy5
         *          2        16        32   com.sun.proxy.$Proxy6
         *          2        32        64   java.io.File
         *         15        32       480   java.io.FilePermission
         *         15        24       360   java.io.FilePermissionCollection
         *          2        16        32   java.lang.Boolean
         *        736       744    547584   java.lang.Class
         *          9        24       216   java.lang.Class$AnnotationData
         *        141        56      7896   java.lang.Class$ReflectionData
         *          1        40        40   java.lang.ClassLoader$NativeLibrary
         *          1        64        64   java.lang.ClassValue$ClassValueMap
         *          2        32        64   java.lang.ClassValue$Entry
         *          1        16        16   java.lang.ClassValue$Identity
         *          1        24        24   java.lang.ClassValue$Version
         *          8        16       128   java.lang.Integer
         *          8        24       192   java.lang.Long
         *       1699        16     27184   java.lang.Object
         *         78        56      4368   java.lang.Package
         *         15        24       360   java.lang.RuntimePermission
         *     102572        24   2461728   java.lang.String
         *     200000        24   4800000   java.lang.StringBuilder
         *          2        32        64   java.lang.StringCoding$StringDecoder
         *          3        32        96   java.lang.StringCoding$StringEncoder
         *          5       376      1880   java.lang.Thread
         *          2        48        96   java.lang.ThreadGroup
         *     100004        16   1600064   java.lang.ThreadLocal
         *          3        24        72   java.lang.ThreadLocal$ThreadLocalMap
         *     100009        32   3200288   java.lang.ThreadLocal$ThreadLocalMap$Entry
         *          1        16        16   java.lang.UNIXProcess$$Lambda$96/766706901
         *          1        24        24   java.lang.annotation.ElementType
         *          3        24        72   java.lang.annotation.RetentionPolicy
         *          1       384       384   java.lang.ref.Finalizer$FinalizerThread
         *          1       376       376   java.lang.ref.Reference$ReferenceHandler
         *         67        32      2144   java.lang.ref.ReferenceQueue
         *         69        16      1104   java.lang.ref.ReferenceQueue$Lock
         *          2        32        64   java.lang.ref.ReferenceQueue$Null
         *        160        40      6400   java.lang.ref.SoftReference
         *     100000        32   3200000   java.lang.ref.WeakReference
         *         30        80      2400   java.lang.reflect.Constructor
         *        983        72     70776   java.lang.reflect.Field
         *        194        88     17072   java.lang.reflect.Method
         *          6        40       240   java.lang.reflect.Parameter
         *        166        64     10624   java.net.URL
         *          2        64       128   java.nio.DirectByteBuffer
         *     500000        48  24000000   java.nio.HeapByteBuffer
         *          2        16        32   java.nio.charset.CodingErrorAction
         *         10        40       400   java.security.AccessControlContext
         *         15        32       480   java.security.BasicPermissionCollection
         *         17        32       544   java.security.CodeSource
         *         15        32       480   java.security.Permissions
         *         17        40       680   java.security.ProtectionDomain
         *         17        16       272   java.security.ProtectionDomain$Key
         *         63        24      1512   java.util.ArrayDeque
         *     500028        24  12000672   java.util.ArrayList
         *          1        24        24   java.util.Collections$EmptyMap
         *          2        24        48   java.util.Collections$SynchronizedSet
         *        102        48      4896   java.util.HashMap
         *          3        16        48   java.util.HashMap$EntrySet
         *        435        32     13920   java.util.HashMap$Node
         *          3        16        48   java.util.HashSet
         *          2        48        96   java.util.Hashtable
         *         34        56      1904   java.util.LinkedHashMap
         *         48        40      1920   java.util.LinkedHashMap$Entry
         *          4        16        64   java.util.LinkedHashMap$LinkedValues
         *          8        32       256   java.util.LinkedList
         *         12        24       288   java.util.LinkedList$Node
         *          2        32        64   java.util.Stack
         *          4        32       128   java.util.Vector
         *         65        48      3120   java.util.WeakHashMap
         *         11        40       440   java.util.WeakHashMap$Entry
         *          4        64       256   java.util.concurrent.ConcurrentHashMap
         *       1764        32     56448   java.util.concurrent.ConcurrentHashMap$Node
         *          1        32        32   java.util.concurrent.SynchronousQueue
         *          1        16        16   java.util.concurrent.SynchronousQueue$TransferStack
         *          1        32        32   java.util.concurrent.SynchronousQueue$TransferStack$SNode
         *          1        80        80   java.util.concurrent.ThreadPoolExecutor
         *          1        16        16   java.util.concurrent.ThreadPoolExecutor$AbortPolicy
         *          1        48        48   java.util.concurrent.ThreadPoolExecutor$Worker
         *          1        16        16   java.util.concurrent.atomic.AtomicInteger
         *          1        24        24   java.util.concurrent.atomic.AtomicLong
         *          2        24        48   java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject
         *          1        32        32   java.util.concurrent.locks.AbstractQueuedSynchronizer$Node
         *          1        16        16   java.util.concurrent.locks.ReentrantLock
         *          2        32        64   java.util.concurrent.locks.ReentrantLock$NonfairSync
         *         14        16       224   java.util.jar.Attributes
         *        150        24      3600   java.util.jar.Attributes$Name
         *         61        64      3904   java.util.jar.JarFile
         *         55        96      5280   java.util.jar.JarFile$JarFileEntry
         *          1        88        88   java.util.jar.JarVerifier
         *          1        16        16   java.util.jar.JarVerifier$3
         *         14        24       336   java.util.jar.Manifest
         *         56        48      2688   java.util.zip.Inflater
         *         56        24      1344   java.util.zip.ZStreamRef
         *         63        32      2016   java.util.zip.ZipCoder
         *     100000        32   3200000   net.openhft.chronicle.algo.bitset.ReusableBitSet
         *     100000        24   2400000   net.openhft.chronicle.algo.bitset.SingleThreadedFlatBitSetFrame
         *          1        16        16   net.openhft.chronicle.algo.bytes.NativeAccess
         *     500000        48  24000000   net.openhft.chronicle.bytes.HeapBytesStore
         *     500000        72  36000000   net.openhft.chronicle.bytes.NativeBytes
         *     100000        72   7200000   net.openhft.chronicle.bytes.NativeBytesStore
         *          1        24        24   net.openhft.chronicle.bytes.NoBytesStore
         *     100000        72   7200000   net.openhft.chronicle.bytes.PointerBytesStore
         *     300000        16   4800000   net.openhft.chronicle.bytes.StreamingInputStream
         *     300000        16   4800000   net.openhft.chronicle.bytes.StreamingOutputStream
         *     400000        64  25600000   net.openhft.chronicle.bytes.VanillaBytes
         *          1        16        16   net.openhft.chronicle.core.UnsafeMemory
         *    1400000        16  22400000   net.openhft.chronicle.core.io.AbstractReferenceCounted$$Lambda$7/120960120
         *     200000        16   3200000   net.openhft.chronicle.core.io.AbstractReferenceCounted$$Lambda$87/249155636
         *          1        16        16   net.openhft.chronicle.core.io.BackgroundResourceReleaser$$Lambda$6/55909012
         *    1600000        24  38400000   net.openhft.chronicle.core.io.VanillaReferenceCounted
         *     100000        24   2400000   net.openhft.chronicle.hash.VanillaGlobalMutableState$$Native
         *     100000        16   1600000   net.openhft.chronicle.hash.impl.ContextHolder
         *          1        16        16   net.openhft.chronicle.hash.impl.HashSplitting$ForSingleSegment
         *     100000        32   3200000   net.openhft.chronicle.hash.impl.InMemoryChronicleHashResources
         *     100000        48   4800000   net.openhft.chronicle.hash.impl.IntCompactOffHeapLinearHashTable
         *     100000        32   3200000   net.openhft.chronicle.hash.impl.MemoryResource
         *     100000        16   1600000   net.openhft.chronicle.hash.impl.VanillaChronicleHash$Identity
         *          1        24        24   net.openhft.chronicle.hash.impl.stage.entry.NoChecksumStrategy
         *     100000        16   1600000   net.openhft.chronicle.hash.impl.util.CleanerUtils$$Lambda$88/242355057
         *     300000        32   9600000   net.openhft.chronicle.hash.serialization.impl.SerializableDataAccess
         *     100000        16   1600000   net.openhft.chronicle.hash.serialization.impl.SerializableReader
         *          1        16        16   net.openhft.chronicle.hash.serialization.impl.StopBitSizeMarshaller
         *     200000        16   3200000   net.openhft.chronicle.hash.serialization.impl.StringSizedReader
         *     200000        24   4800000   net.openhft.chronicle.hash.serialization.impl.StringUtf8DataAccess
         *          1        16        16   net.openhft.chronicle.map.DefaultSpi
         *     100000       336  33600000   net.openhft.chronicle.map.VanillaChronicleMap
         *     100000       320  32000000   net.openhft.chronicle.map.impl.CompiledMapQueryContext
         *     100000        16   1600000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$AcquireHandle
         *     100000        24   2400000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$DefaultReturnValue
         *     100000        24   2400000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$DummyValueZeroData
         *     100000        24   2400000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$EntryKeyBytesData
         *     100000        24   2400000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$EntryValueBytesData
         *     100000        16   1600000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$HashEntryChecksumStrategy
         *     100000        48   4800000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$InputKeyBytesData
         *     100000        16   1600000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$ReadLock
         *     100000        16   1600000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$UpdateLock
         *     100000        24   2400000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$UsingReturnValue
         *     100000        56   5600000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$WrappedValueBytesData
         *     100000        32   3200000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$WrappedValueInstanceDataHolder
         *     100000        16   1600000   net.openhft.chronicle.map.impl.CompiledMapQueryContext$WriteLock
         *          1        24        24   net.openhft.chronicle.map.impl.stage.data.ZeroBytesStore
         *          5        96       480   net.openhft.chronicle.values.IntegerFieldModel
         *          5        24       120   net.openhft.chronicle.values.IntegerFieldModel$1
         *          1        40        40   net.openhft.chronicle.values.ValueModel
         *          1        24        24   net.openhft.chronicle.values.ValueModel$1
         *          5        24       120   net.openhft.chronicle.values.ValueModel$FieldData
         *          1        96        96   org.openjdk.jol.vm.HotspotUnsafe
         *          1        24        24   org.openjdk.jol.vm.HotspotUnsafe$1
         *          1        48        48   org.openjdk.jol.vm.HotspotUnsafe$Sizes
         *          1        32        32   sun.instrument.InstrumentationImpl
         *          1        24        24   sun.instrument.TransformerManager
         *     100001        40   4000040   sun.misc.Cleaner
         *          1        16        16   sun.misc.FloatingDecimal$1
         *          1        40        40   sun.misc.FloatingDecimal$BinaryToASCIIBuffer
         *          2        24        48   sun.misc.JarIndex
         *          1        96        96   sun.misc.Launcher$AppClassLoader
         *          1        88        88   sun.misc.Launcher$ExtClassLoader
         *         10        24       240   sun.misc.MetaIndex
         *          1        16        16   sun.misc.Perf
         *          1        24        24   sun.misc.Perf$1
         *          2        48        96   sun.misc.URLClassPath
         *          2        24        48   sun.misc.URLClassPath$FileLoader
         *         81        56      4536   sun.misc.URLClassPath$JarLoader
         *          1        16        16   sun.misc.Unsafe
         *          1        16        16   sun.net.www.protocol.file.Handler
         *          2        16        32   sun.net.www.protocol.jar.Handler
         *          1        16        16   sun.net.www.protocol.jar.JarFileFactory
         *          2        80       160   sun.net.www.protocol.jar.URLJarFile
         *          1        24        24   sun.nio.cs.UTF_8
         *         62        40      2480   sun.nio.cs.UTF_8$Decoder
         *         66        56      3696   sun.nio.cs.UTF_8$Encoder
         *         14        16       224   sun.reflect.DelegatingConstructorAccessorImpl
         *          7        16       112   sun.reflect.DelegatingMethodAccessorImpl
         *          1        16        16   sun.reflect.GeneratedConstructorAccessor1
         *         13        24       312   sun.reflect.NativeConstructorAccessorImpl
         *          7        24       168   sun.reflect.NativeMethodAccessorImpl
         *        134        32      4288   sun.reflect.UnsafeObjectFieldAccessorImpl
         *        100        32      3200   sun.reflect.UnsafeQualifiedObjectFieldAccessorImpl
         *          2        40        80   sun.reflect.UnsafeQualifiedStaticLongFieldAccessorImpl
         *          5        40       200   sun.reflect.UnsafeQualifiedStaticObjectFieldAccessorImpl
         *         20        24       480   sun.reflect.annotation.AnnotationInvocationHandler
         *          7        32       224   sun.reflect.annotation.AnnotationType
         *          2        24        48   sun.reflect.generics.factory.CoreReflectionFactory
         *          3        32        96   sun.reflect.generics.reflectiveObjects.TypeVariableImpl
         *          2        32        64   sun.reflect.generics.repository.ClassRepository
         *          2        24        48   sun.reflect.generics.scope.ClassScope
         *          2        24        48   sun.reflect.generics.tree.ClassSignature
         *          6        16        96   sun.reflect.generics.tree.ClassTypeSignature
         *          3        24        72   sun.reflect.generics.tree.FormalTypeParameter
         *          6        24       144   sun.reflect.generics.tree.SimpleClassTypeSignature
         *          1        16        16   sun.reflect.generics.tree.TypeVariableSignature
         *   11514277           453889504   (total)
         */
        System.out.println(GraphLayout.parseInstance(rows).toFootprint());
    }
}
