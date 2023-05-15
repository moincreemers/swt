package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class RedoEventHandler extends EventHandler {
    public static final String NAME = "onRedo";
    public static final String EVENT_NAME = "redo";
    public RedoEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
