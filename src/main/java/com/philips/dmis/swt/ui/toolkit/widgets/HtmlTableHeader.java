package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.events.OrderChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class HtmlTableHeader extends DataBoundWidget<HtmlTableHeader, ItemsDataSourceUsage> implements
        HasOrderingControls<HtmlTableHeader>, HasTableHeaderRows {
    public HtmlTableHeader() {
        super(WidgetType.TABLE_HEADER);
    }

    public HtmlTableHeader addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ItemsDataSourceUsage.ITEMS, dataSourceSupplier, dataAdapters);
        return this;
    }

    // ORDERING CONTROLS

    private final OrderingControlsImpl<HtmlTableHeader> orderingControlsImpl = new OrderingControlsImpl<>(this);

    @Override
    public HasOrderingControls<HtmlTableHeader> getOrderingControlsImpl() {
        return orderingControlsImpl;
    }

    @Override
    public HtmlTableHeader setOrder(String source, Order order) {
        return orderingControlsImpl.setOrder(source, order);
    }

    @Override
    public Map<String, Order> getOrder() {
        return orderingControlsImpl.getOrder();
    }

    @Override
    public boolean isMultiLevel() {
        return orderingControlsImpl.isMultiLevel();
    }

    @Override
    public HtmlTableHeader setMultiLevel(boolean multiLevel) {
        return orderingControlsImpl.setMultiLevel(multiLevel);
    }

    @Override
    public HtmlTableHeader onOrderChange(OrderChangeEventHandler eventHandler) {
        return orderingControlsImpl.onOrderChange(eventHandler);
    }
}