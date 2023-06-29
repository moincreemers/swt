package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.SlavesVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class BeforeUpdateFunction implements JsFunction {
    public static final String ID = "beforeUpdate";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataBoundWidget<?, ?>;
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
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceUsage", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason,cacheType,dataSourceUsage,dataSourceId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);

        // either update self or update slaves
        js.append("const slaveIds=widget.%s;", SlavesVariable.ID);
        js.append("const hasSlaves=slaveIds.length!=0;");
        js.append("const widgetIds=hasSlaves?slaveIds:[id];");
        js.append("for(const i in widgetIds){"); // for
        js.append("var widgetId=widgetIds[i];");

        js.append("switch(dataSourceUsage){");
        for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
            js.append("case '%s':", dataSourceUsage.name());
            switch (dataSourceUsage) {
                case VALUE:
                    js.append("if(implements.includes('%s')){", ValueWidget.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateValueFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasText.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTextFunction.class));
                    js.append("};");
                    break;

                case ITEMS:
                    js.append("if(implements.includes('%s')){", HasOptions.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateOptionsFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasListItems.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateListItemsFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasDataTemplate.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTemplateListItemsFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasTableHeaderRows.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTableHeaderFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasTableRows.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTableBodyFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasTableFooterRows.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTableFooterFunction.class));
                    js.append("};");
                    break;

                case TRANSFORM:
                    js.append("if(implements.includes('%s')){", DataProxy.class.getSimpleName());
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateDataProxyFunction.class));
                    js.append("};");
                    break;
            }
            js.append("break;");
        }
        js.append("};"); // end switch

        js.append("%s(widgetId,%s);",
                JsWidgetModule.getId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnBeforeUpdateEventHandlerFunction.class));

        js.append("};"); // end for

        js.append("}"); // end function
    }
}
