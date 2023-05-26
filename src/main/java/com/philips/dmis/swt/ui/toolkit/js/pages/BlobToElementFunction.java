package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class BlobToElementFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "blobToElement";
    private final Widget widget;
    private final WidgetType widgetType;

    public BlobToElementFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("element", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("uid", JsType.STRING));
        parameters.add(JsParameter.getInstance("view", JsType.OBJECT));

    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(element,uid,view)=>{");

        js.append("if(element==undefined||element==null||uid==undefined||uid==null||uid==''||view==undefined||view==null){");
        js.append("return;");
        js.append("};");

        String syncVar = JsPagesModule.getId(widget, SyncVariable.class);

        // todo: move to function and call from other places as well
        js.append("if(element.tagName=='IMG'&&view.format.appearance=='%s'&&%s[uid]!=undefined){",
                URLAppearanceType.XHR_IMAGE,
                syncVar);

        js.append("%s[uid].then((xhrResponse)=>{", syncVar); // function
        js.debug("console.log('image callback',xhrResponse);");
        js.append("const dataURL=window.URL.createObjectURL(xhrResponse.data);");
        js.append("element.setAttribute('src',dataURL);");
        js.append("element.style.visibility='';");
        js.append("delete %s[uid];", syncVar);
        js.append("});"); // end function

        js.append("%s[uid].fail((e)=>{", syncVar); // function
        js.info("console.log('failed',e);");
        js.append("delete %s[uid];", syncVar);
        js.append("});"); // end function

        js.append("};");

        js.append("}");
    }
}
