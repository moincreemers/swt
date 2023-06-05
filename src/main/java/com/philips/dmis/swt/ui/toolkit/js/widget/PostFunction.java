package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class PostFunction implements JsFunction {
    public static final String ID = "post";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.UPDATE_SERVICE;
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
        dependencies.add(SendHttpRequestFunction.class);
        dependencies.add(ProcessResponseFunction.class);
        dependencies.add(EventHandlerFunction.OnErrorEventHandlerFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,object)=>{");
        js.trace(this);

        // method,contentTypeEncoding,responseType,url,headers,obj,success,failure,args
        js.append("const widget=window[id];");
        js.append("%s(id,widget.%s,widget.%s,widget.%s,widget.%s,window.%s,object,%s,%s,{});",
                JsGlobalModule.getQualifiedId(SendHttpRequestFunction.class),
                HttpMethodVariable.ID,
                ContentTypeEncodingVariable.ID,
                ResponseTypeVariable.ID,
                URLVariable.ID,
                HttpHeadersVariable.ID,
                JsWidgetModule.getQualifiedId(ProcessResponseFunction.class),
                JsWidgetModule.getQualifiedId(EventHandlerFunction.OnErrorEventHandlerFunction.class));

        js.append("}");
    }
}
