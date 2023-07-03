package com.philips.dmis.swt.ui.toolkit.widgets;

public enum CheckInputType {
    DEFAULT(""),
    BUTTON("tk-check-button"),

    ;

    final String className;

    CheckInputType(String className) {
        this.className = className;
    }
}
