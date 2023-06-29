package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class UpdateFunction implements JsFunction {
    public static final String ID = "update";

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
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("dataSourceUsage", JsType.STRING));
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason,cacheType,dataSourceUsage,object,dataSourceId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        // in case the widget is a DataProxy we store the data, call Refresh and exit.
        js.append("if(widgetType=='%s'){", WidgetType.DATA_PROXY.name()); // if
        // DataProxy stores what it receives from its data source
        js.append("widget.%s=JSON.stringify(object);", DataVariable.ID);
        // call refresh on the DataProxy
        //  note: its important the reason is set to "datasource" otherwise the update will not run any data
        //  adapters on the data bound widget
        js.trace("console.log('updated data proxy',object);");
        js.append("%s(id,'%s');",
                JsWidgetModule.getQualifiedId(RefreshFunction.class),
                JsStateModule.REASON_DATA_SOURCE);
        js.append("return;");
        js.append("};"); // end if


        // in case the reason for the update is that the data source was refreshed
        // then we need to call the data adapters to transform that data first.
        // in the case it is a 'user' initiated update, we need to update the widget directly.
        js.append("if(reason=='%s'){", JsStateModule.REASON_DATA_SOURCE); // if
        // execute only the adapters for the given data source usage
        js.append("var adaptersForUsage=Object.assign([],widget.%s[dataSourceUsage]);", DataAdaptersVariable.ID);
        js.append("var adapters=(adaptersForUsage!=undefined&&adaptersForUsage!=null)?adaptersForUsage[dataSourceId]:[];");
        // NOTE: This is where the adapters on WIDGETS are called in sequence
        js.append("for(const i in adapters){"); // for
        js.append("if(widget.%s.includes(adapters[i].id)){", DisabledDataAdaptersVariable.ID); // if
        js.trace("console.log('data adapter disabled',adapters[i].id);");
        js.append("continue;");
        js.append("};"); // end if
        js.trace("console.log('executing data adapter',adapters[i].id);");
        js.append("object=adapters[i].fn(object);");
        js.append("};"); // end for
        js.append("};");// end if





        // either update self or update slaves
        js.append("const slaveIds=widget.%s;", SlavesVariable.ID);
        js.append("const hasSlaves=slaveIds.length!=0;");
        js.append("const widgetIds=hasSlaves?slaveIds:[id];");
        js.append("for(const i in widgetIds){"); // for
        js.append("var widgetId=widgetIds[i];");

        js.trace("console.log('updating widget',widgetId);");

        js.append("switch(dataSourceUsage){"); // switch
        for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
            js.append("case '%s':", dataSourceUsage.name());
            switch (dataSourceUsage) {
                case VALUE:
                    js.append("if(implements.includes('%s')){", ValueWidget.class.getSimpleName());
                    js.trace("console.log('update value widget');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateValueFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasText.class.getSimpleName());
                    js.trace("console.log('update text widget');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTextFunction.class));
                    js.append("};");
                    break;

                case ITEMS:
                    js.append("if(implements.includes('%s')){", HasOptions.class.getSimpleName());
                    js.trace("console.log('update options');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateOptionsFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasListItems.class.getSimpleName());
                    js.trace("console.log('update list items');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateListItemsFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasDataTemplate.class.getSimpleName());
                    js.trace("console.log('update data template');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTemplateListItemsFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasTableHeaderRows.class.getSimpleName());
                    js.trace("console.log('update table header');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTableHeaderFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasTableRows.class.getSimpleName());
                    js.trace("console.log('update table body');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTableBodyFunction.class));
                    js.append("}else if(implements.includes('%s')){", HasTableFooterRows.class.getSimpleName());
                    js.trace("console.log('update table footer');");
                    js.append("%s(widgetId,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTableFooterFunction.class));
                    js.append("};");
                    break;

                case TRANSFORM:
                    js.append("if(implements.includes('%s')){", DataProxy.class.getSimpleName());
                    js.trace("console.log('update data proxy');");
                    js.append("%s(widgetId,reason,cacheType,dataSourceId);", JsWidgetModule.getId(UpdateDataProxyFunction.class));
                    js.append("};");

                    break;
            }
            js.append("break;");
        }
        js.append("};"); // end switch

        js.append("%s(widgetId,%s,null,null,null,{id:widgetId,reason:reason,cacheType:cacheType,object:object,dataSourceId:dataSourceId,dataSourceUsage:dataSourceUsage});",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnUpdateEventHandlerFunction.class));

        js.append("};"); // end for

        js.append("}"); // end function
    }
}
