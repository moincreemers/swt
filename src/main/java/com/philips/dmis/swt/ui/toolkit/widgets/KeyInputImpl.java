package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.GlobalEvents;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyUpEventHandler;

import java.util.Map;

public class KeyInputImpl<T extends Widget> implements HasKeyInput {
    private final T widget;

    public KeyInputImpl(T widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasKeyInput<T> getKeyInputImpl() {
        return this;
    }

    @Override
    public T onKeyDown(KeyDownEventHandler eventHandler) {
        eventHandler.setWidgetId(widget.getId());
        GlobalEvents.onKeyDown();
        widget.getEventHandlers().add(eventHandler);
        return widget;
    }

    @Override
    public T onKeyPress(KeyPressEventHandler eventHandler) {
        eventHandler.setWidgetId(widget.getId());
        GlobalEvents.onKeyPress();
        widget.getEventHandlers().add(eventHandler);
        return widget;
    }

    @Override
    public T onKeyUp(KeyUpEventHandler eventHandler) {
        eventHandler.setWidgetId(widget.getId());
        GlobalEvents.onKeyUp();
        widget.getEventHandlers().add(eventHandler);
        return widget;
    }

    @Override
    public T onInput(InputEventHandler eventHandler) {
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
