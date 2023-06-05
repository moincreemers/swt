package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetParameterFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasURL;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class SetQueryParameterStatement extends MethodStatement {
    private final HasURL hasURL;
    private final ValueStatement nameStatement;
    private final ValueStatement valueStatement;

    public SetQueryParameterStatement(HasURL hasURL, ValueStatement nameStatement, ValueStatement valueStatement) {
        this.hasURL = hasURL;
        this.nameStatement = nameStatement;
        this.valueStatement = valueStatement;
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
        js.append("%s('%s',%s,%s);",
                JsWidgetModule.getQualifiedId(SetParameterFunction.class),
                hasURL.asWidget().getId(),
                ValueStatement.valueOf(toolkit, nameStatement, widget),
                ValueStatement.valueOf(toolkit, valueStatement, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        hasURL.asWidget().validate(toolkit);
        nameStatement.validate(toolkit);
        valueStatement.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(nameStatement);
        statements.add(valueStatement);
    }
}
