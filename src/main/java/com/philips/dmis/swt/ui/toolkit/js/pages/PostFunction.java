package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SendHttpRequestFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.UpdateService;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class PostFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "post";
    private final Widget widget;
    private final WidgetType widgetType;

    public PostFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.UPDATE_SERVICE;
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
        dependencies.add(SendHttpRequestFunction.class);
        dependencies.add(ProcessResponseFunction.class);
        dependencies.add(EventHandlerFunction.OnErrorEventHandlerFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(object)=>{");

        UpdateService updateService = (UpdateService) widget;
        js.append("%s('%s','%s','%s',%s,object,%s,%s);",
                JsGlobalModule.getQualifiedId(SendHttpRequestFunction.class),
                updateService.getHttpMethod().name(),
                updateService.getContentType().getEncoding(),
                updateService.getURL(),
                JsPagesModule.getId(widget, HttpHeadersVariable.class),
                JsPagesModule.getId(widget, ProcessResponseFunction.class),
                JsPagesModule.getId(widget, EventHandlerFunction.OnErrorEventHandlerFunction.class));
        js.append("}");
    }
}
