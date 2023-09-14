package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum FormMethodType implements HasDefault<FormMethodType> {
    GET,
    POST,
    DIALOG,
    ;

    @Override
    public FormMethodType getDefault() {
        return POST;
    }
}
