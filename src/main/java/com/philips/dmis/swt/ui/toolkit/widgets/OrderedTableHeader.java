package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.InteractiveOrderingDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.events.OrderChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;

import java.util.Map;

public class OrderedTableHeader extends Composite {
    private final DataSourceSupplier dataSourceSupplier;
    private final HtmlTableHeader htmlTableHeader = new HtmlTableHeader();

    public OrderedTableHeader(DataSourceSupplier dataSourceSupplier) {
        this(dataSourceSupplier, false);
    }

    public OrderedTableHeader(DataSourceSupplier dataSourceSupplier, boolean multiLevel) {
        this.dataSourceSupplier = dataSourceSupplier;
        setMultiLevel(multiLevel);
    }

    public boolean isMultiLevel() {
        return htmlTableHeader.isMultiLevel();
    }

    public void setMultiLevel(boolean multiLevel) {
        htmlTableHeader.setMultiLevel(multiLevel);
    }

    public Map<String, Order> getOrder() {
        return htmlTableHeader.getOrder();
    }

    public OrderedTableHeader setOrder(String source, Order order) {
        htmlTableHeader.setOrder(source, order);
        return this;
    }

    @Override
    protected void build() throws Exception {
        add(htmlTableHeader);
        htmlTableHeader.addDataSource(dataSourceSupplier);
        InteractiveOrderingDataAdapter interactiveOrderingDataAdapter =
                new InteractiveOrderingDataAdapter(htmlTableHeader);
        dataSourceSupplier.addDataAdapter(interactiveOrderingDataAdapter);
        htmlTableHeader.onOrderChange(new OrderChangeEventHandler(
                M.Refresh(dataSourceSupplier.asWidget(), JsStateModule.REASON_LOCAL)
        ));
    }
}
