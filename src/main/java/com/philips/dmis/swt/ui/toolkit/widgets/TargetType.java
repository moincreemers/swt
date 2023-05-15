package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TargetType {
    SELF("_self"),
    BLANK("_blank"),
    PARENT("_parent"),
    TOP("_top"),

    ;

    final String value;

    TargetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
