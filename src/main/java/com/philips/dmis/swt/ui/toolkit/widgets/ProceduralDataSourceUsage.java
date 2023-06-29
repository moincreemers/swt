package com.philips.dmis.swt.ui.toolkit.widgets;

public enum ProceduralDataSourceUsage implements HasDataSourceUsage {
    PROCEDURAL(DataSourceUsage.PROCEDURAL),

    ;

    final DataSourceUsage dataSourceUsage;

    ProceduralDataSourceUsage(DataSourceUsage dataSourceUsage) {
        this.dataSourceUsage = dataSourceUsage;
    }

    @Override
    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }
}
