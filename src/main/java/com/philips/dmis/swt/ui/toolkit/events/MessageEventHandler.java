package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class MessageEventHandler extends EventHandler {
    public static final String NAME = "onMessage";
    public static final String EVENT_NAME = "message";
    public MessageEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
