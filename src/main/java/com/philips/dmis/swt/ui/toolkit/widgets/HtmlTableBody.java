package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.OpenEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.SelectionChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlTableBody extends DataBoundWidget<HtmlTableBody> {
    public static final String CSS_CELL_BOOLEAN = "tk-boolean";
    public static final String CSS_CELL_NUMBER = "tk-number";
    public static final String CSS_CELL_STRING = "tk-string";
    public static final String CSS_CELL_DATE = "tk-date";
    public static final String CSS_CELL_URL = "tk-url";
    public static final String CSS_CELL_ARRAY = "tk-array";

    private final TableOrientationType tableOrientationType;

    public HtmlTableBody() {
        this(TableOrientationType.NORMAL);
    }

    public HtmlTableBody(TableOrientationType tableOrientationType) {
        super(WidgetType.TABLE_BODY);
        this.tableOrientationType = tableOrientationType;
        if (tableOrientationType==TableOrientationType.ROW_HEADERS) {
            addClassName("tk-row-headers");
        }
    }

    public TableOrientationType getTableOrientationType() {
        return tableOrientationType;
    }

    public HtmlTableBody addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(DataSourceUsage.TABLE_BODY, dataSourceSupplier, dataAdapters);
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
