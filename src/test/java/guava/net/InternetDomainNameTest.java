package guava.net;

import com.google.common.net.InternetDomainName;
import org.junit.Assert;
import org.junit.Test;

public class InternetDomainNameTest {
    @Test
    public void test() {
        InternetDomainName owner =InternetDomainName.from("foo.blogspot.com");
        Assert.assertEquals("blogspot.com", owner.topDomainUnderRegistrySuffix().toString());
        Assert.assertEquals("foo.blogspot.com", owner.topPrivateDomain().toString());
        Assert.assertEquals("com", owner.registrySuffix().toString());
        Assert.assertEquals("blogspot.com", owner.publicSuffix().toString());
    }
}
