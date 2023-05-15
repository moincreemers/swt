package com.philips.dmis.swt.ui.toolkit.statement;

import com.philips.dmis.swt.ui.toolkit.TestJsWriter;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.statement.value.ConstantValue;
import org.junit.jupiter.api.Test;

public class ConstantValueTest {
    @Test
    public void checkJs() {
        Toolkit tk = new TestToolkit();
        TestJsWriter js = new TestJsWriter();
        ConstantValue constantValue = new ConstantValue("someValue");
        constantValue.renderJs(tk, null, js);
        js.assertEquals("'someValue'");
    }
}
