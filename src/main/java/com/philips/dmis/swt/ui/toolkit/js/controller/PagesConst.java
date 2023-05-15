package com.philips.dmis.swt.ui.toolkit.js.controller;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsConstant;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

public class PagesConst implements JsConstant {
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
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("[]");
    }
}
