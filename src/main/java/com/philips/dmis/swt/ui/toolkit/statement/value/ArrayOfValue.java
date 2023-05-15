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

public class ArrayOfValue extends ValueStatement {
    private List<ValueStatement> valueStatements = new ArrayList<>();

    public ArrayOfValue(ValueStatement... valueStatements) {
        this.valueStatements.addAll(Arrays.asList(valueStatements));
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
        js.append("[");
        int i = 0;
        for (ValueStatement valueStatement : valueStatements) {
            if (i > 0) {
                js.append(",");
            }
            valueStatement.renderJs(toolkit, widget, js);
            i++;
        }
        js.append("]");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (ValueStatement valueStatement : valueStatements) {
            valueStatement.validate(toolkit);
        }
    }
}
