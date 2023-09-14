package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("referrerPolicy")
public class ReferrerPolicyImpl implements HasReferrerPolicy {
    private final Widget widget;
    private ReferrerPolicyType referrerPolicy = ReferrerPolicyType.STRICT_ORIGIN_WHEN_CROSS_ORIGIN;

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
        return referrerPolicy;
    }

    @Override
    public void setReferrerPolicy(ReferrerPolicyType referrerPolicy) {
        this.referrerPolicy = referrerPolicy;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (referrerPolicy != null) {
            htmlAttributes.put("referrerpolicy", referrerPolicy.getValue());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
