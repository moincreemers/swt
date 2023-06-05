package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class SetDisabledFunction implements JsFunction {
    public static final String ID = "setDisabled";

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
        js.append("(id,disabled)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");
        js.append("if(!implements.includes('%s')&&(implements.includes('%s')||implements.includes('%s'))){",
                HtmlLink.class.getSimpleName(),
                HasValue.class.getSimpleName(),
                IsClickable.class.getSimpleName()); // if
        js.append("element.disabled=disabled;");
        js.append("}else{"); // else
        js.append("if(disabled){");
        js.append("element.setAttribute('disabled','');");
        js.append("}else{");
        js.append("element.removeAttribute('disabled');");
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
        parameters.add(JsParameter.getInstance("disabled", JsType.BOOLEAN));
    }
}
