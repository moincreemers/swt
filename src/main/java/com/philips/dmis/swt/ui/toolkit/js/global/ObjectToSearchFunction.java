package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class ObjectToSearchFunction implements JsFunction {
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
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(obj)=>{");
        js.append("if(obj==undefined){return '';};");
        js.append("var sp=new URLSearchParams();");
        js.append("for(const k in obj){"); // for
        js.append("var v=obj[k];");
        js.append("if(v==null){continue;};");
        js.append("if(Array.isArray(v)){");
        js.append("for(const i in v){");
        js.append("sp.append(k,v[i]);");
        js.append("};");
        js.append("}else{");
        js.append("sp.append(k,v);");
        js.append("};");
        js.append("};");
        js.append("return sp.toString();");
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("obj", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
