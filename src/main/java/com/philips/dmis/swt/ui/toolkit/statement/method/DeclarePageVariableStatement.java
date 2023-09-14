package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.controller.GetCurrentPageFunction;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.DeclarePageVariableFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Declares a variable that is scoped to the current page")
public class DeclarePageVariableStatement extends MethodStatement {
    private final ValueStatement name;

    public DeclarePageVariableStatement(ValueStatement name) {
        this.name = name;
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
        js.append("%s(%s(),%s);",
                JsWidgetModule.getQualifiedId(DeclarePageVariableFunction.class),
                JsPageControllerModule.getQualifiedId(GetCurrentPageFunction.class),
                ValueStatement.valueOf(toolkit, name, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertRequiredAndReturnType("name", name, JsType.STRING);
        name.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(name);
    }
}
