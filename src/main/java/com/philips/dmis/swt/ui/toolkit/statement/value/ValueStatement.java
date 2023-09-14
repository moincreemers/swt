package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

/**
 * A Value statement is a value literal.
 * <p>
 * The statement MUST NOT use the function form, i.e. <code>()=>{}</code>.
 * </p>
 * <p>
 * Note that if the value is a string, the statement must ensure the string value is enclosed
 * using single quotes.
 * </p>
 */
public abstract class ValueStatement extends Statement {
    public static String valueOf(Toolkit toolkit, ValueStatement valueStatement, Widget widget) {
        if (valueStatement == null) {
            return "null";
        }
        JsWriter js = new JsWriter();
        valueStatement.renderJs(toolkit, widget, js);
        return js.toString();
    }

    @Override
    public final List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }
}