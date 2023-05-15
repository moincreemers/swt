package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ResponseEventHandler extends EventHandler {
    public static final String NAME = "onResponse";
    public static final String EVENT_NAME = "response";
    public ResponseEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
