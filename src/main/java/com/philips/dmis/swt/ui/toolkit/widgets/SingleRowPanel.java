package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class SingleRowPanel extends Panel {
    public static final String CSS_CLASS_CELL = "single-row-cell";

    public SingleRowPanel(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.SINGLE_ROW);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    public SingleRowPanel() {
        super(NAMELESS, WidgetType.SINGLE_ROW, PanelType.DEFAULT);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    public SingleRowPanel(Widget... widgets) throws WidgetConfigurationException {
        super(NAMELESS, WidgetType.SINGLE_ROW, PanelType.DEFAULT, widgets);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    public SingleRowPanel(String name, Widget... widgets) throws WidgetConfigurationException {
        super(name, WidgetType.SINGLE_ROW, PanelType.DEFAULT, widgets);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    public SingleRowPanel(String name) {
        super(name, WidgetType.SINGLE_ROW, PanelType.DEFAULT);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    public SingleRowPanel(PanelType panelType) {
        super(NAMELESS, WidgetType.SINGLE_ROW, panelType);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    public SingleRowPanel(PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        super(NAMELESS, WidgetType.SINGLE_ROW, panelType, widgets);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    public SingleRowPanel(String name, PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        super(name, WidgetType.SINGLE_ROW, panelType, widgets);
        setLayoutType(LayoutType.NO_RESIZE);
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        widget.setAppearance(WidgetAppearance.INLINE);
        return super.acceptWidget(widget);
    }
}
