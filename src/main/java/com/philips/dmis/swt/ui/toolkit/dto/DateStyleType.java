package com.philips.dmis.swt.ui.toolkit.dto;

public enum DateStyleType implements HasValue {
    FULL("full"),
    LONG("long"),
    MEDIUM("medium"),
    SHORT("short"),

    ;

    final String value;

    DateStyleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
