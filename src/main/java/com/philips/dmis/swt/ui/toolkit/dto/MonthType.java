package com.philips.dmis.swt.ui.toolkit.dto;

public enum MonthType implements HasValue {
    NUMERIC("numeric"), TWO_DIGIT("2-digit"),
    NARROW("narrow"), SHORT("short"), LONG("long"),

    ;

    final String value;

    MonthType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
