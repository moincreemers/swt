package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class ArrayFillValue extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement value;
    private final ValueStatement start;
    private final ValueStatement end;

    public ArrayFillValue(ValueStatement array,
                          ValueStatement value) throws WidgetConfigurationException {
        this(array, value, null, null);
    }

    public ArrayFillValue(ValueStatement array,
                          ValueStatement value,
                          ValueStatement start) throws WidgetConfigurationException {
        this(array, value, start, null);
    }

    public ArrayFillValue(ValueStatement array, ValueStatement value,
                          ValueStatement start, ValueStatement end) throws WidgetConfigurationException {
        this.array = array;
        this.value = value;
        this.start = start;
        this.end = end;

        if (array == null) {
            throw new WidgetConfigurationException("array is required");
        }
        if (value == null) {
            throw new WidgetConfigurationException("value is required");
        }
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        array.renderJs(toolkit, widget, js);
        js.append(".fill(");
        value.renderJs(toolkit, widget, js);
        if (start != null) {
            js.append(",");
            start.renderJs(toolkit, widget, js);
            if (end != null) {
                js.append(",");
                end.renderJs(toolkit, widget, js);
            }
        }
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        array.validate(toolkit);
        value.validate(toolkit);
        if (start != null) {
            start.validate(toolkit);
        }
        if (end != null) {
            end.validate(toolkit);
        }
    }
}
