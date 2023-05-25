package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.EventHandlerRenderer;
import com.philips.dmis.swt.ui.toolkit.events.UpdateEvent;
import com.philips.dmis.swt.ui.toolkit.events.UpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class UpdateFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "update";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateFunction(Widget widget) {
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
        dependencies.add(DataAdaptersVariable.class);
        dependencies.add(UpdateValueFunction.class);
        dependencies.add(UpdateOptionsFunction.class);
        dependencies.add(UpdateListItemsFunction.class);
        dependencies.add(UpdateTableHeaderFunction.class);
        dependencies.add(UpdateTableBodyFunction.class);
        dependencies.add(UpdateSubscribersFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceUsage", JsType.STRING));
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason,cacheType,dataSourceUsage,object,dataSourceId)=>{");

        js.debug("console.log('update %s',reason,cacheType,dataSourceUsage,object,dataSourceId);", widget.getId());

        // in case the reason for the update is that the data source was refreshed
        // then we need to call the data adapters to transform that data first.
        // in the case it is a 'user' initiated update, we need to update the widget directly.
        js.append("if(reason=='%s'){", JsPagesModule.REASON_DATA_SOURCE);
        // execute only the adapters for the given data source usage
        js.append("var adapters=Object.assign([],%s[dataSourceUsage]);", JsPagesModule.getId(widget, DataAdaptersVariable.class));
        js.debug("console.log('%s','adapters',adapters);", widget.getId());

        // NOTE: This is where the adapters on WIDGETS are called in sequence

        js.append("for(const i in adapters){");
        js.append("if(%s.includes(adapters[i].id)){", JsPagesModule.getId(widget, DisabledDataAdaptersVariable.class));
        js.debug("console.log('data adapter disabled',adapters[i].id);");
        js.append("continue;");
        js.append("};");
        js.append("object=adapters[i].fn(object);");
        js.append("};"); // end for

        js.append("};");// end if

        js.append("switch(dataSourceUsage){");
        for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
            js.append("case '%s':", dataSourceUsage.name());
            switch (dataSourceUsage) {
                case TEXT:
                    js.append("%s(reason,cacheType,object,dataSourceId);", JsPagesModule.getId(widget, UpdateTextFunction.class));
                    break;
                case VALUE:
                    js.append("%s(reason,cacheType,object,dataSourceId);", JsPagesModule.getId(widget, UpdateValueFunction.class));
                    break;
                case OPTIONS:
                    js.append("%s(reason,cacheType,object,dataSourceId);", JsPagesModule.getId(widget, UpdateOptionsFunction.class));
                    break;
                case LIST_ITEMS:
                    js.append("%s(reason,cacheType,object,dataSourceId);", JsPagesModule.getId(widget, UpdateListItemsFunction.class));
                    break;
                case TABLE_HEADER:
                    js.append("%s(reason,cacheType,object,dataSourceId);", JsPagesModule.getId(widget, UpdateTableHeaderFunction.class));
                    break;
                case TABLE_BODY:
                    js.append("%s(reason,cacheType,object,dataSourceId);", JsPagesModule.getId(widget, UpdateTableBodyFunction.class));
                    break;
                case TABLE_FOOTER:
                    js.append("%s(reason,cacheType,object,dataSourceId);", JsPagesModule.getId(widget, UpdateTableFooterFunction.class));
                    break;
            }
            js.append("break;");
        }
        js.append("}");

        if (widgetType == WidgetType.DATA_PROXY) {
            // DataProxy stores what it receives from the data source
            js.append("%s=JSON.stringify(object);", JsPagesModule.getQualifiedId(widget, DataVariable.class));

            // call refresh on the DataProxy
            js.debug("console.log('refresh data proxy',object);");
            js.append("%s(reason);", JsPagesModule.getQualifiedId(widget, RefreshFunction.class));
        }

        EventHandlerRenderer.renderHandlers(widget.getEventHandlers(), toolkit, widget, js, UpdateEventHandler.NAME, new UpdateEvent());

        js.append("}"); // end function
    }
}
