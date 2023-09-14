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

@Description("Returns true if and only if all the provided values are true")
public class AndValueStatement extends ValueStatement {
    private final List<ValueStatement> values = new ArrayList<>();

    public AndValueStatement(ValueStatement... values) {
        this.values.addAll(Arrays.asList(values));
    }

    @Override
    public JsType getType() {
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        if (values.isEmpty()) {
            js.append("false");
        } else {
            js.append("(");
            int i = 0;
            for (ValueStatement value : values) {
                if (i > 0) {
                    js.append("&&");
                }
                value.renderJs(toolkit, widget, js);
                i++;
            }
            js.append(")");
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (ValueStatement value : values) {
            value.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(values);
    }
}
