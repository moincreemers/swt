package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("focusable")
public class FocusableImpl implements IsFocusable {
    private final Widget widget;
    protected boolean focusable;

    public FocusableImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public IsFocusable getFocusableImpl() {
        return this;
    }

    @Override
    public boolean isFocusable() {
        return focusable;
    }

    @Override
    public void setFocusable(boolean focusable) {
        this.focusable = focusable;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (focusable) {
            htmlAttributes.put("tabindex", "0");
        }
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
    }
}
