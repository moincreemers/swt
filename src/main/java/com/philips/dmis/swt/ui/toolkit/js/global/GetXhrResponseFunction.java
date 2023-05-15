package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetXhrResponseFunction implements JsFunction {
    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
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
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(requestURL,xhr)=>{");

        js.append("const response={};");
        js.append("response.status=xhr.status;");
        js.append("response.requestURL=requestURL;");
        js.append("response.responseUrl=xhr.responseURL;");
        js.append("response.contentType=%s(xhr.getResponseHeader('content-type'));",
                JsGlobalModule.getQualifiedId(ParseHttpHeaderFunction.class));
        js.append("response.responseText=xhr.responseText;");
        js.append("return response;");

        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("xhr", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
