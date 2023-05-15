package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class OnlineEventHandler extends EventHandler {
    public static final String NAME = "onOnline";
    public static final String EVENT_NAME = "online";
    public OnlineEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
