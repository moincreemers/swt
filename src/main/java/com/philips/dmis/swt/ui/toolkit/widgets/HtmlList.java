package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.OpenEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlList extends DataBoundWidget<HtmlList> {
    private ListType listType;

    public HtmlList() {
        this(ListType.UNORDERED);
    }

    public HtmlList(ListType listType) {
        super(WidgetType.LIST);
        setListType(listType);
    }

    public HtmlList addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(DataSourceUsage.LIST_ITEMS, dataSourceSupplier, dataAdapters);
        return this;
    }

    public HtmlList onOpen(OpenEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public ListType getListType() {
        return listType;
    }

    public void setListType(ListType listType) {
        if (this.listType != null) {
            removeClassName(this.listType.getClassName());
        }
        this.listType = listType;
        addClassName(listType.getClassName());
    }

    @Override
    public String getHtmlTag(String defaultHtmlTag) {
        return (listType == ListType.ORDERED) ? "ol" : super.getHtmlTag(defaultHtmlTag);
    }
}
