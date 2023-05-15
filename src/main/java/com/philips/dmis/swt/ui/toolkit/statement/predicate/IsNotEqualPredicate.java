package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class IsNotEqualPredicate extends PredicateStatement {
    private final ValueStatement valueStatement;

    public IsNotEqualPredicate(ValueStatement valueStatement) {
        this.valueStatement = valueStatement;
    }

    @Override
    public JsType getParameterType() {
        return valueStatement.getType();
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(value)=>{return value!=");
        if (valueStatement.getType() == JsType.STRING) {
            js.append("'");
        }
        valueStatement.renderJs(toolkit, widget, js);
        if (valueStatement.getType() == JsType.STRING) {
            js.append("'");
        }
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
}
