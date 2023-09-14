package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.GetSessionValueFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns a named value from the current session")
public class GetGlobalValueStatement extends ValueStatement {
    final ValueStatement key;
    final ValueStatement defaultValue;

    public GetGlobalValueStatement(ValueStatement key) {
        this(key, V.Const(null, JsType.NULL));
    }

    public GetGlobalValueStatement(ValueStatement key, ValueStatement defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s('global_'+%s,%s)",
                JsGlobalModule.getQualifiedId(GetSessionValueFunction.class),
                ValueStatement.valueOf(toolkit, key, widget),
                ValueStatement.valueOf(toolkit, defaultValue, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        key.validate(toolkit);
        defaultValue.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(key);
        statements.add(defaultValue);
    }
}
