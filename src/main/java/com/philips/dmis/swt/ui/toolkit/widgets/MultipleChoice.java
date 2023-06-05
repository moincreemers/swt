package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Set;

public class MultipleChoice extends ValueWidget<MultipleChoice> implements
        HasOptions, HasValue<MultipleChoice> {
    private MultipleChoiceAppearance multipleChoiceAppearance = MultipleChoiceAppearance.DEFAULT;

    public MultipleChoice() {
        this("", MultipleChoiceAppearance.DEFAULT);
    }

    public MultipleChoice(String name) {
        this(name, MultipleChoiceAppearance.DEFAULT);
    }

    public MultipleChoice(MultipleChoiceAppearance multipleChoiceAppearance) {
        this("", multipleChoiceAppearance);
    }

    public MultipleChoice(String name, MultipleChoiceAppearance multipleChoiceAppearance) {
        super(name, WidgetType.MULTIPLE_CHOICE);
        setMultipleChoiceAppearance(multipleChoiceAppearance);
    }

    public MultipleChoiceAppearance getMultipleChoiceAppearance() {
        return multipleChoiceAppearance;
    }

    public void setMultipleChoiceAppearance(MultipleChoiceAppearance multipleChoiceAppearance) {
        if (multipleChoiceAppearance == null) {
            return;
        }
        if (this.multipleChoiceAppearance != MultipleChoiceAppearance.DEFAULT) {
            removeClassName(this.multipleChoiceAppearance.className);
        }
        this.multipleChoiceAppearance = multipleChoiceAppearance;
        addClassName(multipleChoiceAppearance.className);
    }

    @Override
    protected DataSourceUsage getDefaultDataSourceUsage() {
        return DataSourceUsage.OPTIONS;
    }

    @Override
    public void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters) {
        // requiredDataAdapters.add(KeyValueListDataAdapter.class);
    }
}
