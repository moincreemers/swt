package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;

public class ColorSchemeChangeEventHandler extends EventHandler {
    public static final String NAME = "onColorSchemeChange";
    public static final String EVENT_NAME = "colorschemechange";

    public ColorSchemeChangeEventHandler(Statement... statements) {
        super(NAME, statements);
    }

    public static ColorSchemeChangeEventHandler getDefaultHandler() {
        return new ColorSchemeChangeEventHandler(
                M.SetStylesheetDisabled(V.Const(Constants.CSS_DARK),
                        V.Is(
                                V.GetEvent(ColorSchemeChangeCustomEvent.COLOR_SCHEME),
                                V.Const(ColorSchemeChangeCustomEvent.SCHEME_LIGHT)))
        );
    }
}
