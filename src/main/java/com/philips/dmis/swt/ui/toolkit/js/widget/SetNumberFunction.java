package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.NumberTextIdVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetNumberFunction implements JsFunction {
    public static final String ID = "setNumber";

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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("text", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,text)=>{");
        js.trace(this);

        js.append("if(text==null||text==undefined||text.length==0){return;};");
        js.append("const widget=window[id];");

        js.append("if(widget.%s=='%s'){", WidgetTypeVariable.ID, WidgetType.HEADING.name());
        js.append("var numberElement=document.getElementById(widget.%s);", NumberTextIdVariable.ID);
        js.append("if(numberElement==null){return;};");
        js.append("numberElement.textContent=text;");
        js.append("};");

        js.append("}"); // end function
    }


}
