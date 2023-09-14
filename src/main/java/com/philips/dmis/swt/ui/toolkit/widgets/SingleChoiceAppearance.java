package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum SingleChoiceAppearance implements HasDefault<SingleChoiceAppearance> {
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

    @Override
    public SingleChoiceAppearance getDefault() {
        return SingleChoiceAppearance.DEFAULT;
    }
}
