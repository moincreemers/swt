package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IsValueStatementObjectFunctionTest {
    @Test
    public void testTrue() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                IsObjectFunction.class);
        Object actual = f.apply(Arrays.asList(new Object()));
        Assertions.assertEquals(true, actual);
    }

    @Test
    public void testString() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                IsObjectFunction.class);
        Object actual = f.apply(Arrays.asList("a"));
        Assertions.assertEquals(false, actual);
    }

    @Test
    public void testNumber() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                IsObjectFunction.class);
        Object actual = f.apply(Arrays.asList(1));
        Assertions.assertEquals(false, actual);
    }

    @Test
    public void testBoolean() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                IsObjectFunction.class);
        Object actual = f.apply(Arrays.asList(Boolean.TRUE));
        Assertions.assertEquals(false, actual);
    }

    @Test
    public void testNullIsNotConsideredObject() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                IsObjectFunction.class);

        List<Object> args = new ArrayList<>();
        args.add(null);

        Object actual = f.apply(args);
        Assertions.assertEquals(false, actual);
    }
}
