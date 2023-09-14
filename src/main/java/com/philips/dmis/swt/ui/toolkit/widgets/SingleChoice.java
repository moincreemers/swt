package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Set;

@PageXmlElement("singleChoiceAppearance")
public class SingleChoice extends ValueWidget<SingleChoice, String, ValueAndItemsDataSourceUsage> implements
        HasOptions, HasValue<SingleChoice, String> {
    private SingleChoiceAppearance singleChoiceAppearance = SingleChoiceAppearance.DEFAULT;

    public SingleChoice(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.SINGLE_CHOICE, JsType.STRING);
    }

    public SingleChoice() {
        this(NAMELESS, SingleChoiceAppearance.DEFAULT);
    }

    public SingleChoice(String name) {
        this(name, SingleChoiceAppearance.DEFAULT);
    }

    public SingleChoice(SingleChoiceAppearance singleChoiceAppearance, Widget... tabPanels) {
        this(NAMELESS, singleChoiceAppearance, tabPanels);
    }

    public SingleChoice(String name, SingleChoiceAppearance singleChoiceAppearance, Widget... tabPanels) {
        super(name, WidgetType.SINGLE_CHOICE, JsType.STRING);
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
    public void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters) {
        // requiredDataAdapters.add(KeyValueListDataAdapter.class);
    }
}
