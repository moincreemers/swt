package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.HasURL;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Parameter;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;


public class ParametersVariable implements JsVariable {
    public static final String ID = "parameters";
    private final Widget widget;
    private final WidgetType widgetType;

    public ParametersVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasURL;
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public String getPublicName(String id) {
        return id;
    }

    @Override
    public JsType getType() {
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("{");
        int i = 0;
        HasURL hasURL = (HasURL) widget;
        for (Parameter parameter : hasURL.getParameters()) {
            if (i > 0) {
                js.append(",");
            }
            js.append("'%s':", parameter.getName());
            js.append("{");
            String defaultValue = parameter.getDefaultValue();
            defaultValue = defaultValue == null ? "null" : "'" + defaultValue + "'";
            js.append("defaultValue:%s,", defaultValue);
            js.append("value:%s", defaultValue);
            js.append("}");
            i++;
        }
        js.append("}");
    }
}
