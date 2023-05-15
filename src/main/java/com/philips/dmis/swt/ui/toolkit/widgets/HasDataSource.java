package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;

public interface HasDataSource<T extends Widget> {
    Widget asWidget();

    java.util.List<DataSource> getDataSource(DataSourceUsage dataSourceUsage);

    boolean hasDataSource(DataSourceUsage dataSourceUsage);

    T addDataSource(DataSource dataSource) throws WidgetConfigurationException;

    T addDataSource(DataSourceUsage dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException;
}
