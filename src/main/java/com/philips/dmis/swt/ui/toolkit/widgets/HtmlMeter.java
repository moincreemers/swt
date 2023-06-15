package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

// todo: does not have HasValue

public class HtmlMeter extends DataBoundWidget<HtmlMeter,ValueDataSourceUsage> implements
        HasRange<Integer>, HasLowHighOptimum {
    public HtmlMeter() {
        super(WidgetType.METER);
    }

    public HtmlMeter addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    public HtmlMeter onClick(ClickEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    // RANGE

    private final RangeImpl<Integer> rangeImpl = new RangeImpl<>(this);

    @Override
    public HasRange<Integer> getRangeImpl() {
        return rangeImpl;
    }

    @Override
    public Integer getRangeMin() {
        return rangeImpl.getRangeMin();
    }

    @Override
    public void setRangeMin(Integer rangeMin) {
        rangeImpl.setRangeMin(rangeMin);
    }

    @Override
    public Integer getRangeMax() {
        return rangeImpl.getRangeMax();
    }

    @Override
    public void setRangeMax(Integer rangeMax) {
        rangeImpl.setRangeMax(rangeMax);
    }

    // LOW, HIGH, OPTIMUM

    private final LowHighOptimumImpl lowHighOptimumImpl = new LowHighOptimumImpl(this);

    @Override
    public HasLowHighOptimum getLowHighOptimumImpl() {
        return lowHighOptimumImpl;
    }

    @Override
    public Integer getLow() {
        return lowHighOptimumImpl.getLow();
    }

    @Override
    public void setLow(Integer low) {
        lowHighOptimumImpl.setLow(low);
    }

    @Override
    public Integer getHigh() {
        return lowHighOptimumImpl.getHigh();
    }

    @Override
    public void setHigh(Integer high) {
        lowHighOptimumImpl.setHigh(high);
    }

    @Override
    public Integer getOptimum() {
        return lowHighOptimumImpl.getOptimum();
    }

    @Override
    public void setOptimum(Integer optimum) {
        lowHighOptimumImpl.setOptimum(optimum);
    }
}
