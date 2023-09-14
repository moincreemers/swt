package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("src")
public class SrcImpl<T extends Widget> implements HasSrc<T> {
    private final T widget;
    private String src = DEFAULT_VALUE_SRC;

    public SrcImpl(T widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasSrc<T> getSrcImpl() {
        return this;
    }

    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public T setSrc(String src) {
        if (src == null) {
            src = DEFAULT_VALUE_SRC;
        }
        this.src = src;
        return widget;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (!src.isEmpty()) {
            htmlAttributes.put("src", src);
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
