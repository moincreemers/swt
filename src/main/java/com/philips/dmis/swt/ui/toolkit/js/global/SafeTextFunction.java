package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SafeTextFunction implements JsFunction {
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
        js.append("(text)=>{");
        js.append("const d=['comment','embed','link','listing','meta','noscript','object','plaintext','script','xmp'];");
        js.append("const x=text.toLowerCase();");
        js.append("for(var i=0;i<d.length;i++){");
        js.append("if(x.indexOf('<'+d[i])!=-1){");
        js.append("return '(unsafe content)';");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("return text;");
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("text", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
