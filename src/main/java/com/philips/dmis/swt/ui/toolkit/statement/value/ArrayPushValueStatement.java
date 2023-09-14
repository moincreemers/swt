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

@Description("Adds one or more elements to the end of the array and returns the new length")
public class ArrayPushValueStatement extends ValueStatement {
    private final ValueStatement array;
    private List<ValueStatement> values = new ArrayList<>();

    public ArrayPushValueStatement(ValueStatement array, ValueStatement... values) {
        this.array = array;
        this.values.addAll(Arrays.asList(values));
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        array.renderJs(toolkit, widget, js);
        js.append(".push(");
        int i = 0;
        for (ValueStatement value : values) {
            if (i > 0) {
                js.append(",");
            }
            value.renderJs(toolkit, widget, js);
        }
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (ValueStatement valueStatement : values) {
            valueStatement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(array);
        statements.addAll(values);
    }
}
