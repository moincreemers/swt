package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyUpEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlTextInput extends ValueWidget<HtmlTextInput> implements
        HasType, HasValue<HtmlTextInput>, HasLength, HasPattern, HasPlaceholder,
        HasReadonly, HasRequired, HasAutocomplete, HasList, HasKeyInput<HtmlTextInput> {

    public HtmlTextInput() {
        this("", "");
    }

    public HtmlTextInput(String name) {
        this(name, "");
    }

    public HtmlTextInput(String name, String value) {
        super(name, WidgetType.TEXT);
        setType(TypeType.TEXT);
        setValue(value);
    }

    public HtmlTextInput addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
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

    // PATTERN

    private final PatternImpl patternImpl = new PatternImpl(this);

    @Override
    public HasPattern getPatternImpl() {
        return patternImpl;
    }

    @Override
    public String getPattern() {
        return patternImpl.getPattern();
    }

    @Override
    public void setPattern(String pattern) {
        patternImpl.setPattern(pattern);
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

    // KEYINPUT

    private final KeyInputImpl<HtmlTextInput> keyInputImpl = new KeyInputImpl<>(this);

    @Override
    public HasKeyInput<HtmlTextInput> getKeyInputImpl() {
        return keyInputImpl;
    }

    @Override
    public HtmlTextInput onInput(InputEventHandler eventHandler) {
        return keyInputImpl.onInput(eventHandler);
    }

    @Override
    public HtmlTextInput onKeyDown(KeyDownEventHandler eventHandler) {
        return keyInputImpl.onKeyDown(eventHandler);
    }

    @Override
    public HtmlTextInput onKeyPress(KeyPressEventHandler eventHandler) {
        return keyInputImpl.onKeyPress(eventHandler);
    }

    @Override
    public HtmlTextInput onKeyUp(KeyUpEventHandler eventHandler) {
        return keyInputImpl.onKeyUp(eventHandler);
    }
}
