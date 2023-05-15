package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class LengthImpl implements HasLength {
    private final Widget widget;
    private Integer min;
    private Integer max;

    public LengthImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasLength getLengthImpl() {
        return this;
    }

    @Override
    public Integer getLengthMin() {
        return min;
    }

    @Override
    public void setLengthMin(Integer lengthMin) {
        min = lengthMin;
    }

    @Override
    public Integer getLengthMax() {
        return max;
    }

    @Override
    public void setLengthMax(Integer lengthMax) {
        max = lengthMax;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (min != null) {
            htmlAttributes.put("minLength", min.toString());
        }
        if (max != null) {
            htmlAttributes.put("maxLength", max.toString());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
