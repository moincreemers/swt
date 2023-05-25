package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.ResponseEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

/**
 * Converging method for all data source inbound data.
 */
public class ProcessResponseFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "processResponse";
    private final Widget widget;
    private final WidgetType widgetType;

    public ProcessResponseFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier
                || widget instanceof DataProviderWidget<?>;
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
        dependencies.add(UpdateSubscribersFunction.class);
        dependencies.add(EventHandlerFunction.OnResponseEventHandlerFunction.class);
        dependencies.add(PutCacheFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("xhrResponse", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(xhrResponse)=>{");

        js.debug("console.log('ProcessResponseFunction before',xhrResponse);");

        js.append("if(xhrResponse.status===200){");

        js.append("var unmodifiedResponse={};");
        js.append("var serviceResponse={};");

        js.append("if(xhrResponse.contentType.value=='application/json'){");
        js.append("unmodifiedResponse=JSON.parse(xhrResponse.data);");
        js.append("serviceResponse=JSON.parse(xhrResponse.data);");
        js.append("};");

        if (widget instanceof DataSourceSupplier dataSourceSupplier) {
            if (dataSourceSupplier.getCacheType() != CacheType.DISABLED) {
                // note: we can store STRING only, so we serialize.
                //  also, requestURL must be used as the cache key, the responseURL is NOT the same thing
                js.append("%s(xhrResponse.requestURL,JSON.stringify(xhrResponse));",
                        JsPagesModule.getId(widget, PutCacheFunction.class));
            }
            if (!dataSourceSupplier.isExpectServiceResponse()) {
                // note: if the service response is not a ServiceResponse object then
                //  one or more 'IMPORT' data adapters can be used to transform the data into one.

                // note: we set serviceResponse to null because the import data adapter(s) create it
                js.append("var serviceResponse2=null;");
                js.append("var importAdapters=Object.assign([],%s['%s']);",
                        JsPagesModule.getId(widget, DataAdaptersVariable.class),
                        DataSourceUsage.IMPORT.name());
                js.append("for(const j in importAdapters){");
                js.debug("console.log('Importing, adapter:',importAdapters[j]);");
                js.append("if(%s.includes(importAdapters[j].id)){",
                        JsPagesModule.getId(widget, DisabledDataAdaptersVariable.class));
                js.debug("console.log('data import adapter disabled',importAdapters[j].id);");
                js.append("continue;");
                js.append("};");
                js.append("serviceResponse2=importAdapters[j].fn(serviceResponse2,unmodifiedResponse);");
                js.append("};");
                js.append("if(serviceResponse2!=null){");
                js.append("serviceResponse=serviceResponse2;");
                js.append("};");
            }

            // NOTE: This is where the adapters on DATA SOURCES are called in sequence

            js.append("var adapters=Object.assign([],%s['%s']);",
                    JsPagesModule.getId(widget, DataAdaptersVariable.class),
                    DataSourceUsage.TRANSFORM.name());
            js.append("for(const i in adapters){");
            js.append("if(%s.includes(adapters[i].id)){", JsPagesModule.getId(widget, DisabledDataAdaptersVariable.class));
            js.debug("console.log('data adapter disabled',adapters[i].id);");
            js.append("continue;");
            js.append("};");
            //js.debug("console.log('ProcessResponseFunction before adapter: ' + adapters[i].id + ', items:' + serviceResponse.data.items.length);");
            js.append("serviceResponse=adapters[i].fn(serviceResponse,unmodifiedResponse);");
            //js.debug("console.log('ProcessResponseFunction after adapter: ' + adapters[i].id + ', items:' + serviceResponse.data.items.length);");
            js.append("};");

            // For Data, StaticData and DataProxy, DataVariable is used in a different
            // way because the widget IS the actual data source. We do not want to change the data variable.
            if (!(widgetType == WidgetType.DATA || widgetType == WidgetType.STATICDATA || widgetType == WidgetType.DATA_PROXY)) {
                js.append("%s=JSON.stringify(serviceResponse);", JsPagesModule.getQualifiedId(widget, DataVariable.class));
            }

            js.debug("console.log('ProcessResponseFunction after','widget: %s',serviceResponse);", widget.getId());

            // notify subscribers
            if (dataSourceSupplier.isNotifySubscribers()) {
                js.append("%s('%s',serviceResponse);",
                        JsPagesModule.getId(widget, UpdateSubscribersFunction.class),
                        JsPagesModule.REASON_DATA_SOURCE);
            }
        }

        js.append("};"); // end if (OK)

        // call onResponse event handler
        js.append("%s(xhrResponse);",
                JsPagesModule.getId(widget, EventHandlerFunction.OnResponseEventHandlerFunction.class),
                CustomEvent.valueOf(new ResponseEvent()));

        js.append("}"); // end function
    }
}
