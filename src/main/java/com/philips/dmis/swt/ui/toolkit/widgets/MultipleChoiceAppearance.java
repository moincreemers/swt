package com.philips.dmis.swt.ui.toolkit.widgets;

public enum MultipleChoiceAppearance {
    DEFAULT(""),
    INLINE("tk-mc-inline"),

    ;

    final String className;

    MultipleChoiceAppearance(String className) {
        this.className = className;
    }
}
