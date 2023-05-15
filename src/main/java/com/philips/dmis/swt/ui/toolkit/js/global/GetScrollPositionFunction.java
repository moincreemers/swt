package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetScrollPositionFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("()=>{return{x:window.pageXOffset,y:window.pageYOffset};}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
