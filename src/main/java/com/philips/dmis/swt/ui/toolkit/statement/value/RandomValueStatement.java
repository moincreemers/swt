package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns a random value")
public class RandomValueStatement extends ValueStatement {
    private final int DEFAULT_MAX = Integer.MAX_VALUE;
    final ValueStatement max;

    public RandomValueStatement() {
        this(null);
    }

    public RandomValueStatement(ValueStatement max) {
        this.max = max;
    }

    @Override
    public JsType getType() {
        return JsType.NUMBER;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("Math.floor(Math.random()*%s)", max != null ?
                ValueStatement.valueOf(toolkit, max, widget)
                : Integer.valueOf(DEFAULT_MAX).toString());
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (max != null) {
            max.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        if (max != null) {
            statements.add(max);
        }
    }
}
