package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class ContainsPredicate extends PredicateStatement {
    private final ValueStatement valueStatement;

    public ContainsPredicate(ValueStatement valueStatement) {
        this.valueStatement = valueStatement;
    }

    @Override
    public JsType getParameterType() {
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(value)=>{return value.indexOf('");
        valueStatement.renderJs(toolkit, widget, js);
        js.append("')>-1;}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (valueStatement.getType() != JsType.STRING) {
            throw new WidgetConfigurationException("expected STRING");
        }
        valueStatement.validate(toolkit);
    }
}
