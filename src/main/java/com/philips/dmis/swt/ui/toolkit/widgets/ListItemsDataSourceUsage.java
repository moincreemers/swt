package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ListItemsDataSourceUsage implements HasDataSourceUsage {
    LIST_ITEMS(DataSourceUsage.LIST_ITEMS),

    ;

    final DataSourceUsage dataSourceUsage;

    ListItemsDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
