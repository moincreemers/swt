package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.BeforeUpdateEvent;
import com.philips.dmis.swt.ui.toolkit.events.BeforeUpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.EventHandlerRenderer;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class BeforeUpdateFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "beforeUpdate";
    private final Widget widget;
    private final WidgetType widgetType;

    public BeforeUpdateFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataBoundWidget<?>;
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
        dependencies.add(BeforeUpdateListItemsFunction.class);
        dependencies.add(BeforeUpdateTextFunction.class);
        dependencies.add(BeforeUpdateValueFunction.class);
        dependencies.add(BeforeUpdateOptionsFunction.class);
        dependencies.add(BeforeUpdateTableHeaderFunction.class);
        dependencies.add(BeforeUpdateTableBodyFunction.class);
        dependencies.add(BeforeUpdateTableFooterFunction.class);
        dependencies.add(BeforeUpdateSubscribersFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceUsage", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason,cacheType,dataSourceUsage,dataSourceId)=>{");

        js.debug("console.log('BeforeUpdateFunction', '%s',reason,cacheType,dataSourceUsage,dataSourceId);", widget.getId());

        js.append("switch(dataSourceUsage){");
        for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
            js.append("case '%s':", dataSourceUsage.name());
            switch (dataSourceUsage) {
                case TEXT:
                    js.append("%s(reason,cacheType,dataSourceId);", JsPagesModule.getId(widget, BeforeUpdateTextFunction.class));
                    break;
                case VALUE:
                    js.append("%s(reason,cacheType,dataSourceId);", JsPagesModule.getId(widget, BeforeUpdateValueFunction.class));
                    break;
                case OPTIONS:
                    js.append("%s(reason,cacheType,dataSourceId);", JsPagesModule.getId(widget, BeforeUpdateOptionsFunction.class));
                    break;
                case LIST_ITEMS:
                    js.append("%s(reason,cacheType,dataSourceId);", JsPagesModule.getId(widget, BeforeUpdateListItemsFunction.class));
                    break;
                case TABLE_HEADER:
                    js.append("%s(reason,cacheType,dataSourceId);", JsPagesModule.getId(widget, BeforeUpdateTableHeaderFunction.class));
                    break;
                case TABLE_BODY:
                    js.append("%s(reason,cacheType,dataSourceId);", JsPagesModule.getId(widget, BeforeUpdateTableBodyFunction.class));
                    break;
                case TABLE_FOOTER:
                    js.append("%s(reason,cacheType,dataSourceId);", JsPagesModule.getId(widget, BeforeUpdateTableFooterFunction.class));
                    break;
                case TRANSFORM:
                    // not applicable for most data bound widgets (only for data source widgets)
                    // exception is DataProxy which is both

                    if (widgetType == WidgetType.DATA_PROXY) {
                        DataProxy dataProxy = (DataProxy) widget;

                        // notify subscribers
                        if (dataProxy.isNotifySubscribers()) {
                            js.append("%s(reason,cacheType);",
                                    JsPagesModule.getId(widget, BeforeUpdateSubscribersFunction.class));
                        }
                    }
                    break;
            }
            js.append("break;");
        }
        js.append("}");

        EventHandlerRenderer.renderHandlers(widget.getEventHandlers(), toolkit, widget, js, BeforeUpdateEventHandler.NAME, new BeforeUpdateEvent());

        js.append("}"); // end function
    }
}
