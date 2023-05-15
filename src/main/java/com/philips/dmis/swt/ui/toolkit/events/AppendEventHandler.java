package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class AppendEventHandler extends EventHandler {
    public static final String NAME = "onAppend";
    public static final String EVENT_NAME = "append";
    public AppendEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
