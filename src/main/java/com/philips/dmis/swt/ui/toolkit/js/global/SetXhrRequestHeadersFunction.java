package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetXhrRequestHeadersFunction implements JsFunction {
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
        js.append("(xhr,headers)=>{"); // function

        js.append("if(xhr==undefined||xhr==null||headers==undefined||headers==null){return;};");
        js.debug("console.log('SetXhrRequestHeadersFunction',headers);");
        js.append("for(const headerName in headers){"); // for
        js.append("var headerValues=headers[headerName];");
        js.append("for(const hi in headerValues){"); // for
        js.append("xhr.setRequestHeader(headerName,headerValues[hi]);");
        js.append("};"); // end for
        js.append("};"); // end for

        js.append("}"); // end function
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("xhr", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("headers", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
