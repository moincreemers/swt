package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class RandomValue extends ValueStatement {
    private final int DEFAULT_MAX = Integer.MAX_VALUE;
    final ValueStatement max;

    public RandomValue() {
        this(null);
    }

    public RandomValue(ValueStatement max) {
        this.max = max;
    }

    @Override
    public JsType getType() {
        return JsType.NUMBER;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
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
