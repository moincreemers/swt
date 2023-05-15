package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasReadonly;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetReadonlyFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setReadonly";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetReadonlyFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasReadonly;
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
        js.append("(readonly)=>{");

        if (widgetType.isNot(WidgetType.RANGE, WidgetType.COLOR, WidgetType.CHECK, WidgetType.BUTTON)) {
            js.append("if(readonly===true){");
            js.append("%s().setAttribute('readonly','');",
                    JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
            js.append("if(readonly===false){");
            js.append("%s().removeAttribute('readonly');",
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
