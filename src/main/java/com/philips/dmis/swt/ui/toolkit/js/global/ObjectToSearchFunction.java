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
        js.append("var search='';");
        js.append("for(const k in obj){"); // for
        // js.debug("console.log(obj[k], typeof obj[k]);");
        js.append("if(Array.isArray(obj[k])){"); // if
        js.append("for(const i in obj[k]){"); // for
        js.append("if(obj[k][i]==null||obj[k][i]==''){continue;};");
        js.append("search+='&';");
        js.append("search+=encodeURIComponent(k);");
        js.append("search+='=';");
        js.append("search+=encodeURIComponent(obj[k][i]);");
        js.append("};");// end for
        js.append("}");// else
        js.append("else if(typeof obj[k]=='string'){");
        js.append("if(obj[k]==''){continue;};");
        js.append("search+='&';");
        js.append("search+=encodeURIComponent(k);");
        js.append("search+='=';");
        js.append("search+=encodeURIComponent(obj[k]);");
        js.append("};");// end if
        js.append("};");// end for
        js.append("if(search.length>0){search=search.substring(1);};");
        js.append("return search;");
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
