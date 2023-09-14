package com.philips.dmis.swt.ui.toolkit.css;

public enum CssUnit {
    PIXEL(false, "px"),
    POINT(false, "pt"),
    PICA(false, "pc"),
    CENTIMETER(false, "cm"),
    MILLIMETER(false, "mm"),
    INCH(false, "in"),

    EM(true, "em"),
    REM(true, "rem"),
    EX(true, "ex"),
    VW(true, "vw"),
    VH(true, "vh"),
    VMIN(true, "vmin"),
    VMAX(true, "vmax"),
    CHARACTER(true, "ch"),
    PERCENT(true, "%"),
    FRACTION(true, "fr"),

    ;

    private final boolean relative;
    private final String value;

    CssUnit(boolean relative, String value) {
        this.relative = relative;
        this.value = value;
    }

    public boolean isRelative() {
        return relative;
    }

    public String getValue() {
        return value;
    }

    public static String removeUnit(String expression, CssUnit unit) {
        if (expression.endsWith(unit.value)) {
            return expression.substring(0, expression.length() - unit.value.length());
        }
        throw new IllegalArgumentException("expression \"" + expression + "\" does not match CSS unit: " + unit.name());
    }
}
