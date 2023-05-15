package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class ReadonlyImpl implements HasReadonly {
    private final Widget widget;
    private boolean readonly;

    public ReadonlyImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasReadonly getReadonlyImpl() {
        return this;
    }

    @Override
    public boolean getReadonly() {
        return readonly;
    }

    @Override
    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (readonly) {
            htmlAttributes.put("readonly", "");
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
