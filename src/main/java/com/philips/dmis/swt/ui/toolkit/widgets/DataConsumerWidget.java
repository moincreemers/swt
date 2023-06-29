package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class DataConsumerWidget extends DataBoundWidget<DataConsumerWidget, ProceduralDataSourceUsage> {
    public DataConsumerWidget() {
        super(WidgetType.DATA_CONSUMER);
    }

    public DataConsumerWidget addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ProceduralDataSourceUsage.PROCEDURAL, dataSourceSupplier, dataAdapters);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
    }
}
