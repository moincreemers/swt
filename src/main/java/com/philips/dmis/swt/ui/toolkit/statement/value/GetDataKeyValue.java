package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetDataKeyValue extends ValueStatement {
    private final ValueStatement defaultValue;

    public GetDataKeyValue() {
        this(null);
    }

    public GetDataKeyValue(ValueStatement defaultValue) {
        this.defaultValue = defaultValue;
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
        String defaultValue = this.defaultValue != null
                ? ValueStatement.valueOf(toolkit, this.defaultValue, widget)
                : "null";
        js.append("(eventContext.dataKey!=null?eventContext.dataKey:%s)",
                defaultValue);
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (defaultValue != null) {
            defaultValue.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        if (defaultValue != null) {
            statements.add(defaultValue);
        }
    }
}
