package com.philips.dmis.swt.ui.toolkit.widgets;

public enum Overflow {
    FIT_CONTENT("", false),
    FIXED_SIZE("tk-panel-fixed", true),

    ;

    public static final Overflow DEFAULT = Overflow.FIT_CONTENT;

    final String className;
    final boolean requiresSize;

    Overflow(String className, boolean requiresSize) {
        this.className = className;
        this.requiresSize = requiresSize;
    }
}
