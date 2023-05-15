package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasURL;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class HttpHeadersVariable implements JsVariable, IsPageModuleMember {
    public static final String ID = "httpHeaders";
    private final Widget widget;
    private final WidgetType widgetType;

    public HttpHeadersVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasURL;
    }

    @Override
    public boolean isPublic() {
        return false;
    }

    @Override
    public String getPublicName(String id) {
        return id;
    }

    @Override
    public JsType getType() {
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        HasURL hasURL = (HasURL) widget;
        js.append("{");
        int i = 0;
        for (String name : hasURL.getHttpHeaders().keySet()) {
            if (i > 0) {
                js.append(",");
            }
            js.append(name);
            js.append(":[");
            List<ValueStatement> values = hasURL.getHttpHeaders().get(name);
            int j = 0;
            for (ValueStatement value : values) {
                if (j > 0) {
                    js.append(",");
                }
                value.renderJs(toolkit, widget, js);
                j++;
            }
            js.append("]");
            i++;
        }
        js.append("}");
    }
}
