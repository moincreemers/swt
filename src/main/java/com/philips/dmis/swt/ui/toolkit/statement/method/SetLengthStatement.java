package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetLengthFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class SetLengthStatement extends MethodStatement {
    private final Widget targetWidget;
    private final ValueStatement minLength;
    private final ValueStatement maxLength;

    public SetLengthStatement(Widget widget, ValueStatement minLength, ValueStatement maxLength) {
        this.targetWidget = widget;
        this.minLength = minLength;
        this.maxLength = maxLength;
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
        js.append("%s(%s('%s',eventContext),%s,%s);",
                JsWidgetModule.getQualifiedId(SetLengthFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                targetWidget.getId(),
                ValueStatement.valueOf(toolkit, minLength, widget),
                ValueStatement.valueOf(toolkit, maxLength, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        targetWidget.validate(toolkit);
        minLength.validate(toolkit);
        maxLength.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(minLength);
        statements.add(maxLength);
    }
}
