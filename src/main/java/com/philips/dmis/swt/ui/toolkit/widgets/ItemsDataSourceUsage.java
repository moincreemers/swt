package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ItemsDataSourceUsage implements HasDataSourceUsage {
    PROCEDURAL(DataSourceUsage.PROCEDURAL),
    ITEMS(DataSourceUsage.ITEMS),

    ;

    final DataSourceUsage dataSourceUsage;

    ItemsDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
