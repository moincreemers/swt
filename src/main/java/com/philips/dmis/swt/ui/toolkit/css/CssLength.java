package com.philips.dmis.swt.ui.toolkit.css;

public class CssLength extends CssValue {
    public static final CssLength px(double value) {
        return new CssLength(value, CssUnit.PIXEL);
    }

    public static final CssLength pt(double value) {
        return new CssLength(value, CssUnit.POINT);
    }

    public static final CssLength pc(double value) {
        return new CssLength(value, CssUnit.PICA);
    }

    public static final CssLength cm(double value) {
        return new CssLength(value, CssUnit.CENTIMETER);
    }

    public static final CssLength mm(double value) {
        return new CssLength(value, CssUnit.MILLIMETER);
    }

    public static final CssLength in(double value) {
        return new CssLength(value, CssUnit.INCH);
    }

    public static final CssLength em(double value) {
        return new CssLength(value, CssUnit.EM);
    }

    public static final CssLength rem(double value) {
        return new CssLength(value, CssUnit.REM);
    }

    public static final CssLength ex(double value) {
        return new CssLength(value, CssUnit.EX);
    }

    public static final CssLength vw(double value) {
        return new CssLength(value, CssUnit.VW);
    }

    public static final CssLength vh(double value) {
        return new CssLength(value, CssUnit.VH);
    }

    public static final CssLength vMin(double value) {
        return new CssLength(value, CssUnit.VMIN);
    }

    public static final CssLength vMax(double value) {
        return new CssLength(value, CssUnit.VMAX);
    }

    public static final CssLength ch(double value) {
        return new CssLength(value, CssUnit.CHARACTER);
    }

    public static final CssLength percent(double value) {
        return new CssLength(value, CssUnit.PERCENT);
    }

    public static final CssLength fr(double value) {
        return new CssLength(value, CssUnit.FRACTION);
    }


    public static CssValue DEFAULT_BROWSER_DEFINED_WIDTH = new CssLength(300d, CssUnit.PIXEL);
    public static CssValue DEFAULT_BROWSER_DEFINED_HEIGHT = new CssLength(150d, CssUnit.PIXEL);

    public static CssValue DEFAULT_TAB_PAGE_WIDTH = CssConstant.EMPTY;
    public static CssValue DEFAULT_TAB_PAGE_HEIGHT = new CssLength(300d, CssUnit.PIXEL);

    private final double value;
    private final CssUnit unit;

    public CssLength() {
        value = Double.NaN;
        unit = CssUnit.REM;
    }

    public CssLength(double value, CssUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public CssUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        if (Double.isNaN(value)) {
            return "";
        }
        return ((value % 1.0d > 0) ?
                value : Double.valueOf(value).intValue()) + unit.getValue();
    }
}
