package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.events.OrderChangeEventHandler;

import java.util.Map;

public interface HasOrderingControls<T extends Widget> extends HasStaticHTML {
    HasOrderingControls<T> getOrderingControlsImpl();

    T addOrder(String source, Order order);

    Map<String, Order> getOrder();

    boolean isMultiLevel();

    T setMultiLevel(boolean multiLevel);

    T onOrderChange(OrderChangeEventHandler eventHandler);
}
