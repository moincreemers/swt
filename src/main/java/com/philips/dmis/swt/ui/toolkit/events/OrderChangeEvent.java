package com.philips.dmis.swt.ui.toolkit.events;

public class OrderChangeEvent extends CustomEvent {
    public OrderChangeEvent() {
        super(OrderChangeEventHandler.EVENT_NAME);
    }
}
