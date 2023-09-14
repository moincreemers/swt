package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InPredicate extends PredicateStatement {
    private final List<ValueStatement> valueStatements = new ArrayList<>();

    public InPredicate(ValueStatement... valueStatements) {
        this.valueStatements.addAll(Arrays.asList(valueStatements));
    }

    @Override
    public JsType getParameterType() {
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(value)=>{return [");
        int i = 0;
        for (ValueStatement valueStatement : valueStatements) {
            if (i > 0) {
                js.append(",");
            }
            valueStatement.renderJs(toolkit, widget, js);
        }
        js.append("].includes(value);}");
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
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.addAll(valueStatements);
    }
}
