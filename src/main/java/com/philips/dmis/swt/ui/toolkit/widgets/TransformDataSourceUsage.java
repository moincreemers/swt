package com.philips.dmis.swt.ui.toolkit.widgets;

public enum TransformDataSourceUsage implements HasDataSourceUsage {
    TRANSFORM(DataSourceUsage.TRANSFORM),

    ;

    final DataSourceUsage dataSourceUsage;

    TransformDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
