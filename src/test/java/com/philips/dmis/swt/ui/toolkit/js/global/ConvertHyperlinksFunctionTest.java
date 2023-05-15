package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ConvertHyperlinksFunctionTest {
    private static final String TEXT = "text https://example.com text";
    private static final String TEXT_EXP = "text <a href='https://example.com'>https://example.com</a> text";

    @Test
    public void test() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(), new JsGlobalModuleDependencyResolver(),
                ConvertHyperlinksFunction.class);
        Object actual = f.apply(Arrays.asList(TEXT));
        Assertions.assertEquals(TEXT_EXP, actual);
    }
}
