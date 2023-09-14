package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"low", "high", "optimum"})
public class LowHighOptimumImpl implements HasLowHighOptimum {
    private final Widget widget;
    private Integer low, high, optimum;

    public LowHighOptimumImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasLowHighOptimum getLowHighOptimumImpl() {
        return this;
    }

    @Override
    public Integer getLow() {
        return low;
    }

    @Override
    public void setLow(Integer low) {
        this.low = low;
    }

    @Override
    public Integer getHigh() {
        return high;
    }

    @Override
    public void setHigh(Integer high) {
        this.high = high;
    }

    @Override
    public Integer getOptimum() {
        return optimum;
    }

    @Override
    public void setOptimum(Integer optimum) {
        this.optimum = optimum;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (low != null) {
            htmlAttributes.put("low", low.toString());
        }
        if (high != null) {
            htmlAttributes.put("high", high.toString());
        }
        if (optimum != null) {
            htmlAttributes.put("optimum", optimum.toString());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
