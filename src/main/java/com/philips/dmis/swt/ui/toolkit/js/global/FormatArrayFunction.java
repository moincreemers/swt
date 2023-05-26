package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ArrayAppearanceType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.pages.IsPageModuleMember;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class FormatArrayFunction implements JsFunction, IsPageModuleMember {
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
        js.append("(element,value,arrayFormat)=>{");

        js.append("if(value==undefined||arrayFormat==undefined||value==null||value==''||!Array.isArray(value)){");
        js.append("element.textContent=value;");
        js.append("return element;");
        js.append("};");

        js.append("var newElement=element;");

        js.append("switch(arrayFormat.appearance){");
        js.append("case '%s':", ArrayAppearanceType.TEXT);
        js.append("element.textContent=value.join('');");
        js.append("break;");
        js.append("case '%s':", ArrayAppearanceType.FIRST);
        js.append("element.textContent=value.length==0?'':value[0];");
        js.append("break;");
        js.append("case '%s':", ArrayAppearanceType.LAST);
        js.append("element.textContent=value.length==0?'':value[value.length-1];");
        js.append("break;");
        js.append("case '%s':", ArrayAppearanceType.JOIN);
        js.append("element.textContent=value.join(arrayFormat.separator);");
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
