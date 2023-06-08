package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.EventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

public class EventHandlersVariable implements JsVariable {
    public static final String ID = "eventHandlers";
    private final Widget widget;
    private final WidgetType widgetType;

    public EventHandlersVariable(Widget widget) {
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
        return JsType.NUMBER;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("{");
        int i = 0;
        for (EventHandler eventHandler : widget.getEventHandlers()) {
            if (eventHandler.getStatements().isEmpty()) {
                continue;
            }
            if (i > 0) {
                js.append(",");
            }
            js.append("%s:[", eventHandler.getName()); // array
            js.append("{pageId:'%s',widgetId:'%s',fn:",
                    eventHandler.getPageId(), eventHandler.getWidgetId()); // object
            js.append("(eventContext)=>{"); // function
            for (Statement statement : eventHandler.getStatements()) {
                statement.renderJs(toolkit, widget, js);
            }
            js.append("}"); // end function
            js.append("}"); // end object
            js.append("]"); // end array

            i++;
        }
        js.append("}");
    }
}
