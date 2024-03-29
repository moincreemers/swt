package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns a new Date object that is the sum of the provided Date object and the provided timestamp")
public class DateModifyValueStatement extends ValueStatement {
    private final ValueStatement date;
    private final ValueStatement value;

    public DateModifyValueStatement(ValueStatement date, ValueStatement value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public JsType getType() {
        return JsType.DATE;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("new Date(");
        js.append("new Date(");
        date.renderJs(toolkit, widget, js);
        js.append(").getTime()+");
        value.renderJs(toolkit, widget, js);
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        date.validate(toolkit);
        value.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(date);
        statements.add(value);
    }
}
