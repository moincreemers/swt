package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class InputEventHandler extends EventHandler {
    public static final String NAME = "onInput";
    public static final String EVENT_NAME = "input";
    public InputEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
