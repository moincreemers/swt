package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetRangeFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Sets the range attribute of the provided widget")
public class SetRangeStatement extends MethodStatement {
    private final Widget widget;
    private final ValueStatement min;
    private final ValueStatement max;

    public SetRangeStatement(Widget widget, ValueStatement min, ValueStatement max) {
        this.widget = widget;
        this.min = min;
        this.max = max;
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
                JsWidgetModule.getQualifiedId(SetRangeFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                this.widget.getId(),
                ValueStatement.valueOf(toolkit, min, widget),
                ValueStatement.valueOf(toolkit, max, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("widget", widget);
        widget.validate(toolkit);
        StatementUtil.assertRequired("min", min);
        min.validate(toolkit);
        StatementUtil.assertRequired("max", max);
        max.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(min);
        statements.add(max);
    }
}
