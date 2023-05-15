package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ActivateEventHandler extends EventHandler {
    public static final String NAME = "onActivate";
    public static final String EVENT_NAME = "activate";
    public ActivateEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
