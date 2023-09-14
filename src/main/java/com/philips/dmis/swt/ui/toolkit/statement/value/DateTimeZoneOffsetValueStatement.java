package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns the timezone offset in minutes of the provided Date object")
public class DateTimeZoneOffsetValueStatement extends ValueStatement {
    private final ValueStatement date;

    public DateTimeZoneOffsetValueStatement(ValueStatement date) {
        this.date = date;
    }

    @Override
    public JsType getType() {
        return JsType.NUMBER;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        date.renderJs(toolkit, widget, js);
        js.append(".getTimezoneOffset()");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        date.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(date);
    }
}
