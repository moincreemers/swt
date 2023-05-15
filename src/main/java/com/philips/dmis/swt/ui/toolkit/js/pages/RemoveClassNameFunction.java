package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveClassNameFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "removeClassName";
    private final Widget widget;
    private final WidgetType widgetType;

    public RemoveClassNameFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return !widgetType.isHidden();
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(className)=>{");
        js.append("%s(%s,className);",
                JsGlobalModule.getQualifiedId(com.philips.dmis.swt.ui.toolkit.js.global.RemoveClassNameFunction.class),
                JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(com.philips.dmis.swt.ui.toolkit.js.global.RemoveClassNameFunction.class);
        dependencies.add(GetElementFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("className", JsType.STRING));
    }
}
