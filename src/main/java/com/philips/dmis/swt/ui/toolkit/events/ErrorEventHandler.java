package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ErrorEventHandler extends EventHandler {
    public static final String NAME = "onError";
    public static final String EVENT_NAME = "error";
    public ErrorEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
