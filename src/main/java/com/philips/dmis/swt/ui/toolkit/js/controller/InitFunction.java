package com.philips.dmis.swt.ui.toolkit.js.controller;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class InitFunction implements JsFunction {
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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(UpdateFunction.class);
        dependencies.add(DisplayFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("()=>{");
        js.debug("console.log('%s:init');", JsPageControllerModule.class.getName());
        js.append("window.addEventListener('hashchange',%s);", JsPageControllerModule.getId(UpdateFunction.class));
        js.append("%s();", JsPageControllerModule.getId(DisplayFunction.class));
        js.append("}");
    }
}
