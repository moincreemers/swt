package com.philips.dmis.swt.ui.toolkit.widgets;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum DataSourceUsage {
    PROCEDURAL(false, true, 0, 0),
    ITEMS(false, true, 2, 0),
    VALUE(false, true, 4, 1),
    IMPORT(true, false, 10, 1),
    TRANSFORM(true, true, 11, 1),
    VIEW(true, true, 12, 1),

    ;

    final boolean applyToDataSource;
    final boolean applyToDataConsumer;
    final int priority;
    final int maxOccurs;

    DataSourceUsage(boolean applyToDataSource, boolean applyToDataConsumer, int priority, int maxOccurs) {
        this.applyToDataSource = applyToDataSource;
        this.applyToDataConsumer = applyToDataConsumer;
        this.priority = priority;
        this.maxOccurs = maxOccurs;
    }

    public int getPriority() {
        return priority;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }

    public boolean isApplyToDataSource() {
        return applyToDataSource;
    }

    public boolean isApplyToDataConsumer() {
        return applyToDataConsumer;
    }

    public static List<DataSourceUsage> valuesByPriority() {
        return Arrays.stream(DataSourceUsage.values())
                .sorted(Comparator.comparingInt(o -> o.priority)).toList();
    }

    public static List<DataSourceUsage> valuesByPriorityForDataSource() {
        return Arrays.stream(DataSourceUsage.values())
                .filter(DataSourceUsage::isApplyToDataSource)
                .sorted(Comparator.comparingInt(o -> o.priority)).toList();
    }

    public static List<DataSourceUsage> valuesByPriorityForDataConsumer() {
        return Arrays.stream(DataSourceUsage.values())
                .filter(DataSourceUsage::isApplyToDataConsumer)
                .sorted(Comparator.comparingInt(o -> o.priority)).toList();
    }
}
