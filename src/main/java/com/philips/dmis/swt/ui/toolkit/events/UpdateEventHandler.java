package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class UpdateEventHandler extends EventHandler {
    public static final String NAME = "onUpdate";
    public static final String EVENT_NAME = "update";
    public UpdateEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
