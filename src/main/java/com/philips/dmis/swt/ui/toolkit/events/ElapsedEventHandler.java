package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ElapsedEventHandler extends EventHandler {
    public static final String NAME = "onElapsed";
    public static final String EVENT_NAME = "elapsed";
    public ElapsedEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
