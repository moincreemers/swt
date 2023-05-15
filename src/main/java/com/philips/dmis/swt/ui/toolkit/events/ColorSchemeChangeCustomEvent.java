package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

import java.util.Map;

public class ColorSchemeChangeCustomEvent extends CustomEvent {
    public static final String SCHEME_LIGHT = "light";
    public static final String SCHEME_DARK = "dark";
    public static final ValueStatement COLOR_SCHEME = V.Const("colorScheme");
    private String colorScheme;

    public ColorSchemeChangeCustomEvent(String colorScheme) {
        super(ColorSchemeChangeEventHandler.EVENT_NAME);
        this.colorScheme = colorScheme;
    }

    @Override
    public void getProperties(Map<String, Object> properties) {
        properties.put("colorScheme", colorScheme);
    }
}
