package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TextDataSourceUsage implements HasDataSourceUsage {
    TEXT(DataSourceUsage.TEXT),

    ;

    final DataSourceUsage dataSourceUsage;

    TextDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
