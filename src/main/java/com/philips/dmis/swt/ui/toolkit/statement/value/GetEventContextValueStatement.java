package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.widget.EventHandlerFunction;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetEventContextValueStatement extends ValueStatement {
    private final ValueStatement property;

    public GetEventContextValueStatement() {
        property = null;
    }

    public GetEventContextValueStatement(ValueStatement property) {
        this.property = property;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        if (property != null) {
            String property = ValueStatement.valueOf(toolkit, this.property, widget);
            js.append("(%s[%s]==undefined)", EventHandlerFunction.EVENTCONTEXT_ARGUMENT, property);
            js.append("?null");
            js.append(":%s[%s]", EventHandlerFunction.EVENTCONTEXT_ARGUMENT, property);
        } else {
            js.append(EventHandlerFunction.EVENTCONTEXT_ARGUMENT);
        }
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
