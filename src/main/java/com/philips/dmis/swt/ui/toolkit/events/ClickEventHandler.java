package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ClickEventHandler extends EventHandler {
    public static final String NAME = "onClick";
    public static final String EVENT_NAME = "click";
    public ClickEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
