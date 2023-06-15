package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ValueAndOptionsDataSourceUsage implements HasDataSourceUsage {
    VALUE(DataSourceUsage.VALUE),
    OPTIONS(DataSourceUsage.OPTIONS),

    ;

    final DataSourceUsage dataSourceUsage;

    ValueAndOptionsDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
