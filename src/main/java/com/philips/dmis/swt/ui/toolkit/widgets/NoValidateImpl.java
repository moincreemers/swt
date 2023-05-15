package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class NoValidateImpl implements HasNoValidate {
    private final Widget widget;
    private Boolean noValidate;
    private final boolean button, form;

    public NoValidateImpl(Widget widget) {
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
    public HasNoValidate getNoValidateImpl() {
        return this;
    }

    @Override
    public Boolean getNoValidate() {
        return noValidate;
    }

    @Override
    public void setNoValidate(Boolean noValidate) {
this.noValidate = noValidate;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (noValidate != null) {
            if(button) {
                htmlAttributes.put("formnovalidate", "");
            }
            if(form) {
                htmlAttributes.put("novalidate", "");
            }
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
