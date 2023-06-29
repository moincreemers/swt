package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.EqualsFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Allows for evaluation of a single condition and perform multiple statements if the condition is true.
 */
public class IifStatement extends MethodStatement {
    static class Case {
        final ValueStatement valueStatement;
        final boolean inverted;
        final List<Statement> statements = new ArrayList<>();

        Case(ValueStatement valueStatement, Statement... statements) {
            this(valueStatement, false, statements);
        }

        Case(ValueStatement valueStatement, boolean inverted, Statement... statements) {
            this.valueStatement = valueStatement;
            this.inverted = inverted;
            Collections.addAll(this.statements, statements);
        }
    }

    private final ValueStatement valueStatement;
    private final List<Case> cases = new ArrayList<>();
    private final List<Statement> elseStatements = new ArrayList<>();

    public IifStatement(ValueStatement valueStatement) {
        this.valueStatement = valueStatement;
    }

    public IifStatement True(Statement... statements) {
        return Equals(V.Const(JsGlobalModule.TRUE), statements);
    }

    public IifStatement False(Statement... statements) {
        return Equals(V.Const(JsGlobalModule.FALSE), statements);
    }

    public IifStatement Zero(Statement... statements) {
        return Equals(V.Const(JsGlobalModule.ZERO), statements);
    }

    public IifStatement NotZero(Statement... statements) {
        return NotEquals(V.Const(JsGlobalModule.ZERO), statements);
    }

    public IifStatement Empty(Statement... statements) {
        return Equals(V.Const(JsGlobalModule.EMPTY), statements);
    }

    public IifStatement NotEmpty(Statement... statements) {
        return NotEquals(V.Const(JsGlobalModule.EMPTY), statements);
    }

    private IifStatement Equals(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(valueStatement, statements));
        return this;
    }

    private IifStatement NotEquals(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(valueStatement, true, statements));
        return this;
    }

    public IifStatement Else(Statement... statements) {
        Collections.addAll(elseStatements, statements);
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
        int i = 0;
        for (Case c : cases) {
            if (i > 0) {
                js.append("}else ");
            }
            js.append("if(%s(%s,%s)==%s){", JsGlobalModule.getQualifiedId(EqualsFunction.class),
                    ValueStatement.valueOf(toolkit, valueStatement, widget),
                    ValueStatement.valueOf(toolkit, c.valueStatement, widget),
                    c.inverted ? "false" : "true");
            for (Statement statement : c.statements) {
                statement.renderJs(toolkit, widget, js);
            }
            i++;
        }
        if (!elseStatements.isEmpty()) {
            js.append("}else{");
            for (Statement statement : elseStatements) {
                statement.renderJs(toolkit, widget, js);
            }
        }
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        valueStatement.validate(toolkit);
        for (Case c : cases) {
            c.valueStatement.validate(toolkit);
            for (Statement statement : c.statements) {
                statement.validate(toolkit);
            }
        }
        for (Statement elseStatement : elseStatements) {
            elseStatement.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        for (Case c : cases) {
            statements.add(c.valueStatement);
            statements.addAll(c.statements);
        }
        statements.addAll(elseStatements);
    }
}
