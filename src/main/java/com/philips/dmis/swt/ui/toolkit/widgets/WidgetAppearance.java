package com.philips.dmis.swt.ui.toolkit.widgets;

public enum WidgetAppearance {
    DEFAULT(""),
    ROUNDED_CORNERS("tk-rounded"),
    HARD_CORNERS("tk-hard"),
    BORDERED("tk-bordered"),
    FILL_BACKGROUND("tk-fill-bg"),
    TRANSPARENT_BACKGROUND("tk-trans-bg"),
    SMALL_FONT("tk-font-sm"),
    LARGE_FONT("tk-font-lg"),
    BLOCK("tk-block"),
    INLINE("tk-inline"),
    INLINE_BLOCK("tk-inline-block"),
    FIT_CONTENT("tk-fit-content"),
    FIT_PARENT_WIDTH("tk-fit-parent-width"),
    ALIGN_RIGHT("tk-align-right"),
    FLOAT_RIGHT("tk-float-right"),
    CENTER("tk-center"),

    ;

    final String className;

    WidgetAppearance(String className) {
        this.className = className;
    }
}
