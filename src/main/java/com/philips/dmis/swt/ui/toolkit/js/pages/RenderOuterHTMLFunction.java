package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RenderOuterHTMLFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "renderOuterHTML";
    private final Widget widget;
    private final WidgetType widgetType;

    public RenderOuterHTMLFunction(Widget widget) {
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
        return JsType.VOID;
    }


    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(RenderElementFunction.class);
        dependencies.add(GetWidgetSpecFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");
        js.append("%s(%s());",
                JsPagesModule.getId(widget, RenderElementFunction.class),
                JsPagesModule.getId(widget, GetWidgetSpecFunction.class));
        js.append("}");
    }
}