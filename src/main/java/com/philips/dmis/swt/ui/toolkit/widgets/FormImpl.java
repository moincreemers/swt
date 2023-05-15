package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class FormImpl implements HasForm {
    private final Widget widget;
    private String form;

    public FormImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasForm getFormImpl() {
        return this;
    }

    @Override
    public String getForm() {
        return form;
    }

    @Override
    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public void setForm(HtmlForm htmlForm) {
        this.form = htmlForm.getId();
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (form != null && !form.isEmpty()) {
            htmlAttributes.put("form", form);
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
