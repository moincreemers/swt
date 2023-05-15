package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

/**
 * Used as a callback by the html.RenderElementFunction.
 * This method is called when the widget (specified in this method as well) is created and added to the HTML DOM.
 */
public class RenderInnerHTMLFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "renderInnerHTML";
    private final Widget widget;
    private final WidgetType widgetType;

    public RenderInnerHTMLFunction(Widget widget) {
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
        parameters.add(JsParameter.getInstance("widgetSpec", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("element", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(widgetSpec,element)=>{");

        StringBuffer html = new StringBuffer();
        widget.renderStaticInnerHtml(toolkit, html);
        if (!html.isEmpty()) {
            if (html.indexOf("'") != -1) {
                // Rather than rely on escaping, the entire html is just not allowed
                // when a single quote is found.
                throw new JsRenderException(
                        "inner html contained single-quote (" + widget.getId() + ") " + html);
            }
            if (widgetType == WidgetType.STYLE) {
                // note: prevent the same font to be added to the page more than once to save space
                js.append("if(document.getElementsByClassName(element.classList.value).length<2){");
                js.append("element.textContent='%s'", html);
                js.append("};");
            } else {
                js.append("element.innerHTML='%s'", html);
            }
        }

        js.append("}");
    }
}
