package com.philips.dmis.swt.ui.toolkit.js.controller;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.GetDocumentHashFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.GetSessionValueFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetPageArgumentFunction implements JsFunction {
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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(DefaultPageIdConst.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("()=>{");
        js.trace(this);

        js.append("const hash=%s();",
                JsGlobalModule.getQualifiedId(GetDocumentHashFunction.class));
        js.append("var key=null;");
        js.append("if(hash.d.length!=0){key=hash.d[hash.d.length-1];};");
        js.append("if(key==null){return {};};");
        js.append("const v=%s(key,{value:{}});",
                JsGlobalModule.getQualifiedId(GetSessionValueFunction.class));
        js.append("if(v==null||v==''){return {};};");
        js.append("const obj=JSON.parse(v);");
        js.append("if(obj.hasOwnProperty('value')){return obj.value;}");
        js.append("return obj;");

        js.append("}"); // end function
    }
}
