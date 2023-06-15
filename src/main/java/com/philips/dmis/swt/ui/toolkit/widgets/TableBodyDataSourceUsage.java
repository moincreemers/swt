package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TableBodyDataSourceUsage implements HasDataSourceUsage {
    TABLE_BODY(DataSourceUsage.TABLE_BODY),

    ;

    final DataSourceUsage dataSourceUsage;

    TableBodyDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
