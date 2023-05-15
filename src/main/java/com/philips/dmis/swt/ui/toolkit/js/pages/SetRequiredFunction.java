package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasRequired;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetRequiredFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setRequired";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetRequiredFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasRequired;
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
        js.append("(required)=>{");

        if (widgetType.isNot(WidgetType.RANGE, WidgetType.COLOR, WidgetType.BUTTON)) {
            js.append("if(required===true){");
            js.append("%s().setAttribute('required','');",
                    JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
            js.append("if(required===false){");
            js.append("%s().removeAttribute('required');",
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
