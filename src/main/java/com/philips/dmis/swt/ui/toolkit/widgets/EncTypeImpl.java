package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("encType")
public class EncTypeImpl implements HasEncType {
    private final Widget widget;
    private String encType;
    private boolean link, button, form;

    public EncTypeImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
        link = widget instanceof HtmlLink;
        button = widget instanceof HtmlButton;
        form = widget instanceof HtmlForm;
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasEncType getEncTypeImpl() {
        return this;
    }

    @Override
    public String getEncType() {
        return encType;
    }

    @Override
    public void setEncType(String encType) {
        this.encType = encType;
    }

    @Override
    public void setEncType(MimeType mimeType) {
        this.encType = mimeType.getValue();
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (encType != null && !encType.isEmpty()) {
            if(link) {
                htmlAttributes.put("type", encType);
            }
            if(button) {
                htmlAttributes.put("formenctype", encType);
            }
            if(form) {
                htmlAttributes.put("enctype", encType);
            }
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
