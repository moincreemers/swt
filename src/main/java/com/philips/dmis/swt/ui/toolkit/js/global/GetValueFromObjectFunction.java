package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetValueFromObjectFunction implements JsFunction {
    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
    }

    @Override
    public boolean isPublic() {
        return Constants.DEBUG;
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
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("path", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(object,path)=>{");

        js.append("if(object==undefined||object==null){return null;}");
        js.append("var p=path;");
        js.append("if(p!=null&&typeof p=='string'&&p.startsWith('.')){");
        js.append("p=p.substring(1);");
        js.append("p=p.split('.');");
        js.append("};");
        js.append("const isPath=p!=null&&Array.isArray(p);");
        js.append("if(isPath){");
        js.append("var v=object;");
        js.append("for(const i in p){");
        js.append("v=v[p[i]];");
        js.append("if(v==undefined||v==null){break;}");
        js.append("};");
        js.append("return v;");
        js.append("}else{");
        js.append("if(p==null||p==''){return object;}");
        js.append("return object[p];");
        js.append("};");

        js.append("}");
    }
}
