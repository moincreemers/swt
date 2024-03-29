package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Description("Removes or replaces existing elements and/or adds new elements to the array")
public class ArraySpliceValueStatement extends ValueStatement {
    private final ValueStatement array;
    private final ValueStatement start;
    private final ValueStatement deleteCount;
    private final List<ValueStatement> values = new ArrayList<>();

    public ArraySpliceValueStatement(ValueStatement array) {
        this(array, null, null);
    }

    public ArraySpliceValueStatement(ValueStatement array, ValueStatement start) {
        this(array, start, null);
    }

    public ArraySpliceValueStatement(ValueStatement array,
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

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(array);
        if (start != null) {
            statements.add(start);
        }
        if (deleteCount != null) {
            statements.add(deleteCount);
        }
        statements.addAll(values);
    }
}
