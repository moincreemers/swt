package com.philips.dmis.swt.ui.toolkit.widgets;

public enum SingleChoiceAppearance {
    DEFAULT(""),
    INLINE("tk-sc-inline"),
    TABS("tk-sc-tabs"),

    ;

    final String className;

    SingleChoiceAppearance(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
