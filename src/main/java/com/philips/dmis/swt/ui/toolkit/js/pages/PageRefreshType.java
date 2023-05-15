package com.philips.dmis.swt.ui.toolkit.js.pages;

public enum PageRefreshType {
    INIT(JsPagesModule.REASON_INIT),
    SHOW(JsPagesModule.REASON_SHOW),
    MANUAL(""),

    ;

    final String reason;


    PageRefreshType(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
