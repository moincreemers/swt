package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("method")
public class MethodImpl implements HasMethod {
    private final Widget widget;
    private String method;
    private final boolean button, form;

    public MethodImpl(Widget widget) {
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
    public HasMethod getMethodImpl() {
        return this;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public void setMethod(FormMethodType formMethod) {
        this.method = formMethod.name().toLowerCase();
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (method != null && !method.isEmpty()) {
            if(button) {
                htmlAttributes.put("formmethod", method);
            }
            if(form) {
                htmlAttributes.put("method", method);
            }
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
