package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("action")
public class ActionImpl implements HasAction {
    private final Widget widget;
    private String action;
    private final boolean button, form;

    public ActionImpl(Widget widget) {
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
    public HasAction getActionImpl() {
        return this;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (action != null && !action.isEmpty()) {
            if (button) {
                htmlAttributes.put("formaction", action);
            }
            if (form) {
                htmlAttributes.put("action", action);
            }
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
