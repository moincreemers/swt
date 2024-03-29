package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.css.CssConstant;
import com.philips.dmis.swt.ui.toolkit.css.CssValue;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;

import java.util.List;
import java.util.ArrayList;

public class TabWidget extends Composite {
    static class Pair {
        final String value;
        final Panel panel;

        Pair(String value, CssValue width, CssValue height) {
            this.value = value;
            panel = new Panel(PanelType.TAB_PAGE);
            panel.setOverflowAndSize(OverflowType.FIXED_SIZE, width, height);
        }
    }

    private final List<Pair> values = new ArrayList();
    private int initialIndex = -1;
    private final CssValue width;
    private final CssValue height;
    private final SingleChoice tabWidget = new SingleChoice(SingleChoiceAppearance.TABS);

    public TabWidget() {
        this(CssConstant.EMPTY, CssConstant.EMPTY);
    }

    public TabWidget(CssValue width, CssValue height) {
        this.width = width;
        this.height = height;
    }

    public TabWidget(String... values) {
        this(CssConstant.EMPTY, CssConstant.EMPTY);
        for (String value : values) {
            addValue(value);
        }
    }

    public TabWidget(CssValue width, CssValue height, String... values) {
        this(width, height);
        for (String value : values) {
            addValue(value);
        }
    }

    public TabWidget onChange(ChangeEventHandler eventHandler) {
        tabWidget.getEventHandlers().add(eventHandler);
        return this;
    }

    public ValueWidget<SingleChoice, String, ValueAndItemsDataSourceUsage> getValueWidget() {
        return tabWidget;
    }

    public void addValue(String value) {
        if (values.contains(value)) {
            throw new IllegalArgumentException("duplicate value " + value);
        }
        values.add(new Pair(value, width, height));
    }

    public int size() {
        return values.size();
    }

    public String value(int index) {
        return values.get(index).value;
    }

    public Panel panel(int index) {
        return values.get(index).panel;
    }

    public Panel panel(String value) {
        for (Pair pair : values) {
            if (pair.value.equals(value)) {
                return pair.panel;
            }
        }
        throw new IllegalArgumentException("value not found " + value);
    }

    public int getInitialIndex() {
        if (values.isEmpty()) {
            return -1;
        }
        if (initialIndex == -1 || initialIndex >= values.size()) {
            return 0;
        }
        return initialIndex;
    }

    public void setInitialIndex(int initialIndex) {
        this.initialIndex = initialIndex;
    }

    @Override
    protected void build() throws Exception {
        if (values.isEmpty()) {
            return;
        }

        add(tabWidget);
        ChangeEventHandler changeEventHandler = new ChangeEventHandler();
        tabWidget.onChange(changeEventHandler);
        StaticData staticData = add(new StaticData(
                DataBuilder.list(values.stream().map(pair -> pair.value).toList()).getData()
        ));
        tabWidget.addDataSource(ValueAndItemsDataSourceUsage.ITEMS, staticData, new KeyValueListDataAdapter());

        int initialIndex = getInitialIndex();
        for (int p = 0; p < values.size(); p++) {
            Pair pair = values.get(p);
            if (p == initialIndex) {
                tabWidget.setValue(pair.value);
                pair.panel.setVisible(true);
            } else {
                pair.panel.setVisible(false);
            }
            changeEventHandler.getStatements().add(
                    M.SetVisible(pair.panel, V.Is(V.GetValue(tabWidget), V.Const(pair.value))));

            // add panel to composite
            add(pair.panel);
        }

    }
}
