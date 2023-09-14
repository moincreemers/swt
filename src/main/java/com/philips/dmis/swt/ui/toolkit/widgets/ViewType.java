package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum ViewType implements HasDefault<ViewType> {
    DEFAULT("", "", false),
    DIALOG("tk-dlg-outer", "tk-dlg-inner", true),
    SIDEBAR_DIALOG("tk-sb-outer", "tk-sb-inner", true),

    ;

    final String classNameOuter;
    final String classNameInner;
    final boolean hideScrollbar;

    ViewType(String classNameOuter, String classNameInner, boolean hideScrollbar) {
        this.classNameOuter = classNameOuter;
        this.classNameInner = classNameInner;
        this.hideScrollbar = hideScrollbar;
    }

    public String getClassNameOuter() {
        return classNameOuter;
    }

    public String getClassNameInner() {
        return classNameInner;
    }

    public boolean isHideScroll() {
        return hideScrollbar;
    }

    @Override
    public ViewType getDefault() {
        return ViewType.DEFAULT;
    }
}
