package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class AppendOptionFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(parent,key,value,dataSourceId)=>{");
        js.trace(this);

        js.append("const o=document.createElement('option');");
        js.append("o.setAttribute('tk-source',dataSourceId);");
        js.append("o.value=key;");
        js.append("o.text=value;");
        js.append("if(parent.add!==undefined){");
        js.append("parent.add(o);");
        js.append("}else{");
        js.append("parent.appendChild(o);");
        js.append("};");
        js.append("return o;");
        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("parent", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("key", JsType.STRING));
        parameters.add(JsParameter.getInstance("value", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }
}
