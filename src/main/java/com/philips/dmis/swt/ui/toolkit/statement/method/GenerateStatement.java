package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.CloneFunction;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.InitWidgetFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.ContainerWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GenerateStatement extends MethodStatement {
    private final Widget template;
    private final ContainerWidget<?> target;

    public GenerateStatement(Widget template, ContainerWidget<?> target) {
        this.template = template;
        this.target = target;
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
        // generate a new widget, later we can expand to hierarchy

        // requirements:
        // 1. Generate widget-id function is needed
        js.append("window.IdGen=undefined===window.IdGen?0:window.IdGen;");
        js.append("const widgetId='g'+window.IdGen++;");

        // 2. Generate a new module for the widget by calling Clone method on widget
        js.append("const newModule=%s('%s',widgetId,'%s');",
                JsWidgetModule.getQualifiedId(CloneFunction.class),
                template.getId(),
                target.getId()
        );

        // 3. Call InitFunction
        js.append("newModule.%s();", JsStateModule.getId(template, InitWidgetFunction.class));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        template.validate(toolkit);
        target.validate(toolkit);
    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
