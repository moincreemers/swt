package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum CacheType implements HasDefault<CacheType> {
    DISABLED,
    ENABLED,
    LOCAL_ONLY,
    BACKGROUND_REFRESH,

    ;

    public static CacheType DEFAULT = ENABLED;

    @Override
    public CacheType getDefault() {
        return ENABLED;
    }
}
