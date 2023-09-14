package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class StringReplaceValueStatement extends ValueStatement {
    private final ValueStatement string;
    private final ValueStatement search;
    private final ValueStatement value;

    public StringReplaceValueStatement(ValueStatement string, ValueStatement search, ValueStatement value) {
        this.string = string;
        this.search = search;
        this.value = value;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        string.renderJs(toolkit, widget, js);
        js.append(".replace(");
        search.renderJs(toolkit, widget, js);
        js.append(",");
        value.renderJs(toolkit, widget, js);
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
        search.validate(toolkit);
        value.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(string);
        statements.add(search);
        statements.add(value);
    }
}
