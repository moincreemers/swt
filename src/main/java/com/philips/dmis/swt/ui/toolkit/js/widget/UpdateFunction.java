package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.DataBoundWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateFunction implements JsFunction {
    public static final String ID = "update";

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

        js.append("if(widgetType=='%s'){", WidgetType.DATA_PROXY.name()); // if
        // DataProxy stores what it receives from its data source
        js.append("widget.%s=JSON.stringify(object);", DataVariable.ID);
        // call refresh on the DataProxy
        //  note: its important the reason is set to "datasource" otherwise the update will not run any data
        //  adapters on the data bound widget
        js.debug("console.log('updated data proxy',object);");
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
        js.append("var adapters=Object.assign([],widget.%s[dataSourceUsage]);", DataAdaptersVariable.ID);
        // NOTE: This is where the adapters on WIDGETS are called in sequence
        js.append("for(const i in adapters){"); // for
        js.append("if(widget.%s.includes(adapters[i].id)){", DisabledDataAdaptersVariable.ID); // if
        js.debug("console.log('data adapter disabled',adapters[i].id);");
        js.append("continue;");
        js.append("};"); // end if
        js.append("object=adapters[i].fn(object);");
        js.append("};"); // end for
        js.append("};");// end if

        js.append("switch(dataSourceUsage){"); // switch
        for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
            js.append("case '%s':", dataSourceUsage.name());
            switch (dataSourceUsage) {
                case TEXT:
                    js.append("%s(id,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTextFunction.class));
                    break;
                case VALUE:
                    js.append("%s(id,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateValueFunction.class));
                    break;
                case OPTIONS:
                    js.append("%s(id,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateOptionsFunction.class));
                    break;
                case LIST_ITEMS:
                    js.append("%s(id,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateListItemsFunction.class));
                    break;
                case TABLE_HEADER:
                    js.append("%s(id,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTableHeaderFunction.class));
                    break;
                case TABLE_BODY:
                    js.append("%s(id,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTableBodyFunction.class));
                    break;
                case TABLE_FOOTER:
                    js.append("%s(id,reason,cacheType,object,dataSourceId);", JsWidgetModule.getId(UpdateTableFooterFunction.class));
                    break;
            }
            js.append("break;");
        }
        js.append("};"); // end switch

        js.append("%s(id,%s);",
                JsWidgetModule.getId(EventHandlerFunction.OnUpdateEventHandlerFunction.class),
                CustomEvent.valueOf(new UpdateEvent()));

        js.append("}"); // end function
    }
}
