package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class CreatePromiseFunction implements JsFunction {
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
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");

        js.append("var promise=function(){");

        js.append("var value=null;");
        js.append("var error=null;");
        js.append("var resolved=0;");
        js.append("var onResolved=null;");
        js.append("var onRejected=null;");

        js.append("var isResolved=()=>{return resolved!=0;};");
        js.append("var resolve=(v)=>{value=v;resolved=1;notifyResolved();};");
        js.append("var reject=(err)=>{error=err;resolved=2;notifyRejected();};");
        js.append("var notifyResolved=()=>{if(resolved==1&&onResolved!=null){onResolved(value);};};");
        js.append("var notifyRejected=()=>{if(resolved==2&&onRejected!=null){onRejected(error);};};");

        js.append("var then=(fn)=>{"); // function
        js.append("onResolved=fn;notifyResolved();");
        js.append("};"); // end function

        js.append("var fail=(fn)=>{"); // function
        js.append("onRejected=fn;notifyRejected();");
        js.append("};"); // end function

        js.append("return {resolve:resolve,reject:reject,then:then,fail:fail,isResolved:isResolved}");

        js.append("};");

        js.append("return new promise();");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("parent", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("key", JsType.STRING));
        parameters.add(JsParameter.getInstance("value", JsType.STRING));
    }
}
