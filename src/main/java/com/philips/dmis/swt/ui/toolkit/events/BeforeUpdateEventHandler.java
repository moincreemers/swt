package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class BeforeUpdateEventHandler extends EventHandler {
    public static final String NAME = "onBeforeUpdate";
    public static final String EVENT_NAME = "beforeupdate";
    public BeforeUpdateEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
