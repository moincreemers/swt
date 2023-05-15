package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SanitizeHtmlFunction implements JsFunction {
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
        js.append("(html)=>{");
        js.append("if(html==undefined||html==null){return html;}");
        js.append("const d=['comment','embed','link','listing','meta','noscript','object','plainhtml','script','xmp'];");
        js.append("const x=html.toString().toLowerCase();");
        js.append("for(var i=0;i<d.length;i++){");
        js.append("if(x.indexOf('<'+d[i])!=-1){");
        js.append("return '(unsafe content)';");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("return html;");
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("html", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
