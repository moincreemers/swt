package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class OfflineEventHandler extends EventHandler {
    public static final String NAME = "onOffline";
    public static final String EVENT_NAME = "offline";
    public OfflineEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
