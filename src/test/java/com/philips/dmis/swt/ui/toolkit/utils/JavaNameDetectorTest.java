package com.philips.dmis.swt.ui.toolkit.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaNameDetectorTest {
    static class AutoNamingClass {
        final String name;

        public AutoNamingClass() {
            name = JavaNameDetector.detectDeclaredName(getClass(), "test");
        }
    }

    @Test
    public void testName() {
        AutoNamingClass thisIsMyName = new AutoNamingClass();
        Assertions.assertEquals("thisIsMyName", thisIsMyName.name);
    }

    @Test
    public void testName2() {
        AutoNamingClass thisIsMyName = (new AutoNamingClass());
        Assertions.assertEquals("thisIsMyName", thisIsMyName.name);
    }

    @Test
    public void testName3() {
        AutoNamingClass thisIsMyName;
        thisIsMyName = (new AutoNamingClass());
        Assertions.assertEquals("thisIsMyName", thisIsMyName.name);
    }
}
