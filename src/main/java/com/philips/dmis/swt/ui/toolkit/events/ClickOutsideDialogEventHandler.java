package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ClickOutsideDialogEventHandler extends EventHandler {
    public static final String NAME = "onClickOutsideDialog";
    public static final String EVENT_NAME = "clickoutsidedialog";
    public ClickOutsideDialogEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
