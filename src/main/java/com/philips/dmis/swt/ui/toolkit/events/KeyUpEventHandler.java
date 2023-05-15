package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class KeyUpEventHandler extends EventHandler {
    public static final String NAME = "onKeyUp";
    public static final String EVENT_NAME = "keyup";
    public KeyUpEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
