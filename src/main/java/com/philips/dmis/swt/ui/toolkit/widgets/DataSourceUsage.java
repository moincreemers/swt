package com.philips.dmis.swt.ui.toolkit.widgets;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum DataSourceUsage {
    TEXT(4, 1),
    VALUE(4, 1),
    OPTIONS(3, 0),
    LIST_ITEMS(3, 0),
    LIST_ITEMS_TEMPLATE(2, 0),
    TABLE_HEADER(3, 1),
    TABLE_FOOTER(3, 1),
    TABLE_BODY(3, 1),

    IMPORT(0, 1),
    TRANSFORM(1, 1),

    ;

    final int priority;
    final int maxOccurs;

    DataSourceUsage(int priority, int maxOccurs) {
        this.priority = priority;
        this.maxOccurs = maxOccurs;
    }

    public int getPriority() {
        return priority;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }

    public static List<DataSourceUsage> valuesByPriority() {
        return Arrays.stream(DataSourceUsage.values())
                .sorted(Comparator.comparingInt(o -> o.priority)).toList();
    }
}
