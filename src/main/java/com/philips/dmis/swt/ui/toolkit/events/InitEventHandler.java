package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class InitEventHandler extends EventHandler {
    public static final String NAME = "onInit";
    public static final String EVENT_NAME = "init";
    public InitEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
