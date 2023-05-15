package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ObjectToSearchFunctionTest {
    public static class SomeObject {
        public String a = "hello";
        public String b = "world";
    }

    @Test
    public void test() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                ObjectToSearchFunction.class);

        Object actual = f.apply(Arrays.asList(new SomeObject()));
        Assertions.assertEquals("a=hello&b=world", actual);
    }
}
