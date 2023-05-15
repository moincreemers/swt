package com.philips.dmis.swt.ui.toolkit.dto;

public enum EraType implements HasValue {
    NARROW("narrow"), SHORT("short"), LONG("long"),

    ;

    final String value;

    EraType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
