package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public abstract class ValueWidget<T extends Widget, E extends HasDataSourceUsage> extends DataBoundWidget<T, E> implements
        HasValue<T>, HasDisabled {

    public ValueWidget(String name, WidgetType widgetType) {
        super(widgetType);
        setName(name);
    }

    // VALUE

    private final ValueImpl<T> valueImpl = new ValueImpl<>((T) this);

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
    public HasValue<T> getValueImpl() {
        return valueImpl;
    }

    @Override
    public String getValue() {
        return valueImpl.getValue();
    }

    @Override
    public T setValue(String value) {
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
