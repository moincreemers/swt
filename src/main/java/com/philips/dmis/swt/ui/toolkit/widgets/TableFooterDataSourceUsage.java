package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TableFooterDataSourceUsage implements HasDataSourceUsage {
    TABLE_FOOTER(DataSourceUsage.TABLE_FOOTER),

    ;

    final DataSourceUsage dataSourceUsage;

    TableFooterDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
