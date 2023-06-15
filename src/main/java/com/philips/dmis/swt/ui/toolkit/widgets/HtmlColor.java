package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlColor extends ValueWidget<HtmlColor,ValueDataSourceUsage> implements
        HasType, HasValue<HtmlColor>, HasAutocomplete, HasList {

    public HtmlColor() {
        this("", "");
    }

    public HtmlColor(String name) {
        this(name, "");
    }

    public HtmlColor(String name, String value) {
        super(name, WidgetType.COLOR);
        setType(TypeType.COLOR);
        setValue(value);
    }

    public HtmlColor addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
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

    // AUTOCOMPLETE

    private final AutocompleteImpl autocompleteImpl = new AutocompleteImpl(this);

    @Override
    public HasAutocomplete getAutocompleteImpl() {
        return autocompleteImpl;
    }

    @Override
    public AutocompleteType getAutocomplete() {
        return autocompleteImpl.getAutocomplete();
    }

    @Override
    public void setAutocomplete(AutocompleteType accept) {
        autocompleteImpl.setAutocomplete(accept);
    }

    // LIST

    private final ListImpl listImpl = new ListImpl(this);

    @Override
    public HasList getListImpl() {
        return listImpl;
    }

    @Override
    public String getList() {
        return listImpl.getList();
    }

    @Override
    public void setList(String list) {
        listImpl.setList(list);
    }

    @Override
    public void setList(HtmlDataList htmlDataList) {
        listImpl.setList(htmlDataList);
    }
}
