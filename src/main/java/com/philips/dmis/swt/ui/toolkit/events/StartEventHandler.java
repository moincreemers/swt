package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class StartEventHandler extends EventHandler {
    public static final String NAME = "onStart";
    public static final String EVENT_NAME = "start";
    public StartEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
