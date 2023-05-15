package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasNumberedText;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetWidgetSpecFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "getWidgetSpec";
    private final Widget widget;
    private final WidgetType widgetType;

    public GetWidgetSpecFunction(Widget widget) {
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
        return JsType.OBJECT;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        // todo

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");

        js.append("var widgetSpec={");
        js.append("id:%s,", JsPagesModule.getId(widget, WidgetIdVariable.class));
        js.append("parentId:%s,", JsPagesModule.getId(widget, ParentWidgetIdVariable.class));
        js.append("widgetType:%s,", JsPagesModule.getId(widget, WidgetTypeVariable.class));
        js.append("htmlTag:'%s',", widget.getHtmlTag(widget.getWidgetType().getHtmlTag()));
        js.append("htmlAttributes:{%s},", getHtmlAttributes());

        // only for panels
        js.append("panelType:%s,", widgetType == WidgetType.PANEL
                ? JsPagesModule.getId(widget, PanelTypeVariable.class)
                : "null");

        // only pages
        js.append("innerDivId:'%s',", widgetType == WidgetType.PAGE
                ? Page.getInnerDivId(widget.getId())
                : "");

        js.append("isNumbered:%s,", (widget instanceof HasNumberedText)
                ? "true"
                : "false");

        js.append("appendElement:%s,", widgetType == WidgetType.PAGE ?
                "(widgetSpec,element)=>{document.body.append(element);}"
                : JsPagesModule.getQualifiedId(widget.getParent(), AppendElementFunction.class));

        js.append("renderInner:%s", JsPagesModule.getId(widget, RenderInnerHTMLFunction.class));
        js.append("};"); // end widgetSpec

        js.append("return widgetSpec;");

        js.append("}");
    }

//    private String getAttributes() {
//        Map<String, String> attributes = new LinkedHashMap<>();
//        widget.getAttributes(attributes);
//        return String.join(",",
//                attributes.entrySet().stream()
//                        .map(entry -> entry.getKey() + ":'" + entry.getValue() + "'")
//                        .toList());
//    }

    private String getHtmlAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        widget.getHtmlAttributes(attributes);
        return String.join(",",
                attributes.entrySet().stream()
                        .map(entry -> entry.getKey() + ":'" + entry.getValue() + "'")
                        .toList());
    }
}
