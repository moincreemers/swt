package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class RemoveEventHandler extends EventHandler {
    public static final String NAME = "onRemove";
    public static final String EVENT_NAME = "remove";
    public RemoveEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
