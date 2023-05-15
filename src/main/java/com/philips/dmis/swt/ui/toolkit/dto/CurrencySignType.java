package com.philips.dmis.swt.ui.toolkit.dto;

public enum CurrencySignType implements HasValue {
    STANDARD("standard"),
    ACCOUNTING("accounting"),

    ;

    final String value;

    CurrencySignType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
