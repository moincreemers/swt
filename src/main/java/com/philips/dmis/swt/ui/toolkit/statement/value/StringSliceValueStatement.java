package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class StringSliceValueStatement extends ValueStatement {
    private final ValueStatement string;
    private final ValueStatement start;
    private final ValueStatement end;

    public StringSliceValueStatement(ValueStatement string, ValueStatement start) {
        this(string, start, null);
    }

    public StringSliceValueStatement(ValueStatement string, ValueStatement start, ValueStatement end) {
        this.string = string;
        this.start = start;
        this.end = end;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        string.renderJs(toolkit, widget, js);
        js.append(".slice(");
        start.renderJs(toolkit, widget, js);
        js.append(",");
        start.renderJs(toolkit, widget, js);
        if (end != null) {
            js.append(",");
            end.renderJs(toolkit, widget, js);
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
        string.validate(toolkit);
        start.validate(toolkit);
        if (end != null) {
            end.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(string);
        statements.add(start);
        if (end != null) {
            statements.add(end);
        }
    }
}
