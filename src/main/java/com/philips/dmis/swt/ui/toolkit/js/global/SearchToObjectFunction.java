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
        js.append("var p=search.split('&');");
        js.append("var o={};");
        js.append("for(const i in p){");//for
        js.append("if(p[i]==''){continue;};");
        js.append("var kv=p[i].split('=');");
        js.append("if(o[kv[0]]==undefined){o[kv[0]]=[];};");
        js.append("if(kv.length==2&&kv[1]!=undefined&&kv[1]!=''){"); // if
        js.append("o[kv[0]].push(kv[1]);");
        js.append("};");//end if
        js.append("};");//end for
        js.append("return o;");
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
