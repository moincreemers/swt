package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class TypeImpl implements HasType {
    private final Widget widget;
    private TypeType type;
    private final boolean input, button;

    public TypeImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
        button = widget instanceof HtmlButton;
        input = !button && (widget instanceof HasValue || widget instanceof HtmlImageButton);
        if (input) {
            type = TypeType.TEXT;
        }
        if (button) {
            type = TypeType.BUTTON;
        }
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasType getTypeImpl() {
        return this;
    }

    @Override
    public TypeType getType() {
        return type;
    }

    @Override
    public void setType(TypeType type) {
        if (type == null) {
            if (input) {
                type = TypeType.TEXT;
            }
            if (button) {
                type = TypeType.BUTTON;
            }
        }
        this.type = type;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        htmlAttributes.put("type", type.getValue());
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
