package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class IsContainerFunction implements JsFunction {
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

        // todo: change to parameter id and then get widgetType that way

        js.append("(widgetType)=>{");
        js.trace(this);

        js.append("const types=[");
        int i = 0;
        for (WidgetType widgetType : WidgetType.values()) {
            if (!widgetType.isContainer()) {
                continue;
            }
            if (i > 0) {
                js.append(",");
            }
            js.append("'%s'", widgetType.name());
            i++;
        }
        js.append("];");
        js.append("return types.includes(widgetType);");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("widgetType", JsType.STRING));
    }
}
