package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.FormatCodeFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.TextElementIdVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.TextFormatVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasText;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.TextFormatType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetTextFunction implements JsFunction {
    public static final String ID = "setText";

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
        js.append("(id,text)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        //js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("if(implements.includes('%s')){", HasText.class.getSimpleName()); // if
        js.append("const textElement=document.getElementById(widget.%s);", TextElementIdVariable.ID);
        js.append("if(%s(text)||Array.isArray(text)){",
                JsGlobalModule.getQualifiedId(IsObjectFunction.class));
        js.append("textElement.textContent=JSON.stringify(text,null,4);");
        js.append("}else if(widget.%s=='%s'){", TextFormatVariable.ID, TextFormatType.JSON.name());
        js.append("textElement.textContent=JSON.stringify(JSON.valueOf(text),null,4);");
        js.append("}else if(widget.%s=='%s'){", TextFormatVariable.ID, TextFormatType.JAVA_AND_JS.name());
        js.append("textElement.textContent=%s(text);",
                JsGlobalModule.getQualifiedId(FormatCodeFunction.class));
        js.append("}else{");

        // NOTE: careful
//        js.append("textElement.innerHTML=%s(text);",
//                JsGlobalModule.getQualifiedId(SanitizeHtmlFunction.class));

        js.append("textElement.setHTML(text,{sanitizer:new Sanitizer()});");

        js.append("}");
        js.append("element.setAttribute('tk-has-text',textElement.textContent.length!=0?'true':'false');");
        js.append("};"); // end if

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("text", JsType.STRING));
    }
}
