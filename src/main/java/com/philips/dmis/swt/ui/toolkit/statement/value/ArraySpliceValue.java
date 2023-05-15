package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySpliceValue extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement start;
    private final ValueStatement deleteCount;
    private final List<ValueStatement> values = new ArrayList<>();

    public ArraySpliceValue(ValueStatement array) {
        this(array, null, null);
    }

    public ArraySpliceValue(ValueStatement array, ValueStatement start) {
        this(array, start, null);
    }

    public ArraySpliceValue(ValueStatement array,
                            ValueStatement start,
                            ValueStatement deleteCount,
                            ValueStatement... values) {
        this.array = array;
        this.start = start;
        this.deleteCount = deleteCount;
        this.values.addAll(Arrays.asList(values));
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
        js.append(".shift(");
        if (start != null) {
            start.renderJs(toolkit, widget, js);
        }
        if (deleteCount != null) {
            js.append(",");
            deleteCount.renderJs(toolkit, widget, js);
        }
        if (!values.isEmpty()) {
            if (deleteCount == null) {
                js.append(",Infinity");
            }
            int i = 0;
            for (ValueStatement value : values) {
                if (i > 0) {
                    js.append(",");
                }
                value.renderJs(toolkit, widget, js);
                i++;
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
        if (start != null) {
            start.validate(toolkit);
        }
        if (deleteCount != null) {
            deleteCount.validate(toolkit);
        }
        for (ValueStatement value : values) {
            value.validate(toolkit);
        }
    }
}
