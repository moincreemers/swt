package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class BeforePrintEventHandler extends EventHandler {
    public static final String NAME = "onBeforePrint";
    public static final String EVENT_NAME = "beforeprint";
    public BeforePrintEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
