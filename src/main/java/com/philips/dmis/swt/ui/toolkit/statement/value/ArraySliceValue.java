package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class ArraySliceValue extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement start;
    private final ValueStatement end;

    public ArraySliceValue(ValueStatement array) {
        this(array, null, null);
    }

    public ArraySliceValue(ValueStatement array, ValueStatement start) {
        this(array, start, null);
    }

    public ArraySliceValue(ValueStatement array, ValueStatement start, ValueStatement end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
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
        if (end != null) {
            js.append(",");
            end.renderJs(toolkit, widget, js);
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
        if (end != null) {
            end.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(array);
        statements.add(start);
        statements.add(end);
    }
}
