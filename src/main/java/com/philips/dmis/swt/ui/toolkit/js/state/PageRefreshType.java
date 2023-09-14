package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum PageRefreshType implements HasDefault<PageRefreshType> {
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

    @Override
    public PageRefreshType getDefault() {
        return SHOW;
    }
}
