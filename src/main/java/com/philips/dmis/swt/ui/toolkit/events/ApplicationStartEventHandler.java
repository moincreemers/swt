package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ApplicationStartEventHandler extends EventHandler {
    public static final String NAME = "onApplicationStart";
    public static final String EVENT_NAME = "applicationstart";
    public ApplicationStartEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
