package com.philips.dmis.swt.ui.toolkit.dto;

public enum MinuteType implements HasValue {
    NUMERIC("numeric"), TWO_DIGIT("2-digit"),

    ;

    final String value;

    MinuteType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
