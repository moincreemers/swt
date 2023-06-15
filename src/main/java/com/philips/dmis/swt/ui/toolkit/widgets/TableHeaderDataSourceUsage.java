package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TableHeaderDataSourceUsage implements HasDataSourceUsage {
    TABLE_HEADER(DataSourceUsage.TABLE_HEADER),

    ;

    final DataSourceUsage dataSourceUsage;

    TableHeaderDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
