package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum ButtonType implements HasDefault<ButtonType> {
    DEFAULT("", "d"),
    PRIMARY("tk-button-primary", "p"),
    SUCCESS("tk-button-success", "s"),
    INFO("tk-button-info", "i"),
    WARNING("tk-button-warning", "w"),
    ERROR("tk-button-error", "e"),

    ;
    final String className;
    final  String shortName;

    ButtonType(String className, String shortName) {
        this.className = className;
        this.shortName = shortName;
    }

    public String getClassName() {
        return className;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public ButtonType getDefault() {
        return DEFAULT;
    }
}
