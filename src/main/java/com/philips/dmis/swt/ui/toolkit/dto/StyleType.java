package com.philips.dmis.swt.ui.toolkit.dto;

public enum StyleType implements HasValue {
    DECIMAL("decimal"),
    CURRENCY("currency"),
    PERCENT("percent"),
    UNIT("unit"),

    ;

    final String value;

    StyleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
