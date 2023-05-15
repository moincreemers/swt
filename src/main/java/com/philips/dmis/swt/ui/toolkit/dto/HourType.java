package com.philips.dmis.swt.ui.toolkit.dto;

public enum HourType implements HasValue {
    NUMERIC("numeric"), TWO_DIGIT("2-digit"),

    ;

    final String value;

    HourType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
