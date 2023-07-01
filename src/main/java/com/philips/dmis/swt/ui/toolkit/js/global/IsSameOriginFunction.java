package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class IsSameOriginFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(url)=>{");
        js.trace(this);

        js.append("if(url===undefined||url===null||url===''){");
        js.append("return true;");
        js.append("};");

        js.append("if(url.startsWith('/')){return true;};");

        js.append("const u=null;");
        js.append("try{u=new URL(url);}catch(e){};");

        js.append("if(u==null&&url.startsWith('http')){return false;};");
        js.append("if(u==null){return true;};");

        js.append("return u.host===location.host;");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("url", JsType.STRING));
    }
}
