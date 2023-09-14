package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetParameterFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasURL;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Sets one parameter of the provided data source widget")
public class SetQueryParameterStatement extends MethodStatement {
    private final HasURL hasURL;
    private final ValueStatement name;
    private final ValueStatement value;

    public SetQueryParameterStatement(HasURL hasURL, ValueStatement name, ValueStatement value) {
        this.hasURL = hasURL;
        this.name = name;
        this.value = value;
    }

    @Override
    public JsType getType() {
        return JsType.VOID;
    }

    @Override
    public List<JsParameter> getParameters() {
        return Statement.NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("%s(%s('%s',eventContext),%s,%s);",
                JsWidgetModule.getQualifiedId(SetParameterFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                hasURL.asWidget().getId(),
                ValueStatement.valueOf(toolkit, name, widget),
                ValueStatement.valueOf(toolkit, value, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("hasURL", hasURL);
        hasURL.asWidget().validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("name", name, JsType.STRING);
        name.validate(toolkit);
        StatementUtil.assertRequired("value", value);
        value.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(name);
        statements.add(value);
    }
}
