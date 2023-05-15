package com.philips.dmis.swt.ui.toolkit.widgets;

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

        Pair(String value, Size panelSize) {
            this.value = value;
            panel = new Panel(PanelType.TAB_PAGE);
            if (panelSize != null) {
                panel.setOverflowAndSize(Overflow.FIXED_SIZE, panelSize);
            }
        }
    }

    private final List<Pair> values = new ArrayList();
    private int initialIndex = -1;
    private final Size panelSize;
    private final SingleChoice tabWidget = new SingleChoice(SingleChoiceAppearance.TABS);

    public TabWidget() {
        this.panelSize = null;
    }

    public TabWidget(Size panelSize) {
        this.panelSize = panelSize;
    }

    public TabWidget(String... values) {
        this.panelSize = null;
        for (String value : values) {
            addValue(value);
        }
    }

    public TabWidget(Size panelSize, String... values) {
        this.panelSize = panelSize;
        for (String value : values) {
            addValue(value);
        }
    }

    public TabWidget onChange(ChangeEventHandler eventHandler) {
        tabWidget.getEventHandlers().add(eventHandler);
        return this;
    }

    public ValueWidget<SingleChoice> getValueWidget() {
        return tabWidget;
    }

    public void addValue(String value) {
        if (values.contains(value)) {
            throw new IllegalArgumentException("duplicate value " + value);
        }
        values.add(new Pair(value, panelSize));
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
        StaticData staticData = add(new StaticData(values.stream().map(pair -> pair.value).toList()));
        tabWidget.addDataSource(staticData,
                new KeyValueListDataAdapter("", ""));

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
                    M.SetDisplay(pair.panel, V.Is(V.GetValue(tabWidget), V.Const(pair.value))));

            // add panel to composite
            add(pair.panel);
        }

    }
}
