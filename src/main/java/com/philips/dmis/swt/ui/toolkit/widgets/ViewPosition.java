package com.philips.dmis.swt.ui.toolkit.widgets;

import java.util.ArrayList;
import java.util.List;

public enum ViewPosition {
    DEFAULT("", ""),

    DIALOG_TOP_LEFT("", "tk-dlg-top-left", ViewType.DIALOG),
    DIALOG_TOP_RIGHT("", "tk-dlg-top-right", ViewType.DIALOG),
    DIALOG_BOTTOM_LEFT("", "tk-dlg-bottom-left", ViewType.DIALOG),
    DIALOG_BOTTOM_RIGHT("", "tk-dlg-bottom-right", ViewType.DIALOG),
    DIALOG_CENTER("tk-dlg-outer-center", "tk-dlg-inner-middle", ViewType.DIALOG),

    SIDEBAR_LEFT("", "tk-sb-left", ViewType.SIDEBAR_DIALOG),
    SIDEBAR_TOP("", "tk-sb-top", ViewType.SIDEBAR_DIALOG),
    SIDEBAR_BOTTOM("", "tk-sb-bottom", ViewType.SIDEBAR_DIALOG),
    SIDEBAR_RIGHT("", "tk-sb-right", ViewType.SIDEBAR_DIALOG),

    ;

    final String classNameOuter;
    final String classNameInner;
    final java.util.List<ViewType> viewTypes = new ArrayList<>();

    ViewPosition(String classNameOuter, String classNameInner, ViewType... viewTypes) {
        this.classNameOuter = classNameOuter;
        this.classNameInner = classNameInner;
        if (viewTypes != null) {
            for (ViewType viewType : viewTypes) {
                this.viewTypes.add(viewType);
            }
        }
    }

    public String getClassNameOuter() {
        return classNameOuter;
    }

    public String getClassNameInner() {
        return classNameInner;
    }

    public List<ViewType> getViewTypes() {
        return viewTypes;
    }
}
