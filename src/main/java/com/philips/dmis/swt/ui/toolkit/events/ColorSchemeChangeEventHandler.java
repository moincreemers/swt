package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.Statement;

public class ColorSchemeChangeEventHandler extends EventHandler {
    public static final String NAME = "onColorSchemeChange";
    public static final String EVENT_NAME = "colorschemechange";
    public ColorSchemeChangeEventHandler(Statement... statements) {
        super(NAME, statements);
    }
}
