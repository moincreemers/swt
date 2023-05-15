package com.philips.dmis.swt.ui.toolkit.dto;

public enum HourCycleType implements HasValue {
    H11("h11"),
    H12("h12"),
    H23("h23"),
    H24("h24"),

    ;

    final String value;

    HourCycleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
