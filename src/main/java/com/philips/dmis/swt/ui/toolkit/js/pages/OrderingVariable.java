package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOrderingControls;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.Map;

public class OrderingVariable implements JsVariable, IsPageModuleMember {
    public static final String ID = "ordering";
    private final Widget widget;
    private final WidgetType widgetType;

    public OrderingVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasOrderingControls<?>;
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
        HasOrderingControls<?> hasOrderingControls = (HasOrderingControls<?>) widget;
        Map<String, Order> ordering = hasOrderingControls.getOrder();
        if (ordering.isEmpty()) {
            js.append("[]");
        } else {
            js.append("[");
            int i = 0;
            for (String source : ordering.keySet()) {
                Order order = ordering.get(source);
                if (order == Order.NONE) {
                    continue;
                }
                if (i != 0) {
                    js.append(",");
                }

                js.append("{");
                js.append("source:'%s',", source);
                js.append("order:'%s'", order.name());
                js.append("}");
                i++;
            }
            js.append("]");
        }
    }
}
