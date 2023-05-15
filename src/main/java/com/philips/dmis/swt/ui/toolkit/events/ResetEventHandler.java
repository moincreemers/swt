package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ResetEventHandler extends EventHandler {
    public static final String NAME = "onReset";
    public static final String EVENT_NAME = "reset";
    public ResetEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
