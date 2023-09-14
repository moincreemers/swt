package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlSection extends ContainerWidget<HtmlSection> {
    public HtmlSection(WidgetConfigurator widgetConfigurator) throws WidgetConfigurationException {
        super(widgetConfigurator, WidgetType.SECTION);
    }

    public HtmlSection(Widget... widgets) throws WidgetConfigurationException {
        this(NAMELESS, widgets);
    }

    public HtmlSection(String name, Widget... widgets) throws WidgetConfigurationException {
        super(name, WidgetType.SECTION);
        addAll(widgets);
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return true;
    }
}
