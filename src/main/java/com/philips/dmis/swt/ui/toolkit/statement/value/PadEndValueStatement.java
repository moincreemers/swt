package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class PadEndValueStatement extends ValueStatement {
    private final ValueStatement value;
    private final ValueStatement maxLength;
    private final ValueStatement fillString;

    public PadEndValueStatement(ValueStatement value, ValueStatement maxLength) {
        this(value, maxLength, null);
    }

    public PadEndValueStatement(ValueStatement value, ValueStatement maxLength, ValueStatement fillString) {
        this.value = value;
        this.maxLength = maxLength;
        this.fillString = fillString;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        value.renderJs(toolkit, widget, js);
        js.append(".padEnd(");
        value.renderJs(toolkit, widget, js);
        js.append(",");
        maxLength.renderJs(toolkit, widget, js);
        if (fillString != null) {
            js.append(",");
            fillString.renderJs(toolkit, widget, js);
        }
        js.append(")");
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        value.validate(toolkit);
        maxLength.validate(toolkit);
        if (fillString != null) {
            fillString.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(value);
        statements.add(maxLength);
        if (fillString != null) {
            statements.add(fillString);
        }
    }
}
