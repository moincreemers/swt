package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class AtValueStatement extends ValueStatement {
    private final ValueStatement value;
    private final ValueStatement index;

    public AtValueStatement(ValueStatement value, ValueStatement index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        value.renderJs(toolkit, widget, js);
        js.append(".at(;");
        index.renderJs(toolkit, widget, js);
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
        index.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(value);
        statements.add(index);
    }
}
