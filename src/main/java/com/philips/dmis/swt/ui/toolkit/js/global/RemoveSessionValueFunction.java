package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveSessionValueFunction implements JsFunction {
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
        return JsType.VOID;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(key)=>{");
        js.append("if(key==undefined||key==null||key==''){");
        js.throwError("invalid key", "key");
        js.append("};");
        js.append("sessionStorage.removeItem(key);");
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("key", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
