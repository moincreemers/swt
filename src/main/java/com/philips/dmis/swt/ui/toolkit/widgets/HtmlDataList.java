package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Set;

public class HtmlDataList extends DataBoundWidget<HtmlDataList, ItemsDataSourceUsage> implements HasOptions {
    public HtmlDataList(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.DATA_LIST);
    }

    public HtmlDataList() {
        super(WidgetType.DATA_LIST);
    }

    public HtmlDataList addDataSource(DataSourceSupplier dataSourceSupplier) throws WidgetConfigurationException {
        super.addDataSource(ItemsDataSourceUsage.ITEMS, dataSourceSupplier, new KeyValueListDataAdapter());
        return this;
    }

    public HtmlDataList addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ItemsDataSourceUsage.ITEMS, dataSourceSupplier, dataAdapters);
        return this;
    }

    public HtmlDataList onChange(ChangeEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    @Override
    public void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters) {
        //requiredDataAdapters.add(KeyValueListDataAdapter.class);
    }
}
