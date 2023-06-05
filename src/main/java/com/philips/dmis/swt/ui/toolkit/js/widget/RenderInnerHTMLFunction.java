package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.StaticInnerHtmlVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RenderInnerHTMLFunction implements JsFunction {
    public static final String ID = "renderInnerHTML";

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
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("element", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,element)=>{");
        js.trace(this);

        js.append("const childWidget=window[id];");
        js.append("const childWidgetType=childWidget.%s;", WidgetTypeVariable.ID);
        js.append("const staticInnerHtml=childWidget.%s;", StaticInnerHtmlVariable.ID);

        js.append("if(childWidgetType=='%s'){", WidgetType.STYLE.name());
        js.append("if(document.getElementsByClassName(element.classList.value).length<2){");
        js.append("element.textContent=staticInnerHtml;");
        js.append("};");
        js.append("}else{");
        js.append("element.innerHTML=staticInnerHtml;");
        js.append("};");

        js.append("}");
    }
}
