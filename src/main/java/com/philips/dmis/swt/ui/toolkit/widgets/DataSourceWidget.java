package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.List;
import java.util.*;

public abstract class DataSourceWidget extends Widget implements DataSourceSupplier {
    private final Map<HasDataSource<?>, DataSourceUsage> subscribers = new HashMap<>();
    private final java.util.List<DataAdapter> dataAdapters = new ArrayList<>();
    private final java.util.List<Parameter> parameters = new ArrayList<>();
    private final boolean expectServiceResponse;
    private final boolean autoRefresh;
    private final boolean notifySubscribers;
    private final Set<String> disabledDataAdapters = new HashSet<>();
    private CacheType cacheType = CacheType.DEFAULT;

    public DataSourceWidget(WidgetType widgetType, boolean expectServiceResponse) {
        this(widgetType, expectServiceResponse, true, true);
    }

    public DataSourceWidget(WidgetType widgetType, boolean expectServiceResponse, boolean autoRefresh) {
        this(widgetType, expectServiceResponse, autoRefresh, true);
    }

    public DataSourceWidget(WidgetType widgetType, boolean expectServiceResponse, boolean autoRefresh, boolean notifySubscribers) {
        super(widgetType);
        this.expectServiceResponse = expectServiceResponse;
        this.autoRefresh = autoRefresh;
        this.notifySubscribers = notifySubscribers;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public boolean isExpectServiceResponse() {
        return expectServiceResponse;
    }

    @Override
    public boolean isAutoRefresh() {
        return autoRefresh;
    }

    @Override
    public boolean isNotifySubscribers() {
        return notifySubscribers;
    }

    @Override
    public CacheType getCacheType() {
        return cacheType;
    }

    @Override
    public void setCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
    }

    @Override
    public void subscribe(DataSourceUsage dataSourceUsage, HasDataSource<?> widget) {
        subscribers.put(widget, dataSourceUsage);
    }

    @Override
    public void unsubscribe(HasDataSource<?> widget) {
        subscribers.remove(widget);
    }

    @Override
    public Map<HasDataSource<?>, DataSourceUsage> getSubscribers() {
        return subscribers;
    }

    @Override
    public DataSourceWidget addDataAdapter(DataAdapter dataAdapter) {
        this.dataAdapters.add(dataAdapter);
        return this;
    }

    @Override
    public java.util.List<DataAdapter> getDataAdapters() {
        return dataAdapters;
    }

    @Override
    public DataSourceWidget addParameter(String name, String defaultValue) {
        parameters.add(new Parameter(name, defaultValue));
        return this;
    }

    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public void setDataAdapterDisabled(DataAdapter dataAdapter, boolean disabled) {
        if (disabled) {
            disabledDataAdapters.add(dataAdapter.getId());
        } else {
            disabledDataAdapters.remove(dataAdapter.getId());
        }
    }

    @Override
    public boolean isDataAdapterDisabled(DataAdapter dataAdapter) {
        return disabledDataAdapters.contains(dataAdapter.getId());
    }

    @Override
    public List<String> getDisabledDataAdapters() {
        return disabledDataAdapters.stream().toList();
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        super.validate(toolkit);
        for (HasDataSource<?> hasDataSource : subscribers.keySet()) {
            hasDataSource.asWidget().validate(toolkit);
        }
        for (DataAdapter dataAdapter : dataAdapters) {
            dataAdapter.validate(toolkit);
        }
    }
}
