package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.*;
import java.util.List;

@PageXmlElement({"expectServiceResponse", "autoRefresh", "notifySubscribers", "cacheType"})
public class DataProxy extends DataBoundWidget<DataProxy, TransformDataSourceUsage> implements DataSourceSupplier {
    private final Map<HasDataSource<?, ?>, DataSourceUsage> subscribers = new HashMap<>();
    private final List<DataAdapter> dataAdapters = new ArrayList<>();
    private boolean expectServiceResponse = true;
    private boolean autoRefresh = true;
    private boolean notifySubscribers = true;
    private final Set<String> disabledDataAdapters = new HashSet<>();
    private CacheType cacheType = CacheType.DEFAULT;

    public DataProxy(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.DATA_PROXY);
    }

    public DataProxy() {
        this(true, true, true);
    }

    public DataProxy(boolean expectServiceResponse) {
        this(expectServiceResponse, true, true);
    }

    public DataProxy(boolean expectServiceResponse, boolean autoRefresh) {
        this(expectServiceResponse, autoRefresh, true);
    }

    public DataProxy(boolean expectServiceResponse, boolean autoRefresh, boolean notifySubscribers) {
        super(WidgetType.DATA_PROXY);
        this.expectServiceResponse = expectServiceResponse;
        this.autoRefresh = autoRefresh;
        this.notifySubscribers = notifySubscribers;
    }

    public boolean isExpectServiceResponse() {
        return expectServiceResponse;
    }

    public void setExpectServiceResponse(boolean expectServiceResponse) {
        this.expectServiceResponse = expectServiceResponse;
    }

    public boolean isAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public boolean isNotifySubscribers() {
        return notifySubscribers;
    }

    public void setNotifySubscribers(boolean notifySubscribers) {
        this.notifySubscribers = notifySubscribers;
    }

    // note that a DataProxy is not a data consumer, any data adapters will NOT be executed at any point
    // for this reason we remove the dataAdapters argument here
    public DataSourceSupplier addDataSource(DataSourceSupplier dataSourceSupplier) throws WidgetConfigurationException {
        super.addDataSource(TransformDataSourceUsage.TRANSFORM, dataSourceSupplier);
        return this;
    }

    @Override
    public void subscribe(HasDataSourceUsage dataSourceUsage, HasDataSource<?, ?> widget) {
        subscribers.put(widget, dataSourceUsage.getDataSourceUsage());
    }

    @Override
    public void unsubscribe(HasDataSource<?, ?> widget) {
        subscribers.remove(widget);
    }

    public Map<HasDataSource<?, ?>, DataSourceUsage> getSubscribers() {
        return subscribers;
    }

    public DataProxy addDataAdapter(DataAdapter dataAdapter) {
        dataAdapters.add(dataAdapter);
        return this;
    }

    public List<DataAdapter> getDataAdapters() {
        return dataAdapters;
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
        for (HasDataSource<?, ?> hasDataSource : subscribers.keySet()) {
            hasDataSource.asWidget().validate(toolkit);
        }
        for (DataAdapter dataAdapter : dataAdapters) {
            dataAdapter.validate(toolkit);
        }
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
    public CacheType getCacheType() {
        return cacheType;
    }

    @Override
    public void setCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.OBJECT;
    }
}
