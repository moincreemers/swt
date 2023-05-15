package com.philips.dmis.swt.ui.toolkit.dto;

public enum CompactDisplayType implements HasValue {
    SHORT("short"),
    LONG("long"),

    ;

    final String value;

    CompactDisplayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
