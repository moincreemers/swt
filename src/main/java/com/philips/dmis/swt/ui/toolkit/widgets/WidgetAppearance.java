package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum WidgetAppearance implements HasDefault<WidgetAppearance> {
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
    POSITION_TOP_LEFT("tk-position-top-left"),
    POSITION_TOP_RIGHT("tk-position-top-right"),
    POSITION_BOTTOM_LEFT("tk-position-bottom-left"),
    POSITION_BOTTOM_RIGHT("tk-position-bottom-right"),
    DIM_ON_HOVER("tk-dim-on-hover"),
    SHOW_ON_HOVER("tk-show-on-hover"),

    ;

    final String className;

    WidgetAppearance(String className) {
        this.className = className;
    }

    @Override
    public WidgetAppearance getDefault() {
        return WidgetAppearance.DEFAULT;
    }
}
