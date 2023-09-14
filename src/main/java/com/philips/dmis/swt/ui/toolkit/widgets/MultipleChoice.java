package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Set;

@PageXmlElement("multipleChoiceAppearance")
public class MultipleChoice extends ValueWidget<MultipleChoice, String[], ValueAndItemsDataSourceUsage> implements
        HasOptions, HasValue<MultipleChoice, String[]> {
    private MultipleChoiceAppearance multipleChoiceAppearance = MultipleChoiceAppearance.DEFAULT;

    public MultipleChoice(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.MULTIPLE_CHOICE, JsType.ARRAY);
    }

    public MultipleChoice() {
        this(NAMELESS, MultipleChoiceAppearance.DEFAULT);
    }

    public MultipleChoice(String name) {
        this(name, MultipleChoiceAppearance.DEFAULT);
    }

    public MultipleChoice(MultipleChoiceAppearance multipleChoiceAppearance) {
        this(NAMELESS, multipleChoiceAppearance);
    }

    public MultipleChoice(String name, MultipleChoiceAppearance multipleChoiceAppearance) {
        super(name, WidgetType.MULTIPLE_CHOICE, JsType.ARRAY);
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
    public void getRequiredDataAdapters(Set<Class<? extends DataAdapter>> requiredDataAdapters) {
        // requiredDataAdapters.add(KeyValueListDataAdapter.class);
    }
}
