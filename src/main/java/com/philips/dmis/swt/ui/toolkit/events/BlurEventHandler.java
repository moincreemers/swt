package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class BlurEventHandler extends EventHandler {
    public static final String NAME = "onBlur";
    public static final String EVENT_NAME = "blur";
    public BlurEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
