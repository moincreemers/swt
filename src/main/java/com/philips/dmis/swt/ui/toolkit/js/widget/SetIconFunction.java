package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.IconElementIdVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasIcon;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetIconFunction implements JsFunction {
    public static final String ID = "setIcon";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasIcon;
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
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("icon", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,icon)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        //js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("if(implements.includes('%s')){", HasIcon.class.getSimpleName()); // if
        js.append("const iconElement=document.getElementById(widget.%s);", IconElementIdVariable.ID);
        js.append("iconElement.textContent=icon;");
        js.append("element.setAttribute('tk-has-icon',iconElement.textContent.length!=0?'true':'false');");
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
