package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class OrderChangeEventHandler extends EventHandler {
    public static final String NAME = "onOrderChange";
    public static final String EVENT_NAME = "orderchange";
    public OrderChangeEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
