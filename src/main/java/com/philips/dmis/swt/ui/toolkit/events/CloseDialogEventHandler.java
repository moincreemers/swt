package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class CloseDialogEventHandler extends EventHandler {
    public static final String NAME = "onCloseDialog";
    public static final String EVENT_NAME = "closedialog";
    public CloseDialogEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
