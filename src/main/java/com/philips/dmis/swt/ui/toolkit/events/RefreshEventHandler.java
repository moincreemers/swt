package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class RefreshEventHandler extends EventHandler {
    public static final String NAME = "onRefresh";
    public static final String EVENT_NAME = "refresh";
    public RefreshEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
