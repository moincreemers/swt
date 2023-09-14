package com.philips.dmis.swt.ui.toolkit.css;

public class CssLiteral extends CssValue {
    private final String value;

    public CssLiteral(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
