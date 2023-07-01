package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SearchToObjectFunction implements JsFunction {
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
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(search)=>{");

        js.append("if(search==undefined||search==null){return {};};");
        js.append("if(search.charAt(0)=='?'||search.charAt(0)=='#'){search=search.substring(1);};");
        js.append("const sp=new URLSearchParams(search);");
        js.append("const keys=new Set(sp.keys());");
        js.append("const obj={};");
        js.append("keys.forEach((k)=>obj[k]=sp.getAll(k));");
        js.append("return obj;");

        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("search", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
