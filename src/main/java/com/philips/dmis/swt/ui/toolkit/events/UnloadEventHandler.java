package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class UnloadEventHandler extends EventHandler {
    public static final String NAME = "onUnload";
    public static final String EVENT_NAME = "unload";
    public UnloadEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
