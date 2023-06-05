package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.ContainerWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

public class ChildWidgetsVariable implements JsVariable {
    public static final String ID = "childWidgets";
    private final Widget widget;
    private final WidgetType widgetType;

    public ChildWidgetsVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof ContainerWidget;
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
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        ContainerWidget<?> containerWidget = (ContainerWidget<?>) widget;
        js.append("[");
        int i = 0;
        for (Widget childWidget : containerWidget) {
            if (i > 0) {
                js.append(",");
            }
            js.append("'%s'", childWidget.getId());
            i++;
        }
        js.append("]");
    }
}
