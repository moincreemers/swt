package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Shallow copies part of an array to another location in the same array and returns it without modifying its length")
public class ArrayCopyWithinValueStatement extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement target;
    private final ValueStatement start;
    private final ValueStatement end;

    public ArrayCopyWithinValueStatement(ValueStatement array,
                                         ValueStatement target) throws WidgetConfigurationException {
        this(array, target, null, null);
    }

    public ArrayCopyWithinValueStatement(ValueStatement array,
                                         ValueStatement target,
                                         ValueStatement start) throws WidgetConfigurationException {
        this(array, target, start, null);
    }

    public ArrayCopyWithinValueStatement(ValueStatement array,
                                         ValueStatement target,
                                         ValueStatement start,
                                         ValueStatement end) throws WidgetConfigurationException {
        this.array = array;
        this.target = target;
        this.start = start;
        this.end = end;

        if (array == null) {
            throw new WidgetConfigurationException("array is required");
        }
        if (target == null) {
            throw new WidgetConfigurationException("target is required");
        }
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        array.renderJs(toolkit, widget, js);
        js.append(".copyWithin(");
        target.renderJs(toolkit, widget, js);
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
        target.validate(toolkit);
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
        statements.add(target);
        if (start != null) {
            statements.add(start);
        }
        if (end != null) {
            statements.add(end);
        }
    }
}
