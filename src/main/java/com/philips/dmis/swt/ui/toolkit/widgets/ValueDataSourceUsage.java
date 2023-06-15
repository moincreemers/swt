package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ValueDataSourceUsage implements HasDataSourceUsage {
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
