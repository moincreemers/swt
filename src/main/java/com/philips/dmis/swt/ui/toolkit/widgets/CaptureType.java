package com.philips.dmis.swt.ui.toolkit.widgets;

public enum CaptureType {
    UNSPECIFIED(""),
    USER("user"),
    ENVIRONMENT("environment"),

    ;

    final String value;

    CaptureType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
