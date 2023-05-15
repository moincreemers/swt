package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class UndoEventHandler extends EventHandler {
    public static final String NAME = "onUndo";
    public static final String EVENT_NAME = "undo";
    public UndoEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
