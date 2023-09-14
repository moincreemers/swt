package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum AuthenticationType implements HasDefault<AuthenticationType> {
    NONE,
    BEARER_JWT;

    @Override
    public AuthenticationType getDefault() {
        return NONE;
    }
}
