package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ValueAndListItemsDataSourceUsage implements HasDataSourceUsage {
    VALUE(DataSourceUsage.VALUE),
    LIST_ITEMS(DataSourceUsage.LIST_ITEMS),
    LIST_ITEMS_TEMPLATE(DataSourceUsage.LIST_ITEMS_TEMPLATE),

    ;

    final DataSourceUsage dataSourceUsage;

    ValueAndListItemsDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
