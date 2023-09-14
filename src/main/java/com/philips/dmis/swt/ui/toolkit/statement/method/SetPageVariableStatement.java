package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.controller.GetCurrentPageFunction;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetPageVariableFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Sets the value of a page variable using the provided name, value and operator")
public class SetPageVariableStatement extends MethodStatement {
    private final ValueStatement name;
    private final ValueStatement value;
    private final Operator operator;

    public SetPageVariableStatement(ValueStatement name, ValueStatement value, Operator operator) {
        this.name = name;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s(%s(),%s,%s,'%s');",
                JsWidgetModule.getQualifiedId(SetPageVariableFunction.class),
                JsPageControllerModule.getQualifiedId(GetCurrentPageFunction.class),
                ValueStatement.valueOf(toolkit, name, widget),
                ValueStatement.valueOf(toolkit, value, widget),
                operator.name());
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertRequiredAndReturnType("name", name, JsType.STRING);
        name.validate(toolkit);
        if (value != null) {
            value.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(name);
        if (value != null) {
            statements.add(value);
        }
    }
}
