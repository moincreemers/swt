package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class AfterPrintEventHandler extends EventHandler {
    public static final String NAME = "onAfterPrint";
    public static final String EVENT_NAME = "afterprint";
    public AfterPrintEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
