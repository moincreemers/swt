package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AcceptCharSetImpl implements HasAcceptCharSet {
    private final Widget widget;
    private final java.util.List<String> acceptCharSet = new ArrayList<>();

    public AcceptCharSetImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasAcceptCharSet getAcceptCharSetImpl() {
        return this;
    }

    @Override
    public List<String> getAcceptCharSet() {
        return null;
    }

    @Override
    public void setAcceptCharSet(List<String> acceptCharSet) {
        if (acceptCharSet == null) {
            return;
        }
        this.acceptCharSet.clear();
        this.acceptCharSet.addAll(acceptCharSet);
    }

    @Override
    public void addAcceptCharSet(String acceptCharSet) {
        this.acceptCharSet.add(acceptCharSet);
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (acceptCharSet != null && !acceptCharSet.isEmpty()) {
            htmlAttributes.put("accept-charset", String.join(" ", acceptCharSet));
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
