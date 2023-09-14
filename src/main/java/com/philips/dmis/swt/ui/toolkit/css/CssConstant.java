package com.philips.dmis.swt.ui.toolkit.css;

public class CssConstant extends CssValue {
    public static CssConstant EMPTY = new CssConstant();
    public static CssConstant AUTO = new CssConstant("auto");
    public static CssConstant FIT_CONTENT = new CssConstant("fit-content");
    public static CssConstant MAX_CONTENT = new CssConstant("max-content");
    public static CssConstant MIN_CONTENT = new CssConstant("min-content");
    public static CssConstant STRETCH = new CssConstant("stretch");

    private final String value;

    public CssConstant() {
        this(null);
    }

    public CssConstant(String value) {
        if (value == null) {
            value = "";
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
