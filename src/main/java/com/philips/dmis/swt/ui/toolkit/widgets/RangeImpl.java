package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"rangeMin", "rangeMax"})
public class RangeImpl<T> implements HasRange<T> {
    private final Widget widget;
    private T min;
    private T max;

    public RangeImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasRange<T> getRangeImpl() {
        return this;
    }

    @Override
    public T getRangeMin() {
        return min;
    }

    @Override
    public void setRangeMin(T rangeMin) {
        min = rangeMin;
    }

    @Override
    public T getRangeMax() {
        return max;
    }

    @Override
    public void setRangeMax(T rangeMax) {
        max = rangeMax;
    }


    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (min != null) {
            htmlAttributes.put("min", min.toString());
        }
        if (max != null) {
            htmlAttributes.put("max", max.toString());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
