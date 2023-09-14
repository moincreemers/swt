package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class StringSubstrValueStatement extends ValueStatement {
    private final ValueStatement string;
    private final ValueStatement from;
    private final ValueStatement end;

    public StringSubstrValueStatement(ValueStatement string, ValueStatement from) {
        this(string, from, null);
    }

    public StringSubstrValueStatement(ValueStatement string, ValueStatement from, ValueStatement end) {
        this.string = string;
        this.from = from;
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
        js.append(".substr(");
        from.renderJs(toolkit, widget, js);
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
        from.validate(toolkit);
        if (end != null) {
            end.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(string);
        statements.add(from);
        if (end != null) {
            statements.add(end);
        }
    }
}
