package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Description("Encapsulates one or more statements inside a try-catch-finally block")
public class TryStatement extends MethodStatement {
    private final List<Statement> statements = new ArrayList<>();
    private final List<Statement> catchStatements = new ArrayList<>();
    private final List<Statement> finallyStatements = new ArrayList<>();

    public TryStatement(Statement... statements) {
        this.statements.addAll(Arrays.asList(statements));
    }

    public TryStatement Catch(Statement... statements) {
        catchStatements.addAll(Arrays.asList(statements));
        return this;
    }

    public TryStatement Finally(Statement... statements) {
        finallyStatements.addAll(Arrays.asList(statements));
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
        js.append("try{");
        for (Statement statement : statements) {
            statement.renderJs(toolkit, widget, js);
        }
        js.append("}");
        js.append("catch(err){");
        for (Statement statement : catchStatements) {
            statement.renderJs(toolkit, widget, js);
        }
        js.append("}");
        js.append("finally{");
        for (Statement statement : finallyStatements) {
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
        StatementUtil.assertSize("statements", statements, 1);
        for (Statement statement : statements) {
            statement.validate(toolkit);
        }
        for (Statement statement : catchStatements) {
            statement.validate(toolkit);
        }
        for (Statement statement : finallyStatements) {
            statement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(this.statements);
        statements.addAll(this.catchStatements);
        statements.addAll(this.finallyStatements);
    }
}
