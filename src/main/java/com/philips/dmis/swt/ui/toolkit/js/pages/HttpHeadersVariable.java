package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasAbstractURL;
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
        return widget instanceof HasAbstractURL;
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
        writeHttpHeaders(toolkit, widget, (HasAbstractURL) widget, js);
    }

    public static void writeHttpHeaders(Toolkit toolkit, Widget widget, HasAbstractURL hasAbstractURL, JsWriter js) {
        js.append("{");
        int i = 0;
        for (String name : hasAbstractURL.getHttpHeaders().keySet()) {
            if (i > 0) {
                js.append(",");
            }
            js.append("'");
            js.append(name);
            js.append("'");
            js.append(":[");
            List<ValueStatement> values = hasAbstractURL.getHttpHeaders().get(name);
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
