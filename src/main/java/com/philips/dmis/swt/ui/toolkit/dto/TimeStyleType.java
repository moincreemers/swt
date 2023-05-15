package com.philips.dmis.swt.ui.toolkit.dto;

public enum TimeStyleType implements HasValue {
    FULL("full"),
    LONG("long"),
    MEDIUM("medium"),
    SHORT("short"),

    ;

    final String value;

    TimeStyleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
