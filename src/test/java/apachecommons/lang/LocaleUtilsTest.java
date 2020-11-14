package apachecommons.lang;

import org.apache.commons.lang3.LocaleUtils;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class LocaleUtilsTest {

    @Test
    public void test()
    {
        LocaleUtils.availableLocaleList().forEach(System.out::println);

        assertEquals("da_DK", LocaleUtils.countriesByLanguage("da").get(0).toString());
        assertEquals("da_DK", LocaleUtils.languagesByCountry("DK").get(0).toString());

        assertTrue(LocaleUtils.isAvailableLocale(Locale.US));
        assertFalse(LocaleUtils.isAvailableLocale(new Locale("a", "b", "c")));
    }
}
