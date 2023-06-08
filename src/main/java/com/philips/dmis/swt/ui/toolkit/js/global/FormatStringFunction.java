package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class FormatStringFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(format,obj)=>{");
        js.trace(this);

        js.append("const fn=(i,g)=>{");
        js.debug("console.log('format',i,g,obj[g]);");
        js.append("return obj[g];");
        js.append("};");
        js.append("const inject=(str,obj)=>{");
        js.append("return str.replace(/\\${(.*?)}/g,fn);");
        js.append("};");
        js.append("return inject(format,obj);");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("format", JsType.STRING));
        parameters.add(JsParameter.getInstance("obj", JsType.OBJECT));
    }
}
