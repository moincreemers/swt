package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// todo: not clear how we can do logging in a generic way
public class LogStatement extends MethodStatement {
    final List<ValueStatement> values = new ArrayList<>();

    public LogStatement(String message) {
        this(V.Const(message));
    }

    public LogStatement(ValueStatement... values) {
        this.values.addAll(Arrays.asList(values));
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.info("console.log(");
        int i = 0;
        for (ValueStatement value : values) {
            if (i > 0) {
                js.append(",");
            }
            value.renderJs(toolkit, widget, js);
            i++;
        }
        js.info(");");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (ValueStatement value : values) {
            value.validate(toolkit);
        }
    }
}
