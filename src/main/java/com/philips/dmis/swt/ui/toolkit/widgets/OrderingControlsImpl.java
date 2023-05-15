package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.events.OrderChangeEventHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderingControlsImpl<T extends Widget> implements HasOrderingControls<T> {
    private final T widget;
    private final Map<String, Order> fields = new LinkedHashMap<>();
    private boolean multiLevel;

    public OrderingControlsImpl(T widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasOrderingControls<T> getOrderingControlsImpl() {
        return this;
    }

    @Override
    public T setOrder(String source, Order order) {
        fields.put(source, order);
        return widget;
    }

    @Override
    public Map<String, Order> getOrder() {
        return fields;
    }

    @Override
    public boolean isMultiLevel() {
        return multiLevel;
    }

    @Override
    public T setMultiLevel(boolean multiLevel) {
        this.multiLevel = multiLevel;
        return widget;
    }

    @Override
    public T onOrderChange(OrderChangeEventHandler eventHandler) {
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
