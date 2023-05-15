package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ButtonType {
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
}
