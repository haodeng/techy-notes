package apachecommons.lang;

import org.apache.commons.lang3.JavaVersion;
import org.junit.Test;
import org.apache.commons.lang3.SystemUtils;

public class SystemUtilsTest {

    @Test
    public void test()
    {
        System.out.println(SystemUtils.OS_ARCH);
        System.out.println(SystemUtils.OS_NAME);
        System.out.println(SystemUtils.OS_VERSION);

        System.out.println(SystemUtils.IS_OS_AIX);
        System.out.println(SystemUtils.IS_OS_LINUX);
        System.out.println(SystemUtils.IS_OS_UNIX);
        System.out.println(SystemUtils.IS_OS_MAC);
        System.out.println(SystemUtils.IS_OS_MAC_OSX);
        System.out.println(SystemUtils.IS_OS_MAC_OSX_TIGER);
        System.out.println(SystemUtils.IS_OS_HP_UX);

        System.out.println(SystemUtils.IS_JAVA_1_8);
        System.out.println(SystemUtils.IS_JAVA_9);
        System.out.println(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_0_9));

        System.out.println(SystemUtils.getHostName());
        System.out.println(SystemUtils.getJavaHome());
        System.out.println(SystemUtils.getJavaIoTmpDir());

        System.out.println(SystemUtils.getUserDir());
        System.out.println(SystemUtils.getUserHome());

        System.out.println(SystemUtils.JAVA_VENDOR);
    }
}
