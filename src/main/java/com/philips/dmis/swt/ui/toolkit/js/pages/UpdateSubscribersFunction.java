package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class UpdateSubscribersFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "updateSubscribers";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateSubscribersFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        dependencies.add(UpdateFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason,object)=>{");

        if (widget instanceof DataSourceSupplier dataSourceSupplier) {
            js.debug("console.log('UpdateSubscribersFunction',reason,object);");
            for (HasDataSource<?> subscriber : dataSourceSupplier.getSubscribers().keySet()) {
                DataSourceUsage dataSourceUsage = dataSourceSupplier.getSubscribers().get(subscriber);
                js.append("%s(reason,'%s','%s',object,'%s');",
                        JsPagesModule.getQualifiedId(subscriber.asWidget(), UpdateFunction.class),
                        dataSourceSupplier.getCacheType().name(),
                        dataSourceUsage.name(),
                        widget.getId());
            }
        }

        js.append("}");
    }
}
