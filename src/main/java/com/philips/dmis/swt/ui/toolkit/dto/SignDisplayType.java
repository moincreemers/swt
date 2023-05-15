package com.philips.dmis.swt.ui.toolkit.dto;

public enum SignDisplayType implements HasValue {
    AUTO("auto"),
    ALWAYS("always"),
    EXCEPT_ZERO("exceptZero"),
    NEGATIVE("negative"),
    NEVER("never"),

    ;

    final String value;

    SignDisplayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
