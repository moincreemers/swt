package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LessThanOrEqualsFunctionTest {
    @Test
    public void testGtTrue() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                LessThanOrEqualsFunction.class);
        Object actual = f.apply(Arrays.asList(1, 2));
        Assertions.assertEquals(true, actual);
    }

    @Test
    public void testEqTrue() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                LessThanOrEqualsFunction.class);
        Object actual = f.apply(Arrays.asList(2, 2));
        Assertions.assertEquals(true, actual);
    }

    @Test
    public void testGtFalse() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                LessThanOrEqualsFunction.class);
        Object actual = f.apply(Arrays.asList(2, 1));
        Assertions.assertEquals(false, actual);
    }
}
