package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.logging.Logger;


public class Data extends DataSourceWidget implements HasValue<Data> {
    private static final Logger LOG = Logger.getLogger(Data.class.getName());
    private static final String EMPTY_JSON_OBJECT = "{}";

    public Data(String name) {
        this(name, null);
    }

    public Data(String name, String value) {
        super(WidgetType.DATA, true, false);
        setName(name);
        setValue(value);
    }

    // HASVALUE + HASNAME

    private final ValueImpl<Data> valueImpl = new ValueImpl<>(this);

    @Override
    public HasName<Data> getNameImpl() {
        return valueImpl;
    }

    @Override
    public String getName() {
        return valueImpl.getName();
    }

    @Override
    public Data setName(String name) {
        return valueImpl.setName(name);
    }

    @Override
    public HasValue<Data> getValueImpl() {
        return valueImpl;
    }

    @Override
    public String getValue() {
        return valueImpl.getValue();
    }

    @Override
    public Data setValue(String value) {
        return valueImpl.setValue(value);
    }

    @Override
    public Data onChange(ChangeEventHandler eventHandler) {
        return valueImpl.onChange(eventHandler);
    }
}
