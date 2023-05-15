package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class SingleChoice extends ValueWidget<SingleChoice> implements
        HasOptions, HasValue<SingleChoice> {
    private SingleChoiceAppearance singleChoiceAppearance = SingleChoiceAppearance.DEFAULT;

    public SingleChoice() {
        this("", SingleChoiceAppearance.DEFAULT);
    }

    public SingleChoice(String name) {
        this(name, SingleChoiceAppearance.DEFAULT);
    }

    public SingleChoice(SingleChoiceAppearance singleChoiceAppearance, Widget... tabPanels) {
        this("", singleChoiceAppearance, tabPanels);
    }

    public SingleChoice(String name, SingleChoiceAppearance singleChoiceAppearance, Widget... tabPanels) {
        super(name, WidgetType.SINGLE_CHOICE);
        setSingleChoiceAppearance(singleChoiceAppearance);
        addSiblings(tabPanels);
    }

    public SingleChoiceAppearance getSingleChoiceAppearance() {
        return singleChoiceAppearance;
    }

    public void setSingleChoiceAppearance(SingleChoiceAppearance singleChoiceAppearance) {
        if (singleChoiceAppearance == null) {
            return;
        }
        if (this.singleChoiceAppearance != SingleChoiceAppearance.DEFAULT) {
            removeClassName(this.singleChoiceAppearance.className);
        }
        this.singleChoiceAppearance = singleChoiceAppearance;
        addClassName(singleChoiceAppearance.className);
    }

    @Override
    protected DataSourceUsage getDefaultDataSourceUsage() {
        return DataSourceUsage.OPTIONS;
    }
}
