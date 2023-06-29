package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.OpenEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Set;

public class HtmlList extends DataBoundWidget<HtmlList, ItemsDataSourceUsage> implements HasListItems {
    private ListType listType;

    public HtmlList() {
        this(ListType.UNORDERED);
    }

    public HtmlList(ListType listType) {
        super(WidgetType.LIST);
        setListType(listType);
    }

    public HtmlList addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ItemsDataSourceUsage.ITEMS, dataSourceSupplier, dataAdapters);
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

    @Override
    public void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters) {
        // todo: this needs to be smarter
        // requiredDataAdapters.add(KeyValueListDataAdapter.class);
    }
}
