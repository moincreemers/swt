package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class OpenEventHandler extends EventHandler {
    public static final String NAME = "onOpen";
    public static final String EVENT_NAME = "open";
    public OpenEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
