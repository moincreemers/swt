package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.BeforeUpdateEvent;
import com.philips.dmis.swt.ui.toolkit.events.BeforeUpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.NotifySubscribersVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.DataBoundWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceUsage;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BeforeUpdateFunction implements JsFunction {
    public static final String ID = "beforeUpdate";

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

        js.append("switch(dataSourceUsage){");
        for (DataSourceUsage dataSourceUsage : DataSourceUsage.values()) {
            js.append("case '%s':", dataSourceUsage.name());
            switch (dataSourceUsage) {
                case TEXT:
                    js.append("%s(id,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTextFunction.class));
                    break;
                case VALUE:
                    js.append("%s(id,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateValueFunction.class));
                    break;
                case OPTIONS:
                    js.append("%s(id,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateOptionsFunction.class));
                    break;
                case LIST_ITEMS:
                    js.append("%s(id,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateListItemsFunction.class));
                    break;
                case TABLE_HEADER:
                    js.append("%s(id,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTableHeaderFunction.class));
                    break;
                case TABLE_BODY:
                    js.append("%s(id,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTableBodyFunction.class));
                    break;
                case TABLE_FOOTER:
                    js.append("%s(id,reason,cacheType,dataSourceId);", JsWidgetModule.getId(BeforeUpdateTableFooterFunction.class));
                    break;
                case TRANSFORM:
                    // not applicable for most data bound widgets (only for data source widgets)
                    // exception is DataProxy which is both

                    js.append("if(widgetType=='%s'&&widget.%s==true){",
                            WidgetType.DATA_PROXY.name(),
                            NotifySubscribersVariable.ID); // if
                    js.append("%s(id,reason,cacheType);",
                            JsWidgetModule.getId(BeforeUpdateSubscribersFunction.class));
                    js.append("};"); // end if
                    break;
            }
            js.append("break;");
        }
        js.append("}");

        js.append("%s(id,'%s',%s);",
                JsWidgetModule.getId(RaiseEventFunction.class),
                BeforeUpdateEventHandler.NAME,
                CustomEvent.valueOf(new BeforeUpdateEvent()));

        js.append("}"); // end function
    }
}
