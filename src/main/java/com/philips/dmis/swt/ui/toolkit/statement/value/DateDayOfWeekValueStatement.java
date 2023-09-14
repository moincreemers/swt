package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns the day of the week of the provided Date object")
public class DateDayOfWeekValueStatement extends ValueStatement {
    private final ValueStatement date;
    private final boolean utc;

    public DateDayOfWeekValueStatement(ValueStatement date, boolean utc) {
        this.date = date;
        this.utc = utc;
    }

    @Override
    public JsType getType() {
        return JsType.NUMBER;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        date.renderJs(toolkit, widget, js);
        js.append(utc ? ".getUTCDay()" : ".getDay()");
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
