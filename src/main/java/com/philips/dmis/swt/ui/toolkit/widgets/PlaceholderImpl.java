package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("placeholder")
public class PlaceholderImpl implements HasPlaceholder {
    private final Widget widget;
    private String placeholder;

    public PlaceholderImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasPlaceholder getPlaceholderImpl() {
        return this;
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public void setPlaceholder(String pattern) {
        this.placeholder = pattern;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (placeholder != null && !placeholder.isEmpty()) {
            htmlAttributes.put("placeholder", placeholder);
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
