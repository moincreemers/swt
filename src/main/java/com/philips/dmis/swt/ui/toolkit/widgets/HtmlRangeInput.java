package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlRangeInput extends ValueWidget<HtmlRangeInput, Double, ValueDataSourceUsage> implements
        HasType, HasValue<HtmlRangeInput, Double>, HasRange<Double>, HasStep, HasList {
    public HtmlRangeInput(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.RANGE, JsType.NUMBER);
        setType(TypeType.RANGE);
    }

    public HtmlRangeInput() {
        this(NAMELESS, DEFAULT_VALUE_NUMBER);
    }

    public HtmlRangeInput(String name) {
        this(name, DEFAULT_VALUE_NUMBER);
    }

    public HtmlRangeInput(String name, Double value) {
        super(name, WidgetType.RANGE, JsType.NUMBER);
        setType(TypeType.RANGE);
        setValue(value);
    }

    public HtmlRangeInput addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
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

    private final RangeImpl<Double> rangeImpl = new RangeImpl<>(this);

    @Override
    public HasRange<Double> getRangeImpl() {
        return rangeImpl;
    }

    @Override
    public Double getRangeMin() {
        return rangeImpl.getRangeMin();
    }

    @Override
    public void setRangeMin(Double rangeMin) {
        rangeImpl.setRangeMin(rangeMin);
    }

    @Override
    public Double getRangeMax() {
        return rangeImpl.getRangeMax();
    }

    @Override
    public void setRangeMax(Double rangeMax) {
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
