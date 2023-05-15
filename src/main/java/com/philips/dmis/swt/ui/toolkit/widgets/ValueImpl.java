package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;

import java.util.Map;

public class ValueImpl<T extends Widget> extends NameImpl<T> implements
        HasValue<T> {
    private String value = "";

    public ValueImpl(T widget) {
        super(widget);
    }

    @Override
    public HasValue<T> getValueImpl() {
        return this;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public T setValue(String value) {
        if (value == null) {
            value = "";
        }
        this.value = value;
        return widget;
    }

    @Override
    public T onChange(ChangeEventHandler eventHandler) {
        widget.getEventHandlers().add(eventHandler);
        return widget;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (value != null && !value.isEmpty()) {
            if (widget instanceof HtmlFrame || widget instanceof HtmlImageButton) {
                htmlAttributes.put("src", value);
            } else {
                htmlAttributes.put("value", value);
            }
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
