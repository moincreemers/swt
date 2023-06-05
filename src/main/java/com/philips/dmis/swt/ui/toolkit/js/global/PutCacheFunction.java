package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class PutCacheFunction implements JsFunction {
    public static final String ID = "putCache";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier;
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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(key,value)=>{");

        // note: key must be a valid identifier
        js.append("if(key==undefined||key==null||key.toString().length==0){return value;}");
        // note: we do not cache null
        js.append("if(value==undefined||value==null){return value;}");
        js.append("if(typeof value == 'object'){");
        // note: precautionary measure, if this gets assigned to c.value then result is undefined
        js.append("value=JSON.stringify(value);");
        js.append("};");
        js.append("sessionStorage.setItem(key,value);");
        js.append("return value;");

        js.append("}");
    }
}
