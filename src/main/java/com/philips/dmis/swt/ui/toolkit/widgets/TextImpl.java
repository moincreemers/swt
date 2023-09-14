package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.html.HasConstantStorage;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.ArrayList;
import java.util.Map;

@PageXmlElement({"textFormat"})
public class TextImpl implements HasText {
    protected final Widget widget;
    protected TextFormatType textFormatType = TextFormatType.TEXT;
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
    public boolean isText() {
        return this.text != null && !this.text.isEmpty();
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        htmlAttributes.put("tk-has-text", isText() ? "true" : "false");
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        java.util.List<String> classNames = new ArrayList<>();
        classNames.add("${id}");
        classNames.add(HasText.CSS_CLASS_TEXT);
        String token = "";
        if (isText()) {
            classNames.add(HasConstantStorage.CSS_CLASS_GLOBAL);
            // NOTE: careful
            // todo: base64 encode text?
            token = toolkit.registerConstant(text);
        }
        html.append(String.format("<span id=\"${id}%s\" class=\"%s\" %s=\"%s\"></span>",
                HasText.getTextId(""),
                String.join(" ", classNames),
                HasConstantStorage.HTML_ATTR_TOKEN,
                token));
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.STRING;
    }
}
