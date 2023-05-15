package com.philips.dmis.swt.ui.toolkit.dto;

public enum UnitDisplayType implements HasValue {
    LONG("long"),
    SHORT("short"),
    NARROW("narrow"),

    ;

    final String value;

    UnitDisplayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
