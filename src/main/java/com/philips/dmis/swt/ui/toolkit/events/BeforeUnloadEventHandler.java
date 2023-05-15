package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class BeforeUnloadEventHandler extends EventHandler {
    public static final String NAME = "onBeforeUnload";
    public static final String EVENT_NAME = "beforeunload";
    public BeforeUnloadEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
