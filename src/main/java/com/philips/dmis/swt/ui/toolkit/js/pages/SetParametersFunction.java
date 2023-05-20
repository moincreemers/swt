package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasURL;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetParametersFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setParameters";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetParametersFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasURL;
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

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(obj)=>{");

        js.append("if(obj==undefined||obj==null||typeof obj!='object'){return;};");

        js.append("for(const key in obj){");
        js.append("var value=obj[key];");
        js.append("if(value==null){continue;};");
        js.append("%s[key]=value;", JsPagesModule.getId(widget, ParametersVariable.class));
        js.append("};");

        js.append("}"); // end function
    }
}
