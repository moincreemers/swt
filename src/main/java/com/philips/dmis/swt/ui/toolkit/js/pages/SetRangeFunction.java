package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasRange;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetRangeFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setRange";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetRangeFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasRange<?>;
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(min,max)=>{");

        if (widgetType.isOneOf(WidgetType.DATE, WidgetType.MONTH, WidgetType.WEEK, WidgetType.TIME, WidgetType.DATETIME, WidgetType.NUMBER, WidgetType.RANGE)) {
            js.append("if(min!=null){");
            js.append("%s().setAttribute('min',min);", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
            js.append("if(max!=null){");
            js.append("%s().setAttribute('max',max);", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
        }

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }
}
