package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlCheckInput extends ValueWidget<HtmlCheckInput> implements
        HasType, HasValue<HtmlCheckInput>, HasRequired {
    public HtmlCheckInput() {
        this("");
    }

    public HtmlCheckInput(String name) {
        super(name, WidgetType.CHECK);
        setType(TypeType.CHECK);
    }

    public HtmlCheckInput addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(DataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    // TYPE

    private final TypeImpl typeImpl = new TypeImpl(this);

    @Override
    public HasType getTypeImpl() {
        return typeImpl;
    }

    @Override
    public TypeType getType() {
        return typeImpl.getType();
    }

    @Override
    public void setType(TypeType type) {
        typeImpl.setType(type);
    }


    // REQUIRED

    private final RequiredImpl requiredImpl = new RequiredImpl(this);

    @Override
    public HasRequired getRequiredImpl() {
        return requiredImpl;
    }

    @Override
    public boolean getRequired() {
        return requiredImpl.getRequired();
    }

    @Override
    public void setRequired(boolean required) {
        requiredImpl.setRequired(required);
    }
}
