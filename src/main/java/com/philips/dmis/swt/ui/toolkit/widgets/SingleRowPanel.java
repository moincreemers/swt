package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class SingleRowPanel extends Panel {
    public static final String CSS_CLASS_CELL = "single-row-cell";

    public SingleRowPanel() {
        super("", WidgetType.SINGLE_ROW, PanelType.DEFAULT);
    }

    public SingleRowPanel(Widget... widgets) throws WidgetConfigurationException {
        super("", WidgetType.SINGLE_ROW, PanelType.DEFAULT, widgets);
    }

    public SingleRowPanel(String name, Widget... widgets) throws WidgetConfigurationException {
        super(name, WidgetType.SINGLE_ROW, PanelType.DEFAULT, widgets);
    }

    public SingleRowPanel(String name) {
        super(name, WidgetType.SINGLE_ROW, PanelType.DEFAULT);
    }

    public SingleRowPanel(PanelType panelType) {
        super("", WidgetType.SINGLE_ROW, panelType);
    }

    public SingleRowPanel(PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        super("", WidgetType.SINGLE_ROW, panelType, widgets);
    }

    public SingleRowPanel(String name, PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        super(name, WidgetType.SINGLE_ROW, panelType, widgets);
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        widget.setAppearance(WidgetAppearance.INLINE);
        return super.acceptWidget(widget);
    }
}
