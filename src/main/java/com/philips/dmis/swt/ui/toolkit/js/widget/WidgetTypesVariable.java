package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

public class WidgetTypesVariable implements JsVariable {
    public static final String ID = "widgetTypes";

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
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        int i = 0;
        js.append("{");
        for (WidgetType widgetType : WidgetType.values()) {
            if (i > 0) {
                js.append(",");
            }
            js.append("%s:", widgetType.name());
            js.append("{");
            js.append("name:'%s',", widgetType.name());
            js.append("className:'%s',", widgetType.getClassName());
            js.append("container:%s,", widgetType.isContainer() ? "true" : "false");
            js.append("hidden:'%s',", widgetType.isHidden() ? "true" : "false");
            js.append("htmlTag:'%s'", widgetType.getHtmlTag());
            js.append("}");
            i++;
        }
        js.append("}");
    }
}
