package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class NameImpl<T extends Widget> implements HasName<T> {
    protected final T widget;
    private String name = "";

    public NameImpl(T widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasName<T> getNameImpl() {
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public T setName(String name) {
        if (name == null) {
            name = "";
        }
        this.name = name;
        return widget;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (name != null && !name.isEmpty()) {
            htmlAttributes.put("name", name);
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
