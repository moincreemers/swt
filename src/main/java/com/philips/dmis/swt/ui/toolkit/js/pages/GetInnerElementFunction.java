package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetInnerElementFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "getInner";
    private final Widget widget;
    private final WidgetType widgetType;

    public GetInnerElementFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.PAGE;
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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");

        if (widgetType == WidgetType.PAGE) {
            js.append("const innerDivId='%s';", Page.getInnerDivId(widget.getId()));
            js.append("return document.getElementById(innerDivId);");
        }
        js.throwError("widget does not have inner element");

        js.append("}");
    }


}
