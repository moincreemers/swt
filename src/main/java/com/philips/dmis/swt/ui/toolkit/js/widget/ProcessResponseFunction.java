package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.ResponseEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.PutCacheFunction;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

/**
 * Converging method for all data source inbound data.
 */
public class ProcessResponseFunction implements JsFunction {
    public static final String ID = "processResponse";

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
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("xhrResponse", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,xhrResponse)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);

        js.append("if(xhrResponse.status===200){");
        js.append("var unmodifiedResponse={};");
        js.append("var serviceResponse={};");
        js.append("if(xhrResponse.contentType.value=='%s'){", ContentType.JSON.getEncoding());
        js.append("unmodifiedResponse=JSON.parse(xhrResponse.data);");
        js.append("serviceResponse=JSON.parse(xhrResponse.data);");
        js.append("};");

        js.append("if(implements.includes('%s')){", DataSourceSupplier.class.getSimpleName()); // if
        js.append("if(widget.%s=='%s'){", CacheTypeVariable.ID, CacheType.DISABLED.name()); // if
        // note: we can store STRING only, so we serialize.
        //  also, requestURL must be used as the cache key, the responseURL is NOT the same thing
        js.append("%s(xhrResponse.requestURL,JSON.stringify(xhrResponse));",
                JsGlobalModule.getQualifiedId(PutCacheFunction.class));
        js.append("};"); // end if

        js.append("if(widget.%s==false){", ExpectServiceResponseVariable.ID); // if
        // note: if the service response is not a ServiceResponse object then
        //  one or more 'IMPORT' data adapters can be used to transform the data into one.
        // note: we set serviceResponse to null because the import data adapter(s) create it
        js.append("var serviceResponse2=null;");
        js.append("var importAdapters=widget.%s['%s'];", DataAdaptersVariable.ID, DataSourceUsage.IMPORT.name());
        js.append("for(const j in importAdapters){");
        js.debug("console.log('Importing, adapter:',importAdapters[j]);");
        js.append("if(widget.%s.includes(importAdapters[j].id)){", DisabledDataAdaptersVariable.ID);
        js.debug("console.log('data import adapter disabled',importAdapters[j].id);");
        js.append("continue;");
        js.append("};");
        js.append("serviceResponse2=importAdapters[j].fn(serviceResponse2,unmodifiedResponse);");
        js.append("};");
        js.append("if(serviceResponse2!=null){");
        js.append("serviceResponse=serviceResponse2;");
        js.append("};");
        js.append("};"); // end if

        // NOTE: This is where the adapters on DATA SOURCES are called in sequence
        js.append("var adapters=widget.%s['%s'];", DataAdaptersVariable.ID, DataSourceUsage.TRANSFORM.name());
        js.append("for(const i in adapters){");
        js.append("if(widget.%s.includes(adapters[i].id)){", DisabledDataAdaptersVariable.ID);
        js.debug("console.log('data adapter disabled',adapters[i].id);");
        js.append("continue;");
        js.append("};");
        js.append("serviceResponse=adapters[i].fn(serviceResponse,unmodifiedResponse);");
        js.append("};");

        js.append("if(!(widgetType=='%s'||widgetType=='%s'||widgetType=='%s')){",
                WidgetType.DATA.name(), WidgetType.STATICDATA.name(), WidgetType.DATA_PROXY.name()); // if
        // For Data, StaticData and DataProxy, DataVariable is used in a different
        // way because the widget IS the actual data source. We do not want to change the data variable.
        js.append("widget.%s=JSON.stringify(serviceResponse);", DataVariable.ID);
        js.append("};"); // end if

        js.debug("console.log('ProcessResponseFunction after',id,serviceResponse);");

        // notify subscribers
        js.append("if(widget.%s){", NotifySubscribersVariable.ID); // if
        js.append("%s(id,'%s',serviceResponse);",
                JsWidgetModule.getId(UpdateSubscribersFunction.class),
                JsStateModule.REASON_DATA_SOURCE);
        js.append("};"); // end if

        js.append("};"); // end if

        js.append("};"); // end if (OK)

        // call onResponse event handler
        // todo: event is now the xhrResponse????
        js.append("%s(id,xhrResponse);",
                JsWidgetModule.getId(EventHandlerFunction.OnResponseEventHandlerFunction.class),
                CustomEvent.valueOf(new ResponseEvent()));

        js.append("}"); // end function
    }
}
