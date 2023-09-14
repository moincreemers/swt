package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyUpEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlWeekInput extends ValueWidget<HtmlWeekInput, String, ValueDataSourceUsage> implements
        HasType, HasValue<HtmlWeekInput, String>, HasKeyInput<HtmlWeekInput>, HasRange<String>, HasStep, HasReadonly,
        HasRequired, HasAutocomplete, HasList {
    public HtmlWeekInput(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.WEEK, JsType.STRING);
        setType(TypeType.WEEK);
    }

    public HtmlWeekInput() {
        this(NAMELESS);
    }

    public HtmlWeekInput(String name) {
        this(name, DEFAULT_VALUE_STRING);
    }

    public HtmlWeekInput(String name, String value) {
        super(name, WidgetType.WEEK, JsType.STRING);
        setType(TypeType.WEEK);
        setValue(value);
    }

    public HtmlWeekInput addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
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

    // RANGE

    private final RangeImpl<String> rangeImpl = new RangeImpl<>(this);

    @Override
    public HasRange<String> getRangeImpl() {
        return rangeImpl;
    }

    @Override
    public String getRangeMin() {
        return rangeImpl.getRangeMin();
    }

    @Override
    public void setRangeMin(String rangeMin) {
        rangeImpl.setRangeMin(rangeMin);
    }

    @Override
    public String getRangeMax() {
        return rangeImpl.getRangeMax();
    }

    @Override
    public void setRangeMax(String rangeMax) {
        rangeImpl.setRangeMax(rangeMax);
    }

    // STEP

    private final StepImpl stepImpl = new StepImpl(this);

    @Override
    public HasStep getStepImpl() {
        return stepImpl;
    }

    @Override
    public boolean isStepAny() {
        return stepImpl.isStepAny();
    }

    @Override
    public void setStepAny(boolean stepAny) {
        stepImpl.setStepAny(stepAny);
    }

    @Override
    public Float getStep() {
        return stepImpl.getStep();
    }

    @Override
    public void setStep(Float step) {
        stepImpl.setStep(step);
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

    private final KeyInputImpl<HtmlWeekInput> keyInputImpl = new KeyInputImpl<>(this);

    @Override
    public HasKeyInput<HtmlWeekInput> getKeyInputImpl() {
        return keyInputImpl;
    }

    @Override
    public HtmlWeekInput onInput(InputEventHandler eventHandler) {
        return keyInputImpl.onInput(eventHandler);
    }

    @Override
    public HtmlWeekInput onKeyDown(KeyDownEventHandler eventHandler) {
        return keyInputImpl.onKeyDown(eventHandler);
    }

    @Override
    public HtmlWeekInput onKeyPress(KeyPressEventHandler eventHandler) {
        return keyInputImpl.onKeyPress(eventHandler);
    }

    @Override
    public HtmlWeekInput onKeyUp(KeyUpEventHandler eventHandler) {
        return keyInputImpl.onKeyUp(eventHandler);
    }
}
