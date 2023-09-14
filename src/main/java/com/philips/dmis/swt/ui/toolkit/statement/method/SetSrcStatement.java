package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SetValueFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasSrc;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Sets the src attribute of the provided widget")
public class SetSrcStatement extends MethodStatement {
    private final HasSrc<?> widget;
    private final ValueStatement src;

    public SetSrcStatement(HasSrc<?> widget, ValueStatement src) {
        this.widget = widget;
        this.src = src;
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
                JsWidgetModule.getQualifiedId(SetValueFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                this.widget.asWidget().getId(),
                ValueStatement.valueOf(toolkit, src, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("widget", widget);
        widget.asWidget().validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("src", src, JsType.STRING);
        src.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(src);
    }
}
