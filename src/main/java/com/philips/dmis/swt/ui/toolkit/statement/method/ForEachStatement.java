package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForEachStatement extends MethodStatement {
    private final List<ValueStatement> valueStatements = new ArrayList<>();
    private final List<Statement> statements = new ArrayList<>();

    public ForEachStatement(ValueStatement... valueStatements) {
        this.valueStatements.addAll(Arrays.asList(valueStatements));
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
        if (valueStatements.isEmpty() || statements.isEmpty()) {
            return;
        }
        js.append("const numberOfValues=%d;", valueStatements.size());
        js.append("var items=[];");
        for (ValueStatement valueStatement : valueStatements) {
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
        for (ValueStatement valueStatement : valueStatements) {
            valueStatement.validate(toolkit);
        }
        for (Statement statement : statements) {
            statement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(valueStatements);
        statements.addAll(this.statements);
    }
}
