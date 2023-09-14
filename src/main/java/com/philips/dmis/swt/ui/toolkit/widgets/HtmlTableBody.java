package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.OpenEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.SelectionChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

@PageXmlElement("tableOrientationType")
public class HtmlTableBody extends DataBoundWidget<HtmlTableBody, ItemsDataSourceUsage> implements
        HasTableRows {
    public static final String CSS_CELL_BOOLEAN = "tk-boolean";
    public static final String CSS_CELL_NUMBER = "tk-number";
    public static final String CSS_CELL_STRING = "tk-string";
    public static final String CSS_CELL_DATE = "tk-date";
    public static final String CSS_CELL_URL = "tk-url";
    public static final String CSS_CELL_ARRAY = "tk-array";

    private TableOrientationType tableOrientationType = TableOrientationType.NORMAL;

    public HtmlTableBody() {
        this(TableOrientationType.NORMAL);
    }

    public HtmlTableBody(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.TABLE_BODY);
    }

    public HtmlTableBody(TableOrientationType tableOrientationType) {
        super(WidgetType.TABLE_BODY);
        setTableOrientationType(tableOrientationType);
    }

    public TableOrientationType getTableOrientationType() {
        return tableOrientationType;
    }

    public void setTableOrientationType(TableOrientationType tableOrientationType) {
        if (tableOrientationType == null) {
            tableOrientationType = TableOrientationType.NORMAL;
        }
        if (tableOrientationType == TableOrientationType.ROW_HEADERS) {
            addClassName("tk-row-headers");
        } else {
            removeClassName("tk-row-headers");
        }
        this.tableOrientationType = tableOrientationType;
    }

    public HtmlTableBody addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ItemsDataSourceUsage.ITEMS, dataSourceSupplier, dataAdapters);
        return this;
    }

    public HtmlTableBody onOpen(OpenEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public HtmlTableBody onSelectionChange(SelectionChangeEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }
}
