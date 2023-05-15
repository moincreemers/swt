package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class MultipleImpl implements HasMultiple {
    private final Widget widget;
    private boolean multiple;

    public MultipleImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasMultiple getMultipleImpl() {
        return this;
    }

    @Override
    public boolean getMultiple() {
        return multiple;
    }

    @Override
    public void setMultiple(boolean readonly) {
        this.multiple = readonly;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (multiple) {
            htmlAttributes.put("multiple", "");
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
