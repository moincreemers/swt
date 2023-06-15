package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

// todo: Does not have HasValue

public class HtmlProgress extends DataBoundWidget<HtmlProgress, ValueDataSourceUsage> implements HasMax {
    public HtmlProgress() {
        super(WidgetType.PROGRESS);
    }

    public HtmlProgress addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    public HtmlProgress onClick(ClickEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    // MAX

    private final MaxImpl maxImpl = new MaxImpl(this);

    @Override
    public HasMax getMaxImpl() {
        return maxImpl;
    }

    @Override
    public Float getMax() {
        return maxImpl.getMax();
    }

    @Override
    public void setMax(Float max) {
        maxImpl.setMax(max);
    }

    @Override
    public Float getValue() {
        return maxImpl.getValue();
    }

    @Override
    public void setValue(Float value) {
        maxImpl.setValue(value);
    }
}
