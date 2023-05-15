package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class LetStatement extends MethodStatement {
    final ValueStatement name;
    final ValueStatement value;

    public LetStatement(String name, boolean value) {
        this(V.Const(name), V.Const(value));
    }

    public LetStatement(String name, int value) {
        this(V.Const(name), V.Const(value));
    }

    public LetStatement(String name, long value) {
        this(V.Const(name), V.Const(value));
    }

    public LetStatement(String name, double value) {
        this(V.Const(name), V.Const(value));
    }

    public LetStatement(String name, String value) {
        this(V.Const(name), V.Const(value));
    }

    public LetStatement(ValueStatement name, ValueStatement value) {
        this.name = name;
        this.value = value;
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
        js.append("this[%s]=%s;",
                ValueStatement.valueOf(toolkit, name, widget),
                ValueStatement.valueOf(toolkit, value, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
    }
}
