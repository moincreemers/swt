package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlTableFooter extends DataBoundWidget<HtmlTableFooter, ItemsDataSourceUsage> implements
        HasTableFooterRows {
    public HtmlTableFooter(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.TABLE_FOOTER);
    }

    public HtmlTableFooter() {
        super(WidgetType.TABLE_FOOTER);
    }

    public HtmlTableFooter addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ItemsDataSourceUsage.ITEMS, dataSourceSupplier, dataAdapters);
        return this;
    }
}