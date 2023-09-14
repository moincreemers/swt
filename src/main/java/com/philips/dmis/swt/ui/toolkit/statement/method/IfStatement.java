package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsFunction;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.js.widget.GetFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.ValueWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.*;

/**
 * Allows for evaluation of multiple conditions (i.e., "cases") and either break at the first condition that is true or
 * evaluate all conditions that are true. Comparable to a switch statement.
 */
@Description("Given a ValueWidget, iterates over one or more 'cases' and executes the statements for any case that matches")
public class IfStatement extends MethodStatement {
    static class Case {
        final Class<? extends JsFunction> functionClass;
        final ValueStatement valueStatement;
        final List<Statement> statements = new ArrayList<>();

        Case(Class<? extends JsFunction> functionClass, ValueStatement valueStatement, Statement... statements) {
            this.functionClass = functionClass;
            this.valueStatement = valueStatement;
            Collections.addAll(this.statements, statements);
        }
    }

    private final ValueWidget<?, ?, ?> widget;
    private final boolean breakOnMatch;
    private final List<Case> cases = new ArrayList<>();
    private final List<Statement> elseStatements = new ArrayList<>();

    public IfStatement(ValueWidget<?, ?, ?> widget) {
        this(widget, true);
    }

    public IfStatement(ValueWidget<?, ?, ?> widget, boolean breakOnMatch) {
        this.widget = widget;
        this.breakOnMatch = breakOnMatch;
    }

    public IfStatement True(Statement... statements) {
        return Equals(V.True, statements);
    }

    public IfStatement False(Statement... statements) {
        return Equals(V.False, statements);
    }

    public IfStatement Zero(Statement... statements) {
        return Equals(V.Zero, statements);
    }

    public IfStatement Equals(String value, Statement... statements) {
        return Equals(V.Const(value), statements);
    }

    public IfStatement Equals(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(EqualsFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement NotEquals(String value, Statement... statements) {
        return NotEquals(V.Const(value), statements);
    }

    public IfStatement NotEquals(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(NotEqualsFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement GreaterThan(String value, Statement... statements) {
        return GreaterThan(V.Const(value), statements);
    }

    public IfStatement GreaterThan(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(GreaterThanFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement GreaterThanOrEquals(String value, Statement... statements) {
        return GreaterThanOrEquals(V.Const(value), statements);
    }

    public IfStatement GreaterThanOrEquals(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(GreaterThanOrEqualsFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement LessThan(String value, Statement... statements) {
        return LessThan(V.Const(value), statements);
    }

    public IfStatement LessThan(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(LessThanFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement LessThanOrEquals(String value, Statement... statements) {
        return LessThanOrEquals(V.Const(value), statements);
    }

    public IfStatement LessThanOrEquals(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(LessThanOrEqualsFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement Contains(String value, Statement... statements) {
        return Contains(V.Const(value), statements);
    }

    public IfStatement Contains(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(ContainsFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement Empty(Statement... statements) {
        cases.add(new Case(EqualsFunction.class, V.Const(""), statements));
        return this;
    }

    public IfStatement NotEmpty(Statement... statements) {
        cases.add(new Case(NotEqualsFunction.class, V.Const(""), statements));
        return this;
    }

    public IfStatement Match(ValueStatement valueStatement, Statement... statements) {
        cases.add(new Case(MatchFunction.class, valueStatement, statements));
        return this;
    }

    public IfStatement Else(Statement... statements) {
        elseStatements.addAll(Arrays.asList(statements));
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
        // todo
        js.append("var value=%s('%s');",
                JsWidgetModule.getQualifiedId(GetFunction.class),
                this.widget.getId());

        js.append("var done=false;");
        int i = 0;
        for (Case c : cases) {
            String value = ValueStatement.valueOf(toolkit, c.valueStatement, widget);
            js.debug("console.log('if',value,'%s',%s);", c.functionClass.getSimpleName(), value);
            js.append("if(!done&&%s(value,%s)){",
                    JsGlobalModule.getQualifiedId(c.functionClass), value); // if
            js.debug("console.log('case %d is true');", i);
            for (Statement statement : c.statements) {
                statement.renderJs(toolkit, widget, js);
            }
            if (breakOnMatch) {
                js.append("done=true;");
            }
            js.append("};"); // end if
            i++;
        }
        if (!elseStatements.isEmpty()) {
            js.append("if(!done){");
            for (Statement statement : elseStatements) {
                statement.renderJs(toolkit, widget, js);
            }
            js.append("};");
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("widget", widget);
        widget.validate(toolkit);
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
