package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlTableFooter extends DataBoundWidget<HtmlTableFooter> {
    public HtmlTableFooter() {
        super(WidgetType.TABLE_FOOTER);
    }

    public HtmlTableFooter addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(DataSourceUsage.TABLE_FOOTER, dataSourceSupplier, dataAdapters);
        return this;
    }
}