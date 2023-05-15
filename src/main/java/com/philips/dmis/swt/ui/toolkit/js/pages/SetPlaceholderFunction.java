package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasPlaceholder;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetPlaceholderFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setPlaceholder";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetPlaceholderFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasPlaceholder;
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
        js.append("(placeholder)=>{");

        // text, search, url, tel, email, password, number

        if (widgetType.isOneOf(WidgetType.TEXT, WidgetType.SEARCH, WidgetType.URL, WidgetType.TELEPHONE, WidgetType.EMAIL, WidgetType.PASSWORD, WidgetType.NUMBER)) {
            js.append("if(placeholder!=null){");
            js.append("%s().setAttribute('placeholder',placeholder);",
                    JsPagesModule.getId(widget, GetElementFunction.class));
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
