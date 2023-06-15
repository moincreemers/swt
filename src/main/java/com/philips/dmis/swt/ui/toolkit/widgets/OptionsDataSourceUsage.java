package com.philips.dmis.swt.ui.toolkit.widgets;

public enum OptionsDataSourceUsage implements HasDataSourceUsage {
    OPTIONS(DataSourceUsage.OPTIONS),

    ;

    final DataSourceUsage dataSourceUsage;

    OptionsDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
