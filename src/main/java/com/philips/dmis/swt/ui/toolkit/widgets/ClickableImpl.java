package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;

import java.util.Map;

public class ClickableImpl<T extends Widget> implements IsClickable<T> {
    private final T widget;

    public ClickableImpl(T widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public IsClickable<T> getClickableImpl() {
        return this;
    }

    @Override
    public T onClick(ClickEventHandler eventHandler) {
        widget.getEventHandlers().add(eventHandler);
        return widget;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
    }
}
