package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.AuthenticationType;
import com.philips.dmis.swt.ui.toolkit.widgets.RequestUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetXhrCredentialsFunction implements JsFunction {
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
        return JsType.VOID;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(xhr,authenticationType)=>{"); // function
        js.trace(this);

        js.append("if(xhr==undefined||xhr==null){return;};");

        js.append("switch(authenticationType){");

        js.append("case '%s':", AuthenticationType.BEARER_JWT.name());
        js.append("xhr.setRequestHeader('Authorization','Bearer '+%s('%s',''));",
                JsGlobalModule.getQualifiedId(GetSessionValueFunction.class),
                RequestUtil.GLOBAL_ACCESS_TOKEN_KEY);
        js.append("break;");

        js.append("};"); // end switch


        js.append("}"); // end function
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("xhr", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("authenticationType", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
