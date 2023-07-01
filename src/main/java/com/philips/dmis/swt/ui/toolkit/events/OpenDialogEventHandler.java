package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class OpenDialogEventHandler extends EventHandler {
    public static final String NAME = "onOpenDialog";
    public static final String EVENT_NAME = "opendialog";
    public OpenDialogEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
