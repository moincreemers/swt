package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ContentType;
import com.philips.dmis.swt.ui.toolkit.dto.XhrResponse;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.RefreshEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SendHttpRequestFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.ToQueryStringFunction;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.http.HttpMethod;

import java.util.List;

public class RefreshFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "refresh";
    private final Widget widget;
    private final WidgetType widgetType;

    public RefreshFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason)=>{");

        js.debug("console.log('%s','refresh',reason);", widget.getId());

        if (widget instanceof DataSourceSupplier dataSourceSupplier) {
            // stops refresh (for this widget only) if Auto-Refresh is set to false
            // and this refresh is caused by 'init' or 'show'
            js.append("if(%s==false&&(reason=='%s'||reason=='%s')){",
                    JsPagesModule.getId(widget, AutoRefreshVariable.class),
                    JsPagesModule.REASON_INIT,
                    JsPagesModule.REASON_SHOW);
            js.debug("console.log('did not refresh because of: '+reason);");
            js.append("return;");
            js.append("};");

            // notify subscribers that an update event will follow
            if (dataSourceSupplier.isNotifySubscribers()) {
                js.append("%s('%s');",
                        JsPagesModule.getId(widget, BeforeUpdateSubscribersFunction.class),
                        JsPagesModule.REASON_DATA_SOURCE);
            }
        }

        // call onRefresh event handler
        js.append("const refreshEvent=%s;", CustomEvent.valueOf(new RefreshEvent()));
        js.append("refreshEvent.reason=reason;");
        js.append("%s(refreshEvent);",
                JsPagesModule.getId(widget, EventHandlerFunction.OnRefreshEventHandlerFunction.class));

        // if container, also refresh all child widgets
        if (widget instanceof ContainerWidget<?> containerWidget) {
            for (Widget childWidget : containerWidget) {
                if (childWidget instanceof ContainerWidget<?> || childWidget instanceof DataSourceSupplier) {
                    js.append("%s.refresh(reason);", childWidget.getId());
                }
            }
        }

        if (widgetType == WidgetType.DATA || widgetType == WidgetType.STATICDATA) {
            //xhrResponse
            js.append("var xhrResponse=%s;", DtoUtil.valueOf(new XhrResponse(
                    200,
                    "data://" + widget.getId(),
                    new ContentType("application/json"),
                    null)));
            js.append("xhrResponse.responseText=%s;",
                    JsPagesModule.getQualifiedId(widget, DataVariable.class));
            js.append("%s(xhrResponse);",
                    JsPagesModule.getId(widget, ProcessResponseFunction.class));
        }

        if (widgetType == WidgetType.DATA_PROXY) {
            // todo: refresh upstream data source
        }

        if (widgetType == WidgetType.CALCULATED) {
            //xhrResponse
            js.append("var xhrResponse=%s;", DtoUtil.valueOf(new XhrResponse(
                    200,
                    "calculated://" + widget.getId(),
                    new ContentType("application/json"),
                    null)));
            js.append("xhrResponse.responseText=%s;",
                    JsPagesModule.getQualifiedId(widget, DataVariable.class));
            js.append("%s(xhrResponse);",
                    JsPagesModule.getId(widget, ProcessResponseFunction.class));
        }
        if (widget instanceof QueryService queryService) {
            if (queryService.getCacheType() != CacheType.DISABLED
                    && queryService.getHttpMethod() == HttpMethod.GET) {
                // if reason is 'show' or 'local' and data is cached, then resend cached data
                js.append("if(reason=='%s'||reason=='%s'){",
                        JsPagesModule.REASON_SHOW,
                        JsPagesModule.REASON_LOCAL);
                js.append("const key='%s'+'?'+%s(%s);",
                        queryService.getURL(),
                        JsGlobalModule.getQualifiedId(ToQueryStringFunction.class),
                        JsPagesModule.getId(widget, ParametersVariable.class)
                );
                js.append("const cachedValue=%s(key,null);",
                        JsPagesModule.getId(widget, GetCacheFunction.class));
                js.debug("console.log('Cached value','%s',cachedValue);", queryService.getURL());
                js.append("if(cachedValue!=null){"); // if
                //xhrResponse
                js.append("const xhrResponse=JSON.parse(cachedValue);");
                js.append("%s(xhrResponse);",
                        JsPagesModule.getId(widget, ProcessResponseFunction.class));
                if (queryService.getCacheType() == CacheType.ENABLED) {
                    js.append("return;");  // exit function
                }
                js.append("};"); // end if
                js.append("};"); // end if
            }

            // clear data.
            // For Data and StaticData widgets, DataVariable is used in a different
            // way because the widget IS the actual data source. So the only way to change that is to use SetValue again.
            if (!(widgetType == WidgetType.DATA || widgetType == WidgetType.STATICDATA)) {
                js.append("%s=null;", JsPagesModule.getQualifiedId(widget, DataVariable.class));
            }

            js.append("%s('%s','%s','%s',%s,%s,%s,%s);",
                    JsGlobalModule.getQualifiedId(SendHttpRequestFunction.class),
                    queryService.getHttpMethod().name(),
                    queryService.getContentType().getEncoding(),
                    queryService.getURL(),
                    JsPagesModule.getId(widget, HttpHeadersVariable.class),
                    JsPagesModule.getId(widget, ParametersVariable.class),
                    JsPagesModule.getId(widget, ProcessResponseFunction.class),
                    JsPagesModule.getId(widget, EventHandlerFunction.OnErrorEventHandlerFunction.class));
        }

        js.append("}"); // end function
    }
}
