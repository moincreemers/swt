package com.philips.dmis.swt.ui.toolkit.dto;

public enum SecondType implements HasValue {
    NUMERIC("numeric"), TWO_DIGIT("2-digit"),

    ;

    final String value;

    SecondType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
