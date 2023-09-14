package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum TargetType implements HasDefault<TargetType> {
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

    @Override
    public TargetType getDefault() {
        return TargetType.BLANK;
    }
}
