package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class DateModifyValue extends ValueStatement {
    private final ValueStatement date;
    private final ValueStatement value;

    public DateModifyValue(ValueStatement date, ValueStatement value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public JsType getType() {
        return JsType.DATE;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
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
}
