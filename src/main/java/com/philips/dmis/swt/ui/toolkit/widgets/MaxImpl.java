package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"max", "value"})
public class MaxImpl implements HasMax {
    private final Widget widget;
    private Float max;
    private Float value;

    public MaxImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasMax getMaxImpl() {
        return this;
    }

    @Override
    public Float getMax() {
        return max;
    }

    @Override
    public void setMax(Float max) {
        if (max != null) {
            max = Math.min(1f, Math.max(0f, max));
        }
        this.max = max;
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public void setValue(Float value) {
        if (value != null) {
            value = Math.min(1f, Math.max(0f, value));
        }
        this.value = value;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (max != null) {
            htmlAttributes.put("max", max.toString());
        }
        if (value != null) {
            htmlAttributes.put("value", value.toString());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
