package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.StaticData;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

public class StaticDataVariable implements JsVariable {
    public static final String ID = "staticData";
    private final Widget widget;
    private final WidgetType widgetType;

    public StaticDataVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.STATICDATA;
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
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        StaticData staticData = (StaticData) widget;
        js.append("'%s'", staticData.getUrlEncodedPlusBase64EncodedJson());
    }
}
