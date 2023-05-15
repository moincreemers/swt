package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import org.junit.jupiter.api.Assertions;

public class TestJsWriter extends JsWriter {
    public void assertEquals(String expected) {
        Assertions.assertEquals(expected, toString());
    }
}
