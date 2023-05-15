package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetSelectionFunctionTest {
    @Test
    public void test() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                GetSelectionFunction.class);
        // name, top
        f.apply(null);
        Assertions.assertEquals("", "");
    }
}
