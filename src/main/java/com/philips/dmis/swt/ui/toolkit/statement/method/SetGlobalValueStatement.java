package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SetSessionValueFunction;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.Arrays;
import java.util.List;

public class SetGlobalValueStatement extends MethodStatement {
    final ValueStatement key;
    final ValueStatement value;

    public SetGlobalValueStatement(ValueStatement key, ValueStatement value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return Arrays.asList(
                JsParameter.getInstance("key", JsType.STRING),
                JsParameter.getInstance("value", JsType.STRING)
        );
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s('global_'+%s,%s);",
                JsGlobalModule.getQualifiedId(SetSessionValueFunction.class),
                ValueStatement.valueOf(toolkit, key, widget),
                ValueStatement.valueOf(toolkit, value, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        key.validate(toolkit);
        value.validate(toolkit);
    }
}
