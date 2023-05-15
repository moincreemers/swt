package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ChangeEventHandler extends EventHandler {
    public static final String NAME = "onChange";
    public static final String EVENT_NAME = "change";
    public ChangeEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
