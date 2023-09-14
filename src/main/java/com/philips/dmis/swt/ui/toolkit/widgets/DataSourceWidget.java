package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.*;

@PageXmlElement({"expectServiceResponse", "autoRefresh", "notifySubscribers", "disabledDataAdapters", "cacheType"})
public abstract class DataSourceWidget extends Widget implements DataSourceSupplier {
    private final Map<HasDataSource<?, ?>, DataSourceUsage> subscribers = new HashMap<>();
    private final List<DataAdapter> dataAdapters = new ArrayList<>();
    private boolean expectServiceResponse = true;
    private boolean autoRefresh = true;
    private boolean notifySubscribers = true;
    private final Set<String> disabledDataAdapters = new HashSet<>();
    private CacheType cacheType = CacheType.DEFAULT;

    public DataSourceWidget(WidgetConfigurator widgetConfigurator, WidgetType widgetType) {
        super(widgetConfigurator, widgetType);
    }

    public DataSourceWidget(WidgetType widgetType) {
        super(widgetType);
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public boolean isExpectServiceResponse() {
        return expectServiceResponse;
    }

    public void setExpectServiceResponse(boolean expectServiceResponse) {
        this.expectServiceResponse = expectServiceResponse;
    }

    @Override
    public boolean isAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    @Override
    public boolean isNotifySubscribers() {
        return notifySubscribers;
    }

    public void setNotifySubscribers(boolean notifySubscribers) {
        this.notifySubscribers = notifySubscribers;
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
    public Map<HasDataSource<?, ?>, DataSourceUsage> getSubscribers() {
        return subscribers;
    }

    @Override
    public void subscribe(HasDataSourceUsage dataSourceUsage, HasDataSource<?, ?> widget) {
        subscribers.put(widget, dataSourceUsage.getDataSourceUsage());
    }

    @Override
    public void unsubscribe(HasDataSource<?, ?> widget) {
        subscribers.remove(widget);
    }

    @Override
    public DataSourceWidget addDataAdapter(DataAdapter dataAdapter) {
        dataAdapters.add(dataAdapter);
        return this;
    }

    @Override
    public java.util.List<DataAdapter> getDataAdapters() {
        return dataAdapters;
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
        boolean hasImportDataAdapter = dataAdapters.stream()
                .anyMatch(dataAdapter -> dataAdapter.isDataSourceUsageAllowed(DataSourceUsage.IMPORT));
        if (!expectServiceResponse && !hasImportDataAdapter) {
            throw new WidgetConfigurationException("missing data adapter with DataSourceUsage.IMPORT in data source " + getId() + " <" + srcName + ">");
        }
        boolean hasViewDataAdapter = dataAdapters.stream()
                .anyMatch(dataAdapter -> dataAdapter.isDataSourceUsageAllowed(DataSourceUsage.VIEW));
        if (expectServiceResponse && !hasViewDataAdapter) {
            // todo: this is a bit complicated
            //throw new WidgetConfigurationException("missing data adapter with DataSourceUsage.VIEW in data source " + getId() + " <" + srcName + ">");
        }
        for (HasDataSource<?, ?> hasDataSource : subscribers.keySet()) {
            hasDataSource.asWidget().validate(toolkit);
        }
        for (DataAdapter dataAdapter : dataAdapters) {
            dataAdapter.validate(toolkit);
        }
    }
}
