package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;

import java.util.List;
import java.util.Map;

public interface DataSourceSupplier extends Validatable, HasValueType<Object> {
    Widget asWidget();

    boolean isExpectServiceResponse();

    boolean isAutoRefresh();

    boolean isNotifySubscribers();

    void subscribe(HasDataSourceUsage dataSourceUsage, HasDataSource<?, ?> widget);

    void unsubscribe(HasDataSource<?, ?> widget);

    Map<HasDataSource<?, ?>, DataSourceUsage> getSubscribers();

    DataSourceSupplier addDataAdapter(DataAdapter dataAdapter);

    List<DataAdapter> getDataAdapters();

    void setDataAdapterDisabled(DataAdapter dataAdapter, boolean disabled);

    boolean isDataAdapterDisabled(DataAdapter dataAdapter);

    List<String> getDisabledDataAdapters();

    CacheType getCacheType();

    void setCacheType(CacheType cacheType);
}
