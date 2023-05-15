package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class TargetImpl implements HasTarget {
    private final Widget widget;
    private String target;
    private final boolean button, form;

    public TargetImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
        button = widget instanceof HtmlButton;
        form = widget instanceof HtmlForm;
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasTarget getTargetImpl() {
        return this;
    }


    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public void setTarget(TargetType target) {
        this.target = target.getValue();
    }
    
    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (target != null && !target.isEmpty()) {
            if (button) {
                htmlAttributes.put("formtarget", target);
            }
            if (form) {
                htmlAttributes.put("target", target);
            }
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
