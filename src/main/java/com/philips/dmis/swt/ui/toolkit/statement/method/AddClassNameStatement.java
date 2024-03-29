package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.AddClassNameFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.StatementUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Adds a CSS class name to an element")
public class AddClassNameStatement extends MethodStatement {
    private final Widget targetWidget;
    private final ValueStatement className;

    public AddClassNameStatement(Widget targetWidget, ValueStatement className) {
        this.targetWidget = targetWidget;
        this.className = className;
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
                JsGlobalModule.getQualifiedId(AddClassNameFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                targetWidget.getId(),
                ValueStatement.valueOf(toolkit, className, widget));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        StatementUtil.assertWidget("targetWidget", targetWidget);
        targetWidget.validate(toolkit);
        StatementUtil.assertRequiredAndReturnType("className", className, JsType.STRING);
        className.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(className);
    }
}
