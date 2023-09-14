package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement("value")
public class ValueImpl<T extends Widget, V> extends NameImpl<T> implements
        HasValue<T, V> {
    private V value = null;
    private final JsType jsType;

    public ValueImpl(T widget, JsType jsType) {
        super(widget);
        this.jsType = jsType;
    }

    @Override
    public HasValue<T, V> getValueImpl() {
        return this;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public T setValue(V value) {
//        if (value == null) {
//            value = "";
//        }
        this.value = value;
        return widget;
    }

    @Override
    public T onChange(ChangeEventHandler eventHandler) {
        widget.getEventHandlers().add(eventHandler);
        return widget;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (value != null) {
            if (widget instanceof HtmlFrame || widget instanceof HtmlImageButton) {
                htmlAttributes.put("src", value.toString());
            } else {
                htmlAttributes.put("value", value.toString());
            }
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return jsType;
    }
}
