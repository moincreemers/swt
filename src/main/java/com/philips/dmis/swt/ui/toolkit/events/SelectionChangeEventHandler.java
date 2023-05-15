package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class SelectionChangeEventHandler extends EventHandler {
    public static final String NAME = "onSelectionChange";
    public static final String EVENT_NAME = "selectionchange";
    public SelectionChangeEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
