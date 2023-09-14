package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.BeforeUpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.UpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.*;
import java.util.stream.Collectors;

@PageXmlElement("emptyText")
public abstract class DataBoundWidget<T extends Widget, E extends HasDataSourceUsage> extends Widget
        implements HasDataSource<T, E> {
    private final Map<DataSourceUsage, Map<String, DataSource>> dataSources = new HashMap<>();
    private String emptyText = "";

    public DataBoundWidget(WidgetConfigurator widgetConfigurator, WidgetType widgetType) {
        super(widgetConfigurator, widgetType);
    }

    public DataBoundWidget(WidgetType widgetType) {
        super(widgetType);
    }

    @Override
    public Widget asWidget() {
        return this;
    }


    @Override
    public Map<String, DataSource> getDataSources(DataSourceUsage dataSourceUsage) {
        return dataSources.get(dataSourceUsage);
    }

    @Override
    public Map<String, DataSource> getDataSources(E dataSourceUsage) {
        return dataSources.get(dataSourceUsage.getDataSourceUsage());
    }

    @Override
    public boolean hasDataSource(DataSourceUsage dataSourceUsage) {
        return getDataSources(dataSourceUsage) != null;
    }

    @Override
    public boolean hasDataSource(E dataSourceUsage) {
        return getDataSources(dataSourceUsage) != null;
    }

    @Override
    public T addDataSource(DataSource dataSource) throws WidgetConfigurationException {
        if (dataSource == null) {
            return (T) this;
        }
        unsubscribe(dataSource.getDataSourceUsage(), dataSource.getDataSourceSupplier().asWidget().getId());
        dataSource.getDataSourceSupplier().subscribe(dataSource, this);
        Map<String, DataSource> dataSources = this.dataSources.computeIfAbsent(dataSource.getDataSourceUsage(), k -> new HashMap<>());
        if (dataSource.getDataSourceUsage().getMaxOccurs() > 0
            && dataSources.size() >= dataSource.getDataSourceUsage().getMaxOccurs()) {
            throw new WidgetConfigurationException("data sources exceeds max-occurs for "
                                                   + dataSource.getDataSourceUsage().name() + ": "
                                                   + dataSource.getDataSourceUsage().getMaxOccurs());
        }
        dataSources.put(dataSource.getDataSourceSupplier().asWidget().getId(), dataSource);
        return (T) this;
    }

    @Override
    public T addDataSource(E dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        DataSource dataSource = new DataSource(dataSourceUsage.getDataSourceUsage(), dataSourceSupplier, dataAdapters);
        addDataSource(dataSource);
        return (T) this;
    }

    private void unsubscribe(E dataSourceUsage) {
        Map<String, DataSource> existingDataSources = dataSources.get(dataSourceUsage.getDataSourceUsage());
        if (existingDataSources != null) {
            for (DataSource existingDataSource : existingDataSources.values()) {
                existingDataSource.getDataSourceSupplier().unsubscribe(this);
            }
            dataSources.remove(dataSourceUsage.getDataSourceUsage());
        }
    }

    private void unsubscribe(DataSourceUsage dataSourceUsage, String dataSourceId) {
        Map<String, DataSource> existingDataSources = dataSources.get(dataSourceUsage);
        if (existingDataSources != null) {
            java.util.List<DataSource> removed = new ArrayList<>();
            for (DataSource existingDataSource : existingDataSources.values()) {
                if (existingDataSource.getDataSourceSupplier().asWidget().getId().equals(dataSourceId)) {
                    existingDataSource.getDataSourceSupplier().unsubscribe(this);
                    removed.add(existingDataSource);
                }
            }
            for (DataSource removedDataSource : removed) {
                dataSources.remove(removedDataSource.getDataSourceUsage());
            }
        }
    }

    public T onBeforeUpdate(BeforeUpdateEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return (T) this;
    }

    public T onUpdate(UpdateEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return (T) this;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        super.validate(toolkit);
        Set<Class<? extends DataAdapter>> requiredDataAdapters = new HashSet<>();
        getRequiredDataAdapters(requiredDataAdapters);
        for (Map<String, DataSource> dataSources : dataSources.values()) {
            for (DataSource dataSource : dataSources.values()) {
                dataSource.validate(toolkit);
                for (DataAdapter dataAdapter : dataSource.getDataAdapters()) {
                    requiredDataAdapters.remove(dataAdapter.getClass());
                }
            }
        }
        if (!dataSources.isEmpty() && !requiredDataAdapters.isEmpty()) {
            throw new WidgetConfigurationException(
                    "missing required data adapters: "
                    + requiredDataAdapters.stream()
                            .map(Class::getSimpleName).collect(Collectors.joining(", "))
                    + " on data bound widget: "
                    + getId()
                    + " ("
                    + getWidgetType().name()
                    + ")"
            );
        }
    }

    @Override
    public void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters) {
    }
}
