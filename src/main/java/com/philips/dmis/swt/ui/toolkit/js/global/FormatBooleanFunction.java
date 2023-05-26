package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.BooleanAppearanceType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.pages.IsPageModuleMember;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class FormatBooleanFunction implements JsFunction, IsPageModuleMember {
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(element,value,booleanFormat)=>{");

        js.append("if(value==undefined||booleanFormat==undefined||value==null){");
        js.append("element.textContent=value;");
        js.append("return element;");
        js.append("};");

        js.append("var newElement=element;");

        js.append("switch(booleanFormat.appearance){");
        js.append("case '%s':", BooleanAppearanceType.CIRCLE.name());
        js.append("var span = document.createElement('span');");
        js.append("span.classList.add(value?'tk-circle-true':'tk-circle-false');");
        js.append("element.append(span);");
        js.append("newElement=span;");
        js.append("break;");
        js.append("case '%s':", BooleanAppearanceType.SQUARE.name());
        js.append("var span = document.createElement('span');");
        js.append("span.classList.add(value?'tk-square-true':'tk-square-false');");
        js.append("element.append(span);");
        js.append("newElement=span;");
        js.append("break;");
        js.append("default:");
        js.append("element.textContent=value;");
        js.append("break;");
        js.append("};");

        js.append("return newElement;");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }
}
