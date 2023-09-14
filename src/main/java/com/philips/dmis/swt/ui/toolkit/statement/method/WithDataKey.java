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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Description("Invokes one or more statements but changes the current event context to the provided data key value first")
public class WithDataKey extends MethodStatement {
    final ValueStatement dataKey;
    final List<Statement> statements = new ArrayList<>();

    public WithDataKey(ValueStatement dataKey, Statement... statements) {
        this.dataKey = dataKey;
        this.statements.addAll(Arrays.asList(statements));
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
        js.append("const originalDataKey=eventContext.dataKey;");
        js.append("eventContext.dataKey=%s;", ValueStatement.valueOf(toolkit, dataKey, widget));
        for (Statement statement : statements) {
            statement.renderJs(toolkit, widget, js);
        }
        js.append("eventContext.dataKey=originalDataKey;");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertRequiredAndReturnType("dataKey", dataKey, JsType.STRING);
        dataKey.validate(toolkit);
        for (Statement statement : statements) {
            statement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(dataKey);
        statements.addAll(this.statements);
    }
}
