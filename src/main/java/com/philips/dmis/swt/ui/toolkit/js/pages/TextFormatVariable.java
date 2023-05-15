package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

public class TextFormatVariable implements JsVariable, IsPageModuleMember {
    public static final String ID = "textFormat";
    private final Widget widget;
    private final WidgetType widgetType;

    public TextFormatVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasText;
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
        //if (widget instanceof HasText) {
        // todo: should not all HasText have the text format property?
        if (widget instanceof HtmlPreformatted) {
            HtmlPreformatted htmlPreformatted = (HtmlPreformatted) widget;
            js.append("'%s'", htmlPreformatted.getTextFormat().name());
        } else {
            js.append("''");
        }
        //}
    }
}
