package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.FormatCodeFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.global.SanitizeHtmlFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class SetTextFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setText";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetTextFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasText;
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(text)=>{");

        if (widget instanceof HasText) {
            js.append("const textElement=document.getElementById('%s');",
                    HasText.getTextId(widget.getId()));
            js.append("if(%s(text)||Array.isArray(text)){",
                    JsGlobalModule.getQualifiedId(IsObjectFunction.class));
            js.append("textElement.textContent=JSON.stringify(text,null,4);");
            js.append("}else if(%s=='%s'){", JsPagesModule.getId(widget, TextFormatVariable.class),
                    TextFormatType.JSON.name());
            js.append("textElement.textContent=JSON.stringify(JSON.valueOf(text),null,4);");
            js.append("}else if(%s=='%s'){", JsPagesModule.getId(widget, TextFormatVariable.class),
                    TextFormatType.JAVA_AND_JS.name());
            js.append("textElement.textContent=%s(text);",
                    JsGlobalModule.getQualifiedId(FormatCodeFunction.class));
            js.append("}else{");
            // NOTE: careful
            js.append("textElement.innerHTML=%s(text);",
                    JsGlobalModule.getQualifiedId(SanitizeHtmlFunction.class));
            js.append("}");
        }

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }
}
