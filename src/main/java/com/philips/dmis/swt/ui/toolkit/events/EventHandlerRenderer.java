package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.GlobalEvents;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.controller.GetCurrentPageFunction;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public final class EventHandlerRenderer {
    private static int id = 0;

    private EventHandlerRenderer() {
    }

    public static void renderHandlers(List<EventHandler> eventHandlers, Toolkit toolkit, Widget widget, JsWriter js, String eventHandlerName, CustomEvent customEvent) {
        renderHandlers(eventHandlers, toolkit, widget, js, eventHandlerName, CustomEvent.valueOf(customEvent));
    }

    public static void renderHandlers(List<EventHandler> eventHandlers, Toolkit toolkit, Widget widget, JsWriter js, String eventHandlerName, String event) {
        String fn = "handlers" + (id++);
        js.append("const %s=(%s)=>{", fn, GlobalEvents.EVENT_PARAMETER_NAME);
        js.append("const currentPageId=%s();", JsPageControllerModule.getQualifiedId(GetCurrentPageFunction.class));
        js.append("const eventSourceId=(%s.srcElement==undefined||%s.srcElement==null)?'':%s.srcElement.id;",
                GlobalEvents.EVENT_PARAMETER_NAME,
                GlobalEvents.EVENT_PARAMETER_NAME,
                GlobalEvents.EVENT_PARAMETER_NAME);
        eventHandlers.stream().filter(eventHandler -> eventHandlerName.equals(eventHandler.getName())).forEach((eventHandler) -> {
                    // if pageId not empty then check if equals current page
                    // if widgetId not empty then check if event source equals widgetId
                    js.append("var ehPageId='%s';", eventHandler.getPageId());
                    js.append("var ehWidgetId='%s';", eventHandler.getWidgetId());
                    js.debug("console.log('event handler filter',currentPageId,'=',ehPageId, eventSourceId,'=',ehWidgetId);");
                    js.append("if((ehPageId==''||ehPageId==currentPageId)" +
                            "&&(eventSourceId==''||ehWidgetId==''||ehWidgetId==eventSourceId)){");
                    js.debug("console.log('event handler filter pass');");
                    for (Statement statement : eventHandler.getStatements()) {
                        statement.renderJs(toolkit, widget, js);
                    }
                    js.append("};");
                }
        );
        js.append("};");
        js.append("%s(%s);", fn, event);
    }
}
