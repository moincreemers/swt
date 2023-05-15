package com.philips.dmis.swt.ui.toolkit.dto;

public enum FormatMatcherType implements HasValue {
    BASIC("basic"),
    BEST_FIT("best fit"),

    ;

    final String value;

    FormatMatcherType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
