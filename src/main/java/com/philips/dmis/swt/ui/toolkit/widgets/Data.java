package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;


public class Data extends DataSourceWidget implements HasValue<Data, Object> {
    public Data(String name) {
        this(name, null);
    }

    public Data(String name, String value) {
        super(WidgetType.DATA);
        setExpectServiceResponse(true);
        setAutoRefresh(false);
        setName(name);
        setValue(value);
        setCacheType(CacheType.DISABLED);
    }

    public Data(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.DATA);
        setExpectServiceResponse(true);
        setAutoRefresh(false);
        setCacheType(CacheType.DISABLED);
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.OBJECT;
    }

    // HASVALUE + HASNAME

    private final ValueImpl<Data, Object> valueImpl = new ValueImpl<>(this, JsType.OBJECT);

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
    public HasValue<Data, Object> getValueImpl() {
        return valueImpl;
    }

    @Override
    public Object getValue() {
        return valueImpl.getValue();
    }

    @Override
    public Data setValue(Object value) {
        return valueImpl.setValue(value);
    }

    @Override
    public Data onChange(ChangeEventHandler eventHandler) {
        return valueImpl.onChange(eventHandler);
    }
}
