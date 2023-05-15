package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class CaptureImpl implements HasCapture {
    private final Widget widget;
    private CaptureType capture = CaptureType.UNSPECIFIED;

    public CaptureImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasCapture getCaptureImpl() {
        return this;
    }

    @Override
    public CaptureType getCapture() {
        return capture;
    }

    @Override
    public void setCapture(CaptureType capture) {
        this.capture = capture;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (capture != null) {
            htmlAttributes.put("capture", capture.getValue());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
