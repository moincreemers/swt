package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class DeactivateEventHandler extends EventHandler {
    public static final String NAME = "onDeactivate";
    public static final String EVENT_NAME = "deactivate";
    public DeactivateEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
