package com.philips.dmis.swt.ui.toolkit.dto;

public enum LocaleMatcherType implements HasValue {
    LOOKUP("lookup"),
    BEST_FIT("best fit"),

    ;

    final String value;

    LocaleMatcherType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
