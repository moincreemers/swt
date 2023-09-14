package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.GetElementFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.SubstituteFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

@Description("Returns a boolean indicating whether the provided widget is displayed")
public class IsDisplayedValueStatement extends ValueStatement {
    private final Widget targetWidget;

    public IsDisplayedValueStatement(Widget widget) {
        this.targetWidget = widget;
    }

    @Override
    public JsType getType() {
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(%s(%s('%s',eventContext)).style.display!='none')",
                JsWidgetModule.getQualifiedId(GetElementFunction.class),
                JsWidgetModule.getQualifiedId(SubstituteFunction.class),
                targetWidget.getId());
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        targetWidget.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
