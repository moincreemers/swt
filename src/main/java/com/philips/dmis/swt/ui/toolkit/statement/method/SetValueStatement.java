package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.pages.JsPagesModule;
import com.philips.dmis.swt.ui.toolkit.js.pages.SetValueFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasValue;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class SetValueStatement extends MethodStatement {
    private final HasValue<?> targetWidget;
    private final ValueStatement valueStatement;

    public SetValueStatement(HasValue<?> widget, ValueStatement valueStatement) {
        this.targetWidget = widget;
        this.valueStatement = valueStatement;
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
        js.debug("console.log('SetValue',%s);", ValueStatement.valueOf(toolkit, valueStatement, widget));
        js.append("%s(%s);",
                JsPagesModule.getQualifiedId(targetWidget.asWidget(), SetValueFunction.class),
                ValueStatement.valueOf(toolkit, valueStatement, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        targetWidget.asWidget().validate(toolkit);
        valueStatement.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(valueStatement);
    }
}
