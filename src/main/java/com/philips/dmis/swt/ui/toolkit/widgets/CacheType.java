package com.philips.dmis.swt.ui.toolkit.widgets;

public enum CacheType {
    DISABLED,
    ENABLED,
    LOCAL_ONLY,
    BACKGROUND_REFRESH,

    ;

    public static CacheType DEFAULT = ENABLED;
}
