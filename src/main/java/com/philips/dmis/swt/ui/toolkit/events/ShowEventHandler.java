package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ShowEventHandler extends EventHandler {
    public static final String NAME = "onShow";
    public static final String EVENT_NAME = "show";
    public ShowEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
