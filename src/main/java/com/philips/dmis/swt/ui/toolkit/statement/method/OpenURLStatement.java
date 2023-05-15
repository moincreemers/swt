package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class OpenURLStatement extends MethodStatement {
    final ValueStatement url;

    public OpenURLStatement(ValueStatement url) {
        this.url = url;
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
        js.append("window.open(");
        url.renderJs(toolkit, widget, js);
        js.append(",'_blank');");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        url.validate(toolkit);
    }
}
