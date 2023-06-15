package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TemplateListItemsDataSourceUsage implements HasDataSourceUsage {
    LIST_ITEMS_TEMPLATE(DataSourceUsage.LIST_ITEMS_TEMPLATE),

    ;

    final DataSourceUsage dataSourceUsage;

    TemplateListItemsDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
