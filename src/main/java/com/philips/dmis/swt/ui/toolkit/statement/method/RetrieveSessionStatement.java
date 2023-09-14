package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.Arrays;
import java.util.List;

@Description("Retrieves the provided key from Session Storage and returns that value or returns a provided default value or null")
public class RetrieveSessionStatement extends MethodStatement {
    final ValueStatement key;
    final ValueStatement defaultValue;

    public RetrieveSessionStatement(ValueStatement key, ValueStatement defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return Arrays.asList(JsParameter.getInstance("key", JsType.OBJECT),
                JsParameter.getInstance("defaultValue", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("const value=sessionStorage.getItem(%s);",
                ValueStatement.valueOf(toolkit, key, widget));
        String defaultValue = this.defaultValue != null ?
                ValueStatement.valueOf(toolkit, this.defaultValue, widget) :
                "null";
        js.append("return value!=null?value:%s;", defaultValue);
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertRequiredAndReturnType("key", key, JsType.STRING);
        key.validate(toolkit);
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
