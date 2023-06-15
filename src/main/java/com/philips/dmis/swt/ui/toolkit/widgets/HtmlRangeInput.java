package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlRangeInput extends ValueWidget<HtmlRangeInput, ValueDataSourceUsage> implements
        HasType, HasValue<HtmlRangeInput>, HasRange<Integer>, HasStep, HasList {
    public HtmlRangeInput() {
        this("", "");
    }

    public HtmlRangeInput(String name) {
        this(name, "");
    }

    public HtmlRangeInput(String name, String value) {
        super(name, WidgetType.RANGE);
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

    private final RangeImpl<Integer> rangeImpl = new RangeImpl<>(this);

    @Override
    public HasRange<Integer> getRangeImpl() {
        return rangeImpl;
    }

    @Override
    public Integer getRangeMin() {
        return rangeImpl.getRangeMin();
    }

    @Override
    public void setRangeMin(Integer rangeMin) {
        rangeImpl.setRangeMin(rangeMin);
    }

    @Override
    public Integer getRangeMax() {
        return rangeImpl.getRangeMax();
    }

    @Override
    public void setRangeMax(Integer rangeMax) {
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
