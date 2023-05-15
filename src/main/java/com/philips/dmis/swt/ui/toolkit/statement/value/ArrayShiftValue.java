package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class ArrayShiftValue extends ValueStatement {
    private final ValueStatement array;

    public ArrayShiftValue(ValueStatement array) {
        this.array = array;
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
        js.append(".shift()");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        array.validate(toolkit);
    }
}
