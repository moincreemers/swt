package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class ReferrerPolicyImpl implements HasReferrerPolicy {
    private final Widget widget;
    private ReferrerPolicyType referrerPolicyType = ReferrerPolicyType.STRICT_ORIGIN_WHEN_CROSS_ORIGIN;

    public ReferrerPolicyImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasReferrerPolicy getReferrerPolicyImpl() {
        return this;
    }

    @Override
    public ReferrerPolicyType getReferrerPolicy() {
        return referrerPolicyType;
    }

    @Override
    public void setReferrerPolicy(ReferrerPolicyType referrerPolicy) {
        this.referrerPolicyType = referrerPolicyType;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (referrerPolicyType != null) {
            htmlAttributes.put("referrerpolicy", referrerPolicyType.getValue());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
