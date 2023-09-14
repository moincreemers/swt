package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.GetValueFromObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.EventHandlerFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetEventValueStatement extends ValueStatement {
    private final ValueStatement property;

    public GetEventValueStatement() {
        property = null;
    }

    public GetEventValueStatement(ValueStatement property) {
        this.property = property;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        js.append("(");
        if (property != null) {
            String property = ValueStatement.valueOf(toolkit, this.property, widget);
            js.append("%s==null", EventHandlerFunction.EVENTCONTEXT_ARGUMENT);
            js.append("?null");
            js.append(":(%s.domEvent==null", EventHandlerFunction.EVENTCONTEXT_ARGUMENT);
            js.append("?(%s.arguments==null?null:%s(%s.arguments,%s))",
                    EventHandlerFunction.EVENTCONTEXT_ARGUMENT,
                    JsGlobalModule.getQualifiedId(GetValueFromObjectFunction.class),
                    EventHandlerFunction.EVENTCONTEXT_ARGUMENT,
                    property);
            js.append(":%s(%s.domEvent,%s))",
                    JsGlobalModule.getQualifiedId(GetValueFromObjectFunction.class),
                    EventHandlerFunction.EVENTCONTEXT_ARGUMENT,
                    property);
        } else {
            js.append("%s==null", EventHandlerFunction.EVENTCONTEXT_ARGUMENT);
            js.append("?null");
            js.append(":%s.domEvent==null", EventHandlerFunction.EVENTCONTEXT_ARGUMENT);
            js.append("?(%s.arguments==null?null:%s.arguments)", EventHandlerFunction.EVENTCONTEXT_ARGUMENT, EventHandlerFunction.EVENTCONTEXT_ARGUMENT);
            js.append(":%s.domEvent", EventHandlerFunction.EVENTCONTEXT_ARGUMENT);
        }
        js.append(")");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        if (property != null) {
            property.validate(toolkit);
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        if (property != null) {
            statements.add(property);
        }
    }
}
