package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class LanguageChangeEventHandler extends EventHandler {
    public static final String NAME = "onLanguageChange";
    public static final String EVENT_NAME = "languagechange";
    public LanguageChangeEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
