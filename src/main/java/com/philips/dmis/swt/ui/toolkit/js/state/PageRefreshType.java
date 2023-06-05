package com.philips.dmis.swt.ui.toolkit.js.state;

public enum PageRefreshType {
    INIT(JsStateModule.REASON_INIT),
    SHOW(JsStateModule.REASON_SHOW),
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
