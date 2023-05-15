package com.philips.dmis.swt.ui.toolkit.dto;

public enum NotationType implements HasValue {
    STANDARD("standard"),
    SCIENTIFIC("scientific"),
    ENGINEERING("engineering"),
    COMPACT("compact"),

    ;

    final String value;

    NotationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
