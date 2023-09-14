package com.philips.dmis.swt.ui.toolkit.statement;

import com.philips.dmis.swt.ui.toolkit.TestJsWriter;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.statement.value.ConstantValueStatement;
import org.junit.jupiter.api.Test;

public class ConstantValueStatementTest {
    @Test
    public void checkJs() {
        Toolkit tk = new TestToolkit();
        TestJsWriter js = new TestJsWriter();
        ConstantValueStatement constantValueStatement = new ConstantValueStatement("someValue");
        constantValueStatement.renderJs(tk, null, js);
        js.assertEquals("'someValue'");
    }
}
