package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.LinkedHashMap;
import java.util.Map;

public class HtmlAttributesVariable implements JsVariable {
    public static final String ID = "htmlAttributes";
    private final Widget widget;
    private final WidgetType widgetType;

    public HtmlAttributesVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
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
        return JsType.VOID;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        Map<String, String> attributes = new LinkedHashMap<>();
        widget.getHtmlAttributes(attributes);
        js.append("{");
        int i = 0;
        for (String key : attributes.keySet()) {
            String val = attributes.get(key);
            if (i > 0) {
                js.append(",");
            }
            js.append("'");
            js.append(key);
            if (val == null) {
                js.append("null");
            } else {
                js.append("':'");
                js.append(val+"'");
            }
            i++;
        }
        js.append("}");
    }
}
