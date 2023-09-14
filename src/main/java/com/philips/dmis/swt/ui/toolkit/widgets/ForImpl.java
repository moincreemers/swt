package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("for")
public class ForImpl implements HasFor {
    private final Widget widget;
    private String _for;

    public ForImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasFor getForImpl() {
        return this;
    }

    @Override
    public String getFor() {
        return _for;
    }

    @Override
    public void setFor(String _for) {
        this._for = _for;
    }

    @Override
    public void setFor(Widget widget) {
        if (widget != null) {
            _for = widget.getId();
        }
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (_for != null && !_for.isEmpty()) {
            htmlAttributes.put("for", _for);
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
