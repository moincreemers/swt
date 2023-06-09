package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;

import java.util.Map;
import java.util.Set;

public interface HasDataSource<T extends Widget, E extends HasDataSourceUsage> {
    Widget asWidget();

    Map<String, DataSource> getDataSources(DataSourceUsage dataSourceUsage);

    Map<String, DataSource> getDataSources(E dataSourceUsage);

    boolean hasDataSource(DataSourceUsage dataSourceUsage);

    boolean hasDataSource(E dataSourceUsage);

    T addDataSource(DataSource dataSource) throws WidgetConfigurationException;

    T addDataSource(E dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException;

    void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters);
}
