package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class HtmlSelect extends ValueWidget<HtmlSelect> implements
        HasOptions, HasValue<HtmlSelect>, HasAutocomplete {
    private int size = 1;
    private boolean multiple;

    public HtmlSelect() {
        this("", 1, false);
    }

    public HtmlSelect(String name) {
        this(name, 1, false);
    }

    public HtmlSelect(int size) {
        this("", size, false);
    }

    public HtmlSelect(String name, int size) {
        this(name, size, false);
    }

    public HtmlSelect(int size, boolean multiple) {
        this("", size, multiple);
    }

    public HtmlSelect(String name, int size, boolean multiple) {
        super(name, WidgetType.SELECT);
        setSize(size);
        setMultiple(multiple);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = Math.max(multiple ? 2 : 1, size);
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        htmlAttributes.put("size", Integer.valueOf(getSize()).toString());
        if (multiple) {
            htmlAttributes.put("multiple", "true");
        }
    }

    @Override
    protected DataSourceUsage getDefaultDataSourceUsage() {
        return DataSourceUsage.OPTIONS;
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
}
