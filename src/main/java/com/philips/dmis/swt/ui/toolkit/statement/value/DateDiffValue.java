package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class DateDiffValue extends ValueStatement {
    private final ValueStatement date0;
    private final ValueStatement date1;
    private final TimeIntervalType timeIntervalType;

    public DateDiffValue(ValueStatement date0, ValueStatement date1, TimeIntervalType timeIntervalType) {
        this.date0 = date0;
        this.date1 = date1;
        this.timeIntervalType = timeIntervalType;
    }

    @Override
    public JsType getType() {
        return JsType.NUMBER;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        js.append("(");
        date1.renderJs(toolkit, widget, js);
        js.append("-");
        date0.renderJs(toolkit, widget, js);
        js.append(")");
        js.append("/%d", timeIntervalType.getMillisPerUnit());
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        date0.validate(toolkit);
        date1.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(date0);
        statements.add(date1);
    }
}
