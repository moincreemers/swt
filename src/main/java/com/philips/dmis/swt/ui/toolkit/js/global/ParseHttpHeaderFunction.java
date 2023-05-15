package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class ParseHttpHeaderFunction implements JsFunction {
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
        js.append("(value)=>{");

        js.append("if(value==undefined||value==null||value==''){");
        js.throwError("expected value", "value");
        js.append("};");
        js.append("const result={};");
        js.append("const a=value.split(', ');"); // header value may contain more than one header
        js.append("if(a.length==0){return null;}");
        js.append("const j=0;"); // note: we just parse the first occurrence of the header, duplicate headers is rare
        js.append("const b=a[j].split(';');");
        js.append("result.value=b[0];");
        js.append("for(var i=1;i<b.length;i++){"); // for
        js.append("var c=b[i].split('=');");
        js.append("if(c.length==2&&c[0]!=''&&c[1]!=''){"); // if
        js.append("result[c[0]]=c[1];");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("return result;");

        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("xhr", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
