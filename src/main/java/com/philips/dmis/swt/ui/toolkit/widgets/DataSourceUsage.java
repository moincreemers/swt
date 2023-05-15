package com.philips.dmis.swt.ui.toolkit.widgets;

public enum DataSourceUsage {
    TEXT(1),
    VALUE(1),
    OPTIONS(0),
    LIST_ITEMS(0),
    TABLE_HEADER(1),
    TABLE_FOOTER(1),
    TABLE_BODY(1),
    IMPORT(1),
    TRANSFORM(1),

    ;

    final int maxOccurs;

    DataSourceUsage(int maxOccurs) {
        this.maxOccurs = maxOccurs;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }
}
