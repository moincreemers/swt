//package com.philips.dmis.sso.ui.toolkit.html;
//
//import com.philips.dmis.sso.ui.toolkit.Toolkit;
//import com.philips.dmis.sso.ui.toolkit.js.WidgetType;
//import com.philips.dmis.sso.ui.toolkit.widgets.Widget;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class WidgetHtml {
//    final Toolkit toolkit;
//    final StringBuffer html;
//    final Widget widget;
//    final WidgetType widgetType;
//
//    public WidgetHtml(Toolkit toolkit, StringBuffer html, Widget widget, WidgetType widgetType) {
//        this.toolkit = toolkit;
//        this.html = html;
//        this.widget = widget;
//        this.widgetType = widgetType;
//    }
//
//    public void build() {
//        Map<String, String> htmlAttributes = new HashMap<>();
//        widget.getHtmlAttributes(htmlAttributes);
//        String htmlTag = "";
//        switch (widgetType) {
//            case PAGE:
//                htmlTag = widget.getHtmlTag("div");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case BUTTON:
//                htmlTag = widget.getHtmlTag("button");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case CHECKBOX:
//                htmlTag = widget.getHtmlTag("input");
//                html.append(String.format("<%s type='checkbox' id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case HEADING:
//                htmlTag = widget.getHtmlTag("h1");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case LABEL:
//                htmlTag = widget.getHtmlTag("label");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case LINK:
//                htmlTag = widget.getHtmlTag("a");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case LIST:
//                htmlTag = widget.getHtmlTag("ul");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case LISTBOX:
//                htmlTag = widget.getHtmlTag("select");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case MULTIPLE_CHOICE:
//                htmlTag = widget.getHtmlTag("ul");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case SINGLE_CHOICE:
//                htmlTag = widget.getHtmlTag("ul");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case PANEL:
//                htmlTag = widget.getHtmlTag("div");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case PARAGRAPH:
//                htmlTag = widget.getHtmlTag("p");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//            case PREFORMATTED:
//                htmlTag = widget.getHtmlTag("pre");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//            case TEXTBOX:
//                htmlTag = widget.getHtmlTag("input");
//                html.append(String.format("<%s type='text' id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//            case PASSWORD:
//                htmlTag = widget.getHtmlTag("input");
//                html.append(String.format("<%s type='password' id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case DATA:
//                htmlTag = widget.getHtmlTag("div");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case QUERY_SERVICE:
//                htmlTag = widget.getHtmlTag("div");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case UPDATE_SERVICE:
//                htmlTag = widget.getHtmlTag("div");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case GRID:
//                htmlTag = widget.getHtmlTag("div");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case TABLE:
//                htmlTag = widget.getHtmlTag("table");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case TABLE_HEADER:
//                htmlTag = widget.getHtmlTag("thead");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case TABLE_BODY:
//                htmlTag = widget.getHtmlTag("tbody");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case TABLE_FIXED_BODY:
//                htmlTag = widget.getHtmlTag("tbody");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                html.append("<tr><td>");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append("</td></tr>");
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case TABLE_FOOTER:
//                htmlTag = widget.getHtmlTag("tfoot");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case TABLE_CAPTION:
//                //TableCaption tableCaption = (TableCaption) widget;
//                htmlTag = widget.getHtmlTag("caption");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case CODE:
//                htmlTag = widget.getHtmlTag("script");
//                html.append(String.format("<%s id='%s' language='javascript' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case STYLE:
//                htmlTag = widget.getHtmlTag("style");
//                html.append(String.format("<%s id='%s'",
//                        htmlTag,
//                        widget.getId()));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//
//            case FRAME:
//                htmlTag = widget.getHtmlTag("iframe");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//
//            case TIMER:
//                htmlTag = widget.getHtmlTag("div");
//                html.append(String.format("<%s id='%s' class='%s'",
//                        htmlTag,
//                        widget.getId(),
//                        getValueOfClassNames(widget)));
//                renderHtmlAttr(htmlAttributes, html);
//                html.append(">");
//                widget.renderStaticInnerHtml(toolkit, html);
//                html.append(String.format("</%s>", htmlTag));
//                break;
//        }
//    }
//
//    private void renderHtmlAttr(Map<String, String> htmlAttributes, StringBuffer html) {
//        for (String name : htmlAttributes.keySet()) {
//            html.append(" ");
//            html.append(name);
//            html.append("='");
//            html.append(htmlAttributes.get(name));
//            html.append("'");
//        }
//    }
//
//    private String getValueOfClassNames(Widget widget) {
//        StringBuffer s = new StringBuffer();
//        s.append(widget.getWidgetType().name().toLowerCase());
//        for (String className : widget.getClassNames()) {
//            s.append(" ");
//            s.append(className);
//        }
//        return s.toString();
//    }
//}
