package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.BeforeUpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.UpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.*;
import java.util.stream.Collectors;

public class DataBoundWidget<T extends Widget, E extends HasDataSourceUsage> extends Widget implements HasDataSource<T, E> {
    private final Map<DataSourceUsage, java.util.List<DataSource>> dataSources = new HashMap<>();
    private String emptyText = "";

    public DataBoundWidget(WidgetType widgetType) {
        super(widgetType);
    }

    @Override
    public Widget asWidget() {
        return this;
    }


    @Override
    public java.util.List<DataSource> getDataSources(DataSourceUsage dataSourceUsage) {
        return dataSources.get(dataSourceUsage);
    }

    @Override
    public java.util.List<DataSource> getDataSources(E dataSourceUsage) {
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
        java.util.List<DataSource> dataSources = this.dataSources.computeIfAbsent(dataSource.getDataSourceUsage(), k -> new ArrayList<>());
        if (dataSource.getDataSourceUsage().getMaxOccurs() > 0
                && dataSources.size() >= dataSource.getDataSourceUsage().getMaxOccurs()) {
            throw new WidgetConfigurationException("data sources exceeds max-occurs for "
                    + dataSource.getDataSourceUsage().name() + ": "
                    + dataSource.getDataSourceUsage().getMaxOccurs());
        }
        dataSources.add(dataSource);
        return (T) this;
    }

    @Override
    public T addDataSource(E dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        DataSource dataSource = new DataSource(dataSourceUsage.getDataSourceUsage(), dataSourceSupplier, dataAdapters);
        addDataSource(dataSource);
        return (T) this;
    }

    private void unsubscribe(E dataSourceUsage) {
        java.util.List<DataSource> existingDataSources = dataSources.get(dataSourceUsage.getDataSourceUsage());
        if (existingDataSources != null) {
            for (DataSource existingDataSource : existingDataSources) {
                existingDataSource.getDataSourceSupplier().unsubscribe(this);
            }
            dataSources.remove(dataSourceUsage.getDataSourceUsage());
        }
    }

    private void unsubscribe(DataSourceUsage dataSourceUsage, String dataSourceId) {
        java.util.List<DataSource> existingDataSources = dataSources.get(dataSourceUsage);
        if (existingDataSources != null) {
            java.util.List<DataSource> removed = new ArrayList<>();
            for (DataSource existingDataSource : existingDataSources) {
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
        for (java.util.List<DataSource> dataSources : dataSources.values()) {
            for (DataSource dataSource : dataSources) {
                dataSource.validate(toolkit);
                for (DataAdapter dataAdapter : dataSource.getDataAdapters()) {
                    requiredDataAdapters.remove(dataAdapter.getClass());
                }
            }
        }
        if (!dataSources.isEmpty() && !requiredDataAdapters.isEmpty()) {
            throw new WidgetConfigurationException("missing required data adapters: "
                    + requiredDataAdapters.stream().map(Class::getSimpleName).collect(Collectors.joining(", "))
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
