package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasLength;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetLengthFunction implements JsFunction {
    public static final String ID = "setLength";

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
        js.append("(id,minLength,maxLength)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        //js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("if(widgetType=='%s'||widgetType=='%s'||widgetType=='%s'||widgetType=='%s'||widgetType=='%s'||widgetType=='%s'){",
                WidgetType.TEXT.name(), WidgetType.SEARCH.name(), WidgetType.URL.name(),
                WidgetType.TELEPHONE.name(), WidgetType.EMAIL.name(), WidgetType.PASSWORD.name()); // if
        js.append("if(minLength!=null){");
        js.append("element.setAttribute('minLength',minLength);");
        js.append("};");
        js.append("if(maxLength!=null){");
        js.append("element.setAttribute('maxLength',maxLength);");
        js.append("};");
        js.append("};"); // end if

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("minLength", JsType.NUMBER));
        parameters.add(JsParameter.getInstance("maxLength", JsType.NUMBER));
    }
}
