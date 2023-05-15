package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasLength;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetLengthFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setLength";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetLengthFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasLength;
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
        js.append("(minLength,maxLength)=>{");

        if (widgetType.isOneOf(WidgetType.TEXT, WidgetType.SEARCH, WidgetType.URL,
                WidgetType.TELEPHONE, WidgetType.EMAIL, WidgetType.PASSWORD)) {
            js.append("if(minLength!=null){");
            js.append("%s().setAttribute('minLength',minLength);", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
            js.append("if(maxLength!=null){");
            js.append("%s().setAttribute('maxLength',maxLength);", JsPagesModule.getId(widget, GetElementFunction.class));
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
