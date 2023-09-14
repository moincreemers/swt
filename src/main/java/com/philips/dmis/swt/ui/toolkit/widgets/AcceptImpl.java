package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.ArrayList;
import java.util.Map;

@PageXmlElement("accept")
public class AcceptImpl implements HasAccept {
    private final Widget widget;
    private final java.util.List<String> accept = new ArrayList<>();

    public AcceptImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasAccept getAcceptImpl() {
        return this;
    }

    @Override
    public java.util.List<String> getAccept() {
        return accept;
    }

    @Override
    public void setAccept(java.util.List<String> accept) {
        if (accept == null) {
            return;
        }
        this.accept.clear();
        this.accept.addAll(accept);
    }

    @Override
    public void addAccept(String accept) {
        this.accept.add(accept);
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (accept != null) {
            htmlAttributes.put("accept", String.join(",", accept));
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
