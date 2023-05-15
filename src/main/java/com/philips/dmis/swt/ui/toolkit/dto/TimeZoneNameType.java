package com.philips.dmis.swt.ui.toolkit.dto;

public enum TimeZoneNameType implements HasValue {
    SHORT("short"), LONG("long"),

    ;

    final String value;

    TimeZoneNameType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
