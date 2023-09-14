package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyUpEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class HtmlPasswordInput extends ValueWidget<HtmlPasswordInput, String, ValueDataSourceUsage> implements
        HasType, HasValue<HtmlPasswordInput, String>, HasKeyInput<HtmlPasswordInput>, HasLength, HasPattern, HasPlaceholder,
        HasReadonly, HasRequired {
    public HtmlPasswordInput(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.PASSWORD, JsType.STRING);
        setType(TypeType.PASSWORD);
    }

    public HtmlPasswordInput() {
        this(NAMELESS);
    }

    public HtmlPasswordInput(String name) {
        super(name, WidgetType.PASSWORD, JsType.STRING);
        setType(TypeType.PASSWORD);
    }

    public HtmlPasswordInput addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        htmlAttributes.put("autocomplete", "off");
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

    // KEYINPUT

    private final KeyInputImpl<HtmlPasswordInput> keyInputImpl = new KeyInputImpl<>(this);

    @Override
    public HasKeyInput<HtmlPasswordInput> getKeyInputImpl() {
        return keyInputImpl;
    }

    @Override
    public HtmlPasswordInput onInput(InputEventHandler eventHandler) {
        return keyInputImpl.onInput(eventHandler);
    }

    @Override
    public HtmlPasswordInput onKeyDown(KeyDownEventHandler eventHandler) {
        return keyInputImpl.onKeyDown(eventHandler);
    }

    @Override
    public HtmlPasswordInput onKeyPress(KeyPressEventHandler eventHandler) {
        return keyInputImpl.onKeyPress(eventHandler);
    }

    @Override
    public HtmlPasswordInput onKeyUp(KeyUpEventHandler eventHandler) {
        return keyInputImpl.onKeyUp(eventHandler);
    }
}
