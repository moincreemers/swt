package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

/**
 * A method statement is any executable line of code.
 */
public abstract class MethodStatement extends Statement {
    static String valueOf(Toolkit toolkit, MethodStatement valueStatement, Widget widget) {
        if (valueStatement == null) {
            return "null";
        }
        JsWriter js = new JsWriter();
        valueStatement.renderJs(toolkit, widget, js);
        return js.toString();
    }
}