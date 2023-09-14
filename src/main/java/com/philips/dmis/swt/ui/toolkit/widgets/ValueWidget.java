package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

@PageXmlElement("returnType")
public abstract class ValueWidget<T extends Widget, V, E extends HasDataSourceUsage> extends DataBoundWidget<T, E> implements
        HasValue<T, V>, HasDisabled {

    private final JsType returnType;

    public ValueWidget(WidgetConfigurator widgetConfigurator, String name, WidgetType widgetType, JsType returnType) {
        super(widgetConfigurator, widgetType);
        valueImpl = new ValueImpl<>((T) this, returnType);
        setName(name);
        this.returnType = returnType;
    }

    public ValueWidget(String name, WidgetType widgetType, JsType returnType) {
        super(widgetType);
        valueImpl = new ValueImpl<>((T) this, returnType);
        setName(name);
        this.returnType = returnType;
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return returnType;
    }

    // VALUE

    private final ValueImpl<T, V> valueImpl;

    @Override
    public HasName<T> getNameImpl() {
        return valueImpl;
    }

    @Override
    public String getName() {
        return valueImpl.getName();
    }

    @Override
    public T setName(String name) {
        valueImpl.setName(name);
        return (T) this;
    }

    @Override
    public HasValue<T, V> getValueImpl() {
        return valueImpl;
    }

    @Override
    public V getValue() {
        return valueImpl.getValue();
    }

    @Override
    public T setValue(V value) {
        valueImpl.setValue(value);
        return (T) this;
    }

    @Override
    public T onChange(ChangeEventHandler eventHandler) {
        asWidget().getEventHandlers().add(eventHandler);
        return (T) this;
    }

    // DISABLED

    private final DisabledImpl disabledImpl = new DisabledImpl(this);

    @Override
    public HasDisabled getDisabledImpl() {
        return disabledImpl;
    }

    @Override
    public boolean getDisabled() {
        return disabledImpl.getDisabled();
    }

    @Override
    public void setDisabled(boolean disabled) {
        disabledImpl.setDisabled(disabled);
    }
}
