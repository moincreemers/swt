package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class PageVariableChangeEventHandler extends EventHandler {
    public static final String NAME = "onPageVariableChange";
    public static final String EVENT_NAME = "pagevariablechange";
    public PageVariableChangeEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
