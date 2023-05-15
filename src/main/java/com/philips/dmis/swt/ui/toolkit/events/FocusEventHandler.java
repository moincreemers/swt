package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class FocusEventHandler extends EventHandler {
    public static final String NAME = "onFocus";
    public static final String EVENT_NAME = "focus";
    public FocusEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
