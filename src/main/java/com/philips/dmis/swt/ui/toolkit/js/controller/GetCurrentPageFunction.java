package com.philips.dmis.swt.ui.toolkit.js.controller;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.GetDocumentHashFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.IsValidHashFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetCurrentPageFunction implements JsFunction {
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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(DefaultPageIdConst.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("()=>{");
        js.append("const defaultPageId=%s;", JsPageControllerModule.getId(DefaultPageIdConst.class));
        js.append("const hash=%s();", JsGlobalModule.getQualifiedId(GetDocumentHashFunction.class));
        js.append("var selectedPageId=defaultPageId;");
        js.append("if(%s(hash)){", JsGlobalModule.getQualifiedId(IsValidHashFunction.class)); // if
        js.append("selectedPageId=(hash.p.length==0)?defaultPageId:hash.p[0];");
        js.append("};");
        js.append("return selectedPageId;");
        js.append("}"); // end function
    }
}
