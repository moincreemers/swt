package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class IsGreaterThanOrEqualPredicate extends PredicateStatement {
    private final ValueStatement valueStatement;

    public IsGreaterThanOrEqualPredicate(ValueStatement valueStatement) {
        this.valueStatement = valueStatement;
    }

    @Override
    public JsType getParameterType() {
        return valueStatement.getType();
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(value)=>{return value>=");
        valueStatement.renderJs(toolkit, widget, js);
        js.append(";}");
    }


    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        valueStatement.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(valueStatement);
    }
}
