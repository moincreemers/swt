package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;

import java.util.ArrayList;

public class DataSource {
    private final DataSourceUsage dataSourceUsage;
    private final DataSourceSupplier dataSourceSupplier;
    private final java.util.List<DataAdapter> dataAdapters = new ArrayList<>();
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

    public java.util.List<DataAdapter> getDataAdapters() {
        return dataAdapters;
    }

    public DataAdapter getFinalDataAdapter() {
        return dataAdapters.get(dataAdapters.size() - 1);
    }

    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        dataSourceSupplier.validate(toolkit);
        for (DataAdapter dataAdapter : dataAdapters) {
            dataAdapter.validate(toolkit);
        }
    }
}
