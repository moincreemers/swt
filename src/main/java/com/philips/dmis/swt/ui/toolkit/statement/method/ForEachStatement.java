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

@Description("Executes one or more statements for-each of the provided values")
public class ForEachStatement extends MethodStatement {
    private final List<ValueStatement> values = new ArrayList<>();
    private final List<Statement> statements = new ArrayList<>();

    public ForEachStatement(ValueStatement... values) {
        this.values.addAll(Arrays.asList(values));
    }

    public ForEachStatement Apply(Statement... statements) {
        this.statements.addAll(Arrays.asList(statements));
        return this;
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
        if (values.isEmpty() || statements.isEmpty()) {
            return;
        }
        js.append("const numberOfValues=%d;", values.size());
        js.append("var items=[];");
        for (ValueStatement valueStatement : values) {
            js.append("var v=%s;", ValueStatement.valueOf(toolkit, valueStatement, widget));
            js.append("if(numberOfValues==1&&Array.isArray(v)){");
            js.append("items=items.concat(v);");
            js.append("}else if(numberOfValues==1&&(typeof v=='object')){");
            js.append("items=v;");
            js.append("}else{");
            js.append("items.push(v);");
            js.append("};");
        }
        js.debug("console.log('foreach', items);");
        js.append("for(const key in items){");
        js.append("var value=items[key];");
        for (Statement statement : statements) {
            statement.renderJs(toolkit, widget, js);
        }
        js.append("};");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertSize("values", values, 1);
        for (ValueStatement valueStatement : values) {
            valueStatement.validate(toolkit);
        }
        StatementUtil.assertSize("statements", statements, 1);
        for (Statement statement : statements) {
            statement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(values);
        statements.addAll(this.statements);
    }
}
