package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataSource implements HasDataSourceUsage {
    private static final Logger LOG = Logger.getLogger(DataSource.class.getName());
    private final DataSourceUsage dataSourceUsage;
    private final DataSourceSupplier dataSourceSupplier;
    private final List<DataAdapter> dataAdapters = new ArrayList<>();
    private boolean validated;

    public DataSource(DataSourceUsage dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        if (dataSourceUsage == null) {
            throw new IllegalArgumentException("missing dataSourceUsage");
        }
        if (dataSourceSupplier == null) {
            throw new IllegalArgumentException("missing dataSourceWidget");
        }

// no longer a requirement to have any data adapters
//        if (dataAdapters == null || dataAdapters.length == 0) {
//            throw new IllegalArgumentException("missing dataAdapters");
//        }

        this.dataSourceUsage = dataSourceUsage;
        this.dataSourceSupplier = dataSourceSupplier;
        for (DataAdapter dataAdapter : dataAdapters) {
            this.dataAdapters.add(dataAdapter);
        }
    }

    public DataSourceUsage getDataSourceUsage() {
        return dataSourceUsage;
    }

    public DataSourceSupplier getDataSourceSupplier() {
        return dataSourceSupplier;
    }

    public List<DataAdapter> getDataAdapters() {
        return dataAdapters;
    }

    public DataAdapter getFinalDataAdapter() {
        return dataAdapters.get(dataAdapters.size() - 1);
    }

    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        String dataAdaptersList = dataAdapters.stream().map(dataAdapter -> dataAdapter.getClass().getSimpleName() + " (" + dataAdapter.getId() + ")").collect(Collectors.joining(", "));
        LOG.info("validate data source for: " + dataSourceUsage + ", data adapters: " + dataAdaptersList);
        validated = true;
        dataSourceSupplier.validate(toolkit);
        for (DataAdapter dataAdapter : dataAdapters) {
            dataAdapter.validate(toolkit);
        }
    }
}
