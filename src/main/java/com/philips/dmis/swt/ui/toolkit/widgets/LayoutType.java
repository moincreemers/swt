package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum LayoutType implements HasDefault<LayoutType> {
    AUTO,
    RESIZE,
    NO_RESIZE;

    @Override
    public LayoutType getDefault() {
        return AUTO;
    }
}
