package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class OverlayPanel extends Panel {
    public OverlayPanel() {
        super("", WidgetType.OVERLAY, PanelType.DEFAULT);
    }

    public OverlayPanel(Widget... widgets) throws WidgetConfigurationException {
        super("", WidgetType.OVERLAY, PanelType.DEFAULT, widgets);
    }

    public OverlayPanel(String name, Widget... widgets) throws WidgetConfigurationException {
        super(name, WidgetType.OVERLAY, PanelType.DEFAULT, widgets);
    }

    public OverlayPanel(String name) {
        super(name, WidgetType.SINGLE_ROW, PanelType.DEFAULT);
    }

    public OverlayPanel(PanelType panelType) {
        super("", WidgetType.OVERLAY, panelType);
    }

    public OverlayPanel(PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        super("", WidgetType.OVERLAY, panelType, widgets);
    }

    public OverlayPanel(String name, PanelType panelType, Widget... widgets) throws WidgetConfigurationException {
        super(name, WidgetType.OVERLAY, panelType, widgets);
    }
}
