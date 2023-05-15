package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EqualsFunctionTest {
    @Test
    public void testTrue() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                EqualsFunction.class);
        Object actual = f.apply(Arrays.asList("a", "a"));
        Assertions.assertEquals(true, actual);
    }

    @Test
    public void testFalse() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                EqualsFunction.class);
        Object actual = f.apply(Arrays.asList("a", "b"));
        Assertions.assertEquals(false, actual);
    }
}
