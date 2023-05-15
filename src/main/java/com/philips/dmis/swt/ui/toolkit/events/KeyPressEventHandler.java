package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class KeyPressEventHandler extends EventHandler {
    public static final String NAME = "onKeyPress";
    public static final String EVENT_NAME = "keypress";
    public KeyPressEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
