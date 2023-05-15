package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.UpdateService;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

public class ContentTypeVariable implements JsVariable, IsPageModuleMember {
    public static final String ID = "contentType";
    private final Widget widget;
    private final WidgetType widgetType;

    public ContentTypeVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.UPDATE_SERVICE;
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
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        if (widget instanceof UpdateService) {
            UpdateService updateService = (UpdateService) widget;
            js.append("'%s'", updateService.getContentType());
        } else {
            js.append("''");
        }
    }
}
