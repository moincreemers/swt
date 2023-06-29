package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ValueDataSourceUsage implements HasDataSourceUsage {
    PROCEDURAL(DataSourceUsage.PROCEDURAL),
    VALUE(DataSourceUsage.VALUE),

    ;

    final DataSourceUsage dataSourceUsage;

    ValueDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
