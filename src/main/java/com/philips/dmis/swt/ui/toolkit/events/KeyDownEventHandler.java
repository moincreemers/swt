package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class KeyDownEventHandler extends EventHandler {
    public static final String NAME = "onKeyDown";
    public static final String EVENT_NAME = "keydown";
    public KeyDownEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
