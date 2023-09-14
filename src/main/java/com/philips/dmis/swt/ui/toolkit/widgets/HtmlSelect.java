package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;
import java.util.Set;

@PageXmlElement("size")
public class HtmlSelect extends ValueWidget<HtmlSelect, String, ValueAndItemsDataSourceUsage> implements
        HasOptions, HasValue<HtmlSelect, String>, HasAutocomplete, HasMultiple {
    private int size = 1;

    public HtmlSelect(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.SELECT, JsType.STRING);
    }

    public HtmlSelect() {
        this(NAMELESS, 1, false);
    }

    public HtmlSelect(String name) {
        this(name, 1, false);
    }

    public HtmlSelect(int size) {
        this(NAMELESS, size, false);
    }

    public HtmlSelect(String name, int size) {
        this(name, size, false);
    }

    public HtmlSelect(int size, boolean multiple) {
        this(NAMELESS, size, multiple);
    }

    public HtmlSelect(String name, int size, boolean multiple) {
        super(name, WidgetType.SELECT, JsType.STRING);
        setSize(size);
        setMultiple(multiple);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = Math.max(getMultiple() ? 2 : 1, size);
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        htmlAttributes.put("size", Integer.valueOf(getSize()).toString());
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

    // MULTIPLE

    private final MultipleImpl multipleImpl = new MultipleImpl(this);

    @Override
    public HasMultiple getMultipleImpl() {
        return multipleImpl;
    }

    @Override
    public boolean getMultiple() {
        return multipleImpl.getMultiple();
    }

    @Override
    public void setMultiple(boolean multiple) {
        multipleImpl.setMultiple(multiple);
    }

    @Override
    public void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters) {
        // todo: this needs to be smarter
        //requiredDataAdapters.add(KeyValueListDataAdapter.class);
    }
}
