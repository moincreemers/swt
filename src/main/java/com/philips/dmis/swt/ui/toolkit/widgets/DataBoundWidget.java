package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.BeforeUpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.UpdateEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.*;
import java.util.stream.Collectors;

public class DataBoundWidget<T extends Widget> extends Widget implements HasDataSource<T> {
    private final Map<DataSourceUsage, java.util.List<DataSource>> dataSources = new HashMap<>();
    // todo: editable is not used for anything
    private boolean editable = false;
    private boolean generateLinks = false;
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
    public boolean hasDataSource(DataSourceUsage dataSourceUsage) {
        return getDataSources(dataSourceUsage) != null;
    }

    @Override
    public T addDataSource(DataSource dataSource) throws WidgetConfigurationException {
        if (dataSource == null) {
            return (T) this;
        }
        unsubscribe(dataSource.getDataSourceUsage(), dataSource.getDataSourceSupplier().asWidget().getId());
        dataSource.getDataSourceSupplier().subscribe(dataSource.getDataSourceUsage(), this);
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
    public T addDataSource(DataSourceUsage dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        DataSource dataSource = new DataSource(dataSourceUsage, dataSourceSupplier, dataAdapters);
        addDataSource(dataSource);
        return (T) this;
    }

    private void unsubscribe(DataSourceUsage dataSourceUsage) {
        java.util.List<DataSource> existingDataSources = dataSources.get(dataSourceUsage);
        if (existingDataSources != null) {
            for (DataSource existingDataSource : existingDataSources) {
                existingDataSource.getDataSourceSupplier().unsubscribe(this);
            }
            dataSources.remove(dataSourceUsage);
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
                dataSources.remove(removedDataSource);
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

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isGenerateLinks() {
        return generateLinks;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    /**
     * todo: get rid of this
     * <p>
     * Replaces hyperlinks in text with html anchors automatically.
     * <p>
     * Warning: Use with caution. This feature uses element.innerHTML
     * which is unsafe if the source of the data is not trusted.
     * <p>
     * If the field contains a URL only, setting the DataDisplayType
     * for the column to LINK is a safer option.
     * <p>
     * Note that the toolkit does scan for unsafe elements and prevents
     * displaying such content but this cannot mitigate all risks.
     *
     * @param generateLinks
     */
    public void setGenerateLinks(boolean generateLinks) {
        this.generateLinks = generateLinks;
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
