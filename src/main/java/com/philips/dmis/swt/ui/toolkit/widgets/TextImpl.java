package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.html.HasConstantStorage;
import com.philips.dmis.swt.ui.toolkit.html.SanitizeHTML;

import java.util.ArrayList;
import java.util.Map;

public class TextImpl implements HasText {
    protected final Widget widget;
    protected TextFormatType textFormatType;
    protected String text;

    public TextImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasText getTextImpl() {
        return this;
    }

    @Override
    public TextFormatType getTextFormat() {
        return textFormatType;
    }

    @Override
    public void setTextFormat(TextFormatType textFormatType) {
        this.textFormatType = textFormatType;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        java.util.List<String> classNames = new ArrayList<>();
        classNames.add(widget.getId());
        classNames.add(HasText.CSS_CLASS_TEXT);
        String token = "";
        if (!(text == null || text.isEmpty())) {
            classNames.add(HasConstantStorage.CSS_CLASS_GLOBAL);
            // NOTE: careful
            token = toolkit.registerConstant(SanitizeHTML.secureHTML(text));
        }
        html.append(String.format("<span id=\"%s\" class=\"%s\" %s=\"%s\"></span>",
                HasText.getTextId(widget.getId()),
                String.join(" ", classNames),
                HasConstantStorage.HTML_ATTR_TOKEN,
                token));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
    }


    //    @Override
//    protected void renderInnerHtmlText(Toolkit toolkit, StringBuffer html) {
//        if (!(text == null || text.isEmpty())) {
//            // NOTE: careful
//            if (textFormat == TextFormat.TEXT) {
//                html.append(StringUtils.encodeHtml(text));
//            }
//            if (textFormat == TextFormat.JSON) {
//                try {
//                    JsonNode j = OBJECT_MAPPER.readTree(text);
//                    html.append(j.toPrettyString());
//                } catch (JsonProcessingException e) {
//                    html.append(e.getMessage());
//                }
//            }
//            if (textFormat == TextFormat.JAVA_AND_JS) {
//                html.append(JavaCodeFormatter.format(text));
//            }
//        }
//    }
}
