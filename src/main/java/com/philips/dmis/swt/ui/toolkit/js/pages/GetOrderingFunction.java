package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOrderingControls;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetOrderingFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "getOrdering";
    private final Widget widget;
    private final WidgetType widgetType;

    public GetOrderingFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasOrderingControls<?>;
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
        // todo

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");
        js.append("return %s;", JsPagesModule.getId(widget, OrderingVariable.class));
        js.append("}");
    }
}
