package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum MultipleChoiceAppearance implements HasDefault<MultipleChoiceAppearance> {
    DEFAULT(""),
    INLINE("tk-mc-inline"),

    ;

    final String className;

    MultipleChoiceAppearance(String className) {
        this.className = className;
    }

    @Override
    public MultipleChoiceAppearance getDefault() {
        return DEFAULT;
    }
}
