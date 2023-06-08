package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.XhrResponse;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.RefreshEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.GetCacheFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.ToQueryStringFunction;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.http.HttpMethod;

import java.util.List;

public class RefreshFunction implements JsFunction {
    public static final String ID = "refresh";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof ContainerWidget<?> || widget instanceof DataSourceSupplier;
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
        dependencies.add(AutoRefreshVariable.class);
        dependencies.add(SendHttpRequestFunction.class);
        dependencies.add(ParametersVariable.class);
        dependencies.add(ProcessResponseFunction.class);
        dependencies.add(EventHandlerFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id2", JsType.STRING));
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id2,reason)=>{");
        js.trace(this);

        js.append("const widget=window[id2];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);

        js.append("if(implements.includes('%s')){", DataSourceSupplier.class.getSimpleName()); // if
        // stops refresh (for this widget only) if Auto-Refresh is set to false
        // and this refresh is caused by 'init' or 'show'
        js.append("if(widget.%s==false&&(reason=='%s'||reason=='%s')){",
                AutoRefreshVariable.ID,
                JsStateModule.REASON_INIT, JsStateModule.REASON_SHOW);
        js.debug("console.log('did not refresh because of: '+reason);");
        js.append("return;");
        js.append("};"); // end if
        // notify subscribers that an update event will follow
        js.append("if(widget.%s){", NotifySubscribersVariable.ID);// if
        js.append("%s(id2,'%s');",
                JsWidgetModule.getId(BeforeUpdateSubscribersFunction.class),
                JsStateModule.REASON_DATA_SOURCE);
        js.append("};"); // end if
        js.append("};"); // end if

        // call onRefresh event handler
        js.append("const refreshEvent=%s;", CustomEvent.valueOf(new RefreshEvent()));
        js.append("refreshEvent.reason=reason;");
        js.append("%s(id2,%s,refreshEvent);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnRefreshEventHandlerFunction.class));

        // if container, also refresh all child widgets
        js.append("if(implements.includes('%s')){", ContainerWidget.class.getSimpleName()); // if
        js.append("for(const i in widget.%s){", ChildWidgetsVariable.ID); // for
        js.append("var childWidgetId=widget.%s[i];", ChildWidgetsVariable.ID);
        js.append("var childWidget=window[childWidgetId];");
        js.append("var childImplements=childWidget.%s;", ImplementsVariable.ID);
        js.append("if(childImplements.includes('%s')||childImplements.includes('%s')){",
                ContainerWidget.class.getSimpleName(), DataSourceSupplier.class.getSimpleName()); // if
        js.append("%s(childWidgetId,reason);", JsWidgetModule.getId(RefreshFunction.class));
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("};"); // end if

        js.ifInArray("widgetType", WidgetType.DATA.name(),
                WidgetType.STATICDATA.name(), WidgetType.CALCULATED.name(),
                WidgetType.DATA_PROXY.name()); // if
        //re-create xhrResponse
        js.append("var data=widget.%s;", DataVariable.ID);
        js.append("if(data!=null){");

        js.append("var xhrResponse=%s;", DtoUtil.getDefault(XhrResponse.class, false));
        js.append("xhrResponse.status=200;");
        js.append("xhrResponse.url=widgetType+'://'+id2;");
        js.append("xhrResponse.data=data;");
        js.append("xhrResponse.contentType.value='%s';", ContentType.JSON.getEncoding());
        js.append("%s(id2,xhrResponse);",
                JsWidgetModule.getId(ProcessResponseFunction.class));
        js.append("};");
        js.append("};"); // end if

        js.append("if(implements.includes('%s')){", QueryService.class.getSimpleName());//if
        js.append("if(widget.%s=='%s'&&widget.%s=='%s'){",
                CacheTypeVariable.ID, CacheType.DISABLED.name(),
                HttpMethodVariable.ID, HttpMethod.GET.name()); // if
        // if reason is 'show' or 'local' and data is cached, then resend cached data
        js.append("if(reason=='%s'||reason=='%s'){",
                JsStateModule.REASON_SHOW, JsStateModule.REASON_LOCAL);
        js.append("const key=widget.%s+'?'+%s(widget.%s);",
                URLVariable.ID,
                JsGlobalModule.getQualifiedId(ToQueryStringFunction.class),
                ParametersVariable.ID);
        js.append("const cachedValue=%s(key,null);", JsGlobalModule.getId(GetCacheFunction.class));
        js.debug("console.log('Cached value',key,cachedValue);");
        js.append("if(cachedValue!=null){"); // if
        //xhrResponse
        js.append("const xhrResponse=JSON.parse(cachedValue);");
        js.append("%s(id2,xhrResponse);", JsWidgetModule.getId(ProcessResponseFunction.class));
        js.append("if(widget.%s=='%s'){",
                CacheTypeVariable.ID, CacheType.ENABLED.name()); // if
        js.append("return;");  // exit function
        js.append("};"); // end if
        js.append("};"); // end if
        js.append("};"); // end if
        js.append("};"); // end if

        // clear data.
        // For Data and StaticData widgets, DataVariable is used in a different
        // way because the widget IS the actual data source. So the only way to change that is to use SetValue again.
        js.ifNotInArray("widgetType", WidgetType.DATA.name(), WidgetType.STATICDATA.name()); // if
        js.append("widget.%s=null;", DataVariable.ID);
        js.append("};"); // end if

        // method,contentTypeEncoding,responseType,url,headers,obj,success,failure,args
        js.append("%s(id2,widget.%s,widget.%s,widget.%s,widget.%s,widget.%s,%s(id2),%s,%s,{});",
                JsGlobalModule.getQualifiedId(SendHttpRequestFunction.class),
                HttpMethodVariable.ID,
                ContentTypeEncodingVariable.ID,
                ResponseTypeVariable.ID,
                URLVariable.ID,
                HttpHeadersVariable.ID,
                JsWidgetModule.getId(GetParametersFunction.class),
                JsWidgetModule.getId(ProcessResponseFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnErrorEventHandlerFunction.class));

        js.append("};");// end if

        js.append("}"); // end function
    }
}
