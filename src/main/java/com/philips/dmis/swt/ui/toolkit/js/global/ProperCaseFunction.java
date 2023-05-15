package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class ProperCaseFunction implements JsFunction {
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
        // streetAddress --> Street Address
        // street_address --> Street Address
        // street address --> Street Address
        js.append("(s)=>{");
        js.append("if(s==null||s.length==0){return s;};");
        js.append("var a='';");
        js.append("var u=false;");
        js.append("for(var i=0;i<s.length;i++){");
        js.append("var c=s.charCodeAt(i);");
        js.append("if(i==0){a+=s.charAt(i).toUpperCase();}"); // first char
        js.append("else if(c==95||c==32){a+=' ';u=true;}"); // underscore, space
        js.append("else if(c>96&&c<123){if(u){a+=s.charAt(i).toUpperCase();u=false;}else{a+=s.charAt(i);}}"); // lowercase
        js.append("else if(c>64&&c<91){a+=' ';a+=s.charAt(i);}"); // uppercase
        js.append("};");
        js.append("return a;");
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("s", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
