package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.GlobalEvents;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class GetEvent extends ValueStatement {
    private final ValueStatement property;

    public GetEvent() {
        property = null;
    }

    public GetEvent(ValueStatement property) {
        this.property = property;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) {
        if (property != null) {
            String property = ValueStatement.valueOf(toolkit, this.property, widget);
            js.append("(%s[%s]==undefined)", GlobalEvents.EVENT_PARAMETER_NAME, property);
            js.append("?null");
            js.append(":%s[%s]", GlobalEvents.EVENT_PARAMETER_NAME, property);
        } else {
            js.append(GlobalEvents.EVENT_PARAMETER_NAME);
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
