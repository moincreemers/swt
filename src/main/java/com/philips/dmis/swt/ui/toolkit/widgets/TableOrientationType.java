package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum TableOrientationType implements HasDefault<TableOrientationType> {
    NORMAL,
    ROW_HEADERS;

    @Override
    public TableOrientationType getDefault() {
        return TableOrientationType.NORMAL;
    }
}
