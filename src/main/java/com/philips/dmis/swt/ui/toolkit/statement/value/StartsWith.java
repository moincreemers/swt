package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class StartsWith extends ValueStatement {
    private final ValueStatement left;
    private final ValueStatement right;

    public StartsWith(ValueStatement left, ValueStatement right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public JsType getType() {
        return JsType.BOOLEAN;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        left.renderJs(toolkit, widget, js);
        js.append(".startsWith(");
        right.renderJs(toolkit, widget, js);
        js.append(")");
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        left.validate(toolkit);
        right.validate(toolkit);
    }
}
