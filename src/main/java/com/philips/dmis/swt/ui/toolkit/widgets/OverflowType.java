package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum OverflowType implements HasDefault<OverflowType> {
    FIT_CONTENT("", false),
    FIXED_SIZE("tk-panel-fixed", true),

    ;

    public static final OverflowType DEFAULT = OverflowType.FIT_CONTENT;

    final String className;
    final boolean requiresSize;

    OverflowType(String className, boolean requiresSize) {
        this.className = className;
        this.requiresSize = requiresSize;
    }

    @Override
    public OverflowType getDefault() {
        return FIT_CONTENT;
    }
}
