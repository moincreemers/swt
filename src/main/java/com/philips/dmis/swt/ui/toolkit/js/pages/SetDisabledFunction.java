package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class SetDisabledFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setDisabled";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetDisabledFunction(Widget widget) {
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(disabled)=>{");

        js.append("const element=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
        if (!(widget instanceof HtmlLink) && (widget instanceof HasValue<?> || widget instanceof IsClickable<?>)) {
            js.append("element.disabled=disabled;");
        } else {
            js.append("if(disabled){");
            js.append("element.setAttribute('disabled','');");
            js.append("}else{");
            js.append("element.removeAttribute('disabled');");
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
