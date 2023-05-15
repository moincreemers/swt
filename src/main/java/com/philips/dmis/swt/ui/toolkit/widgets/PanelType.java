package com.philips.dmis.swt.ui.toolkit.widgets;

public enum PanelType {
    DEFAULT("", "d"),

    PADDED("tk-panel-padded", "p"),
    BORDERED("tk-panel-bordered", "b"),
    TOOLBAR("tk-panel-toolbar", "t"),
    TAB_PAGE("tk-panel-tabpage", "tp"),

    PAGE_HEADER("tk-panel-page-header", "h"),
    PAGE_FOOTER("tk-panel-page-footer", "f"),
    NAV_LEFT("tk-panel-nav-left", "l"),
    NAV_RIGHT("tk-panel-nav-right", "r"),

    BANNER("tk-panel-banner", "b"),
    SUCCESS("tk-panel-success", "s"),
    INFO("tk-panel-info", "i"),
    WARNING("tk-panel-warning", "w"),
    ERROR("tk-panel-error", "e"),

    GROUP("tk-panel-group", "g"),


    ;

    final String shortName;
    final String className;

    PanelType(String className, String shortName) {
        this.className = className;
        this.shortName = shortName;
    }

    public String getClassName() {
        return className;
    }

    public String getShortName() {
        return shortName;
    }
}
