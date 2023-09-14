package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum CaptureType implements HasDefault<CaptureType> {
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

    @Override
    public CaptureType getDefault() {
        return UNSPECIFIED;
    }
}
