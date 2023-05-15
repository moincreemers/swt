package com.philips.dmis.swt.ui.toolkit.js;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UNGTest {
    @Test
    public void testAbbr(){
        String name = UNG.abbreviate("toAbcDefGhiJklMnoPqrStuVwxYZ", 100);
        Assertions.assertEquals("tadgjmpsvyz", name);
    }

    @Test
    public void generate(){
        String name = UNG.generate(getClass());
        Assertions.assertEquals("ung", name);
    }

    @Test
    public void unique(){
        String a = UNG.generate(getClass());
        String b = UNG.generate(getClass());
        Assertions.assertEquals("ung", a);
        Assertions.assertNotEquals("ung", b);
    }
}
