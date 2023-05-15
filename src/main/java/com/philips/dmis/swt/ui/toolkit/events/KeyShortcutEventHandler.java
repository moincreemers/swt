package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class KeyShortcutEventHandler extends EventHandler {
    public static final String NAME = "onKeyShortcut";
    public static final String EVENT_NAME = "keyshortcut";
    public KeyShortcutEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
