package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class RequiredImpl implements HasRequired {
    private final Widget widget;
    private boolean required;

    public RequiredImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasRequired getRequiredImpl() {
        return this;
    }

    @Override
    public boolean getRequired() {
        return required;
    }

    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (required) {
            htmlAttributes.put("required", "");
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
