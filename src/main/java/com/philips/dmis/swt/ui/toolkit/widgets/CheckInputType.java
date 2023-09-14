package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum CheckInputType implements HasDefault<CheckInputType> {
    DEFAULT(""),
    BUTTON("tk-check-button"),

    ;

    final String className;

    CheckInputType(String className) {
        this.className = className;
    }

    @Override
    public CheckInputType getDefault() {
        return DEFAULT;
    }
}
