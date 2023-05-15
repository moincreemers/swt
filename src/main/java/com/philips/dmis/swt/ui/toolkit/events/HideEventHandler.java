package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class HideEventHandler extends EventHandler {
    public static final String NAME = "onHide";
    public static final String EVENT_NAME = "hide";
    public HideEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
