package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyUpEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlTextAreaInput extends ValueWidget<HtmlTextAreaInput, String, ValueDataSourceUsage> implements
        HasValue<HtmlTextAreaInput, String>, HasLength, HasPlaceholder, HasReadonly, HasRequired,
        HasAutocomplete, HasKeyInput<HtmlTextAreaInput> {
    public HtmlTextAreaInput(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.TEXT_AREA, JsType.STRING);
    }

    public HtmlTextAreaInput() {
        this(NAMELESS, DEFAULT_VALUE_STRING);
    }

    public HtmlTextAreaInput(String name) {
        this(name, DEFAULT_VALUE_STRING);
    }

    public HtmlTextAreaInput(String name, String value) {
        super(name, WidgetType.TEXT_AREA, JsType.STRING);
        setValue(value);
    }

    public HtmlTextAreaInput addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    // LENGTH

    private final LengthImpl lengthImpl = new LengthImpl(this);

    @Override
    public HasLength getLengthImpl() {
        return lengthImpl;
    }

    @Override
    public Integer getLengthMin() {
        return lengthImpl.getLengthMin();
    }

    @Override
    public void setLengthMin(Integer rangeMin) {
        lengthImpl.setLengthMin(rangeMin);
    }

    @Override
    public Integer getLengthMax() {
        return lengthImpl.getLengthMax();
    }

    @Override
    public void setLengthMax(Integer lengthMax) {
        lengthImpl.setLengthMax(lengthMax);
    }

    // PLACEHOLDER

    private final PlaceholderImpl placeholderImpl = new PlaceholderImpl(this);

    @Override
    public HasPlaceholder getPlaceholderImpl() {
        return placeholderImpl;
    }

    @Override
    public String getPlaceholder() {
        return placeholderImpl.getPlaceholder();
    }

    @Override
    public void setPlaceholder(String placeholder) {
        placeholderImpl.setPlaceholder(placeholder);
    }

    // READONLY


    private final ReadonlyImpl readonlyImpl = new ReadonlyImpl(this);

    @Override
    public HasReadonly getReadonlyImpl() {
        return readonlyImpl;
    }

    @Override
    public boolean getReadonly() {
        return readonlyImpl.getReadonly();
    }

    @Override
    public void setReadonly(boolean readonly) {
        readonlyImpl.setReadonly(readonly);
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

    // KEYINPUT

    private final KeyInputImpl<HtmlTextAreaInput> keyInputImpl = new KeyInputImpl<>(this);

    @Override
    public HasKeyInput<HtmlTextAreaInput> getKeyInputImpl() {
        return keyInputImpl;
    }

    @Override
    public HtmlTextAreaInput onInput(InputEventHandler eventHandler) {
        return keyInputImpl.onInput(eventHandler);
    }

    @Override
    public HtmlTextAreaInput onKeyDown(KeyDownEventHandler eventHandler) {
        return keyInputImpl.onKeyDown(eventHandler);
    }

    @Override
    public HtmlTextAreaInput onKeyPress(KeyPressEventHandler eventHandler) {
        return keyInputImpl.onKeyPress(eventHandler);
    }

    @Override
    public HtmlTextAreaInput onKeyUp(KeyUpEventHandler eventHandler) {
        return keyInputImpl.onKeyUp(eventHandler);
    }
}
