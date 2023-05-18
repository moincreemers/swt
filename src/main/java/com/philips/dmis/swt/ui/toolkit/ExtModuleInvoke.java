package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.dto.ExtModuleEvent;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;

public class ExtModuleInvoke {
    public static void renderCall(String name, String pageId, String widgetId, JsWriter js) {
        js.append("const event=%s;", DtoUtil.getDefault(ExtModuleEvent.class, false));
        js.append("event.name='%s';", name);
        if (pageId != null) {
            js.append("event.pageId='%s';", pageId);
        }
        if (widgetId != null) {
            js.append("event.widgetId='%s';", widgetId);
        }
        js.append("%s.%s(event);", Constants.MAIN_MODULE_NAME, Constants.EXTERNAL_MODULE_EVENT);
    }

    public static void renderIndirectCall(String name, String pageIdVar, String widgetIdVar, JsWriter js) {
        js.append("const event=%s;", DtoUtil.getDefault(ExtModuleEvent.class, false));
        js.append("event.name='%s';", name);
        if (pageIdVar != null) {
            js.append("event.pageId=%s;", pageIdVar);
        }
        if (widgetIdVar != null) {
            js.append("event.widgetId=%s;", widgetIdVar);
        }
        js.append("%s.%s(event);", Constants.MAIN_MODULE_NAME, Constants.EXTERNAL_MODULE_EVENT);
    }
}
