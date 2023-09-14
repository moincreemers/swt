package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ErrorEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ResponseEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public abstract class DataProviderWidget<T extends Widget> extends Widget implements
        HasValue<DataProviderWidget<T>, Object> {
    public DataProviderWidget(WidgetConfigurator widgetConfigurator, WidgetType widgetType) {
        super(widgetConfigurator, widgetType);
    }

    public DataProviderWidget(WidgetType widgetType) {
        super(widgetType);
    }

    public T onResponse(ResponseEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return (T) this;
    }

    public T onError(ErrorEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return (T) this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
    }

    // VALUE

    private final ValueImpl<DataProviderWidget<T>, Object> valueImpl = new ValueImpl<>(this, JsType.OBJECT);

    @Override
    public HasName<DataProviderWidget<T>> getNameImpl() {
        return valueImpl;
    }

    @Override
    public String getName() {
        return valueImpl.getName();
    }

    @Override
    public DataProviderWidget<T> setName(String name) {
        valueImpl.setName(name);
        return this;
    }

    @Override
    public HasValue<DataProviderWidget<T>, Object> getValueImpl() {
        return valueImpl;
    }

    @Override
    public Object getValue() {
        return valueImpl.getValue();
    }

    @Override
    public DataProviderWidget<T> setValue(Object value) {
        valueImpl.setValue(value);
        return this;
    }
}
