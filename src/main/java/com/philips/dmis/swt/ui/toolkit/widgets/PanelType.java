package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum PanelType implements HasDefault<PanelType> {
    DEFAULT(""),

    PADDED("tk-panel-padded"),
    BORDERED("tk-panel-bordered"),
    TOOLBAR("tk-panel-toolbar"),
    TAB_PAGE("tk-panel-tabpage"),

    PAGE_HEADER("tk-panel-page-header"),
    PAGE_FOOTER("tk-panel-page-footer"),
    NAV_LEFT("tk-panel-nav-left"),
    NAV_RIGHT("tk-panel-nav-right"),

    BANNER("tk-panel-banner"),
    SUCCESS("tk-panel-success"),
    INFO("tk-panel-info"),
    WARNING("tk-panel-warning"),
    ERROR("tk-panel-error"),

    GROUP("tk-panel-group"),


    ;

    final String className;

    PanelType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public PanelType getDefault() {
        return DEFAULT;
    }
}
