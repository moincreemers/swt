package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ValueAndItemsDataSourceUsage implements HasDataSourceUsage {
    PROCEDURAL(DataSourceUsage.PROCEDURAL),
    VALUE(DataSourceUsage.VALUE),
    ITEMS(DataSourceUsage.ITEMS),

    ;

    final DataSourceUsage dataSourceUsage;

    ValueAndItemsDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
