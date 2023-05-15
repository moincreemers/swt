package com.philips.dmis.swt.ui.toolkit.dto;

public enum WeekDayType implements HasValue {
    NARROW("narrow"), SHORT("short"), LONG("long"),

    ;

    final String value;

    WeekDayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
