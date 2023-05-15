package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class StopEventHandler extends EventHandler {
    public static final String NAME = "onStop";
    public static final String EVENT_NAME = "stop";
    public StopEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
