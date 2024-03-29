package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class StringSplitValueStatement extends ValueStatement {
    private final ValueStatement string;
    private final ValueStatement splitter;
    private final ValueStatement limit;

    public StringSplitValueStatement(ValueStatement string, ValueStatement splitter) {
        this(string, splitter, null);
    }

    public StringSplitValueStatement(ValueStatement string, ValueStatement splitter, ValueStatement limit) {
        this.string = string;
        this.splitter = splitter;
        this.limit = limit;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        string.renderJs(toolkit, widget, js);
        js.append(".split(");
        splitter.renderJs(toolkit, widget, js);
        if (limit != null) {
            js.append(",");
            limit.renderJs(toolkit, widget, js);
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
        splitter.validate(toolkit);
        if (limit != null) {
            limit.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(string);
        statements.add(splitter);
        if (limit != null) {
            statements.add(limit);
        }
    }
}
