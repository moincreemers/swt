package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class IsFocusedFunction implements JsFunction {
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
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(id)=>{");

        js.append("var elem=document.activeElement;");
        js.append("var sanity=20;");
        js.append("while(sanity>0&&elem!=null&&elem.tagName!='BODY'){");
        js.append("if(id==elem.id){");
        js.append("return true;");
        js.append("};");
        js.append("elem=elem.parentElement;");
        js.append("sanity--;");
        js.append("};"); // end while
        js.append("return false;");

        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
