package com.philips.dmis.swt.ui.toolkit.dto;

public enum CurrencyDisplayType implements HasValue {
    SYMBOL("symbol"),
    NARROW_SYMBOL("narrowSymbol"),
    CODE("code"),
    NAME("name"),

    ;

    final String value;

    CurrencyDisplayType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
