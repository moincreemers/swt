package com.philips.dmis.swt.ui.toolkit.dto;

public enum DayType implements HasValue {
    NUMERIC("numeric"), TWO_DIGIT("2-digit"),

    ;

    final String value;

    DayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
