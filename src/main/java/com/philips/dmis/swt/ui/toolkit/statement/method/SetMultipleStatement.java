package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetMultipleFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Sets the multiple attribute of the provided widget")
public class SetMultipleStatement extends MethodStatement {
    private final Widget widget;
    private final ValueStatement multiple;

    public SetMultipleStatement(Widget widget, ValueStatement multiple) {
        this.widget = widget;
        this.multiple = multiple;
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
        js.append("%s(%s('%s',eventContext),%s);",
                JsWidgetModule.getQualifiedId(SetMultipleFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                this.widget.getId(),
                ValueStatement.valueOf(toolkit, multiple, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("widget", widget);
        widget.validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("multiple", multiple, JsType.BOOLEAN);
        multiple.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(multiple);
    }
}
