package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlSection extends ContainerWidget<HtmlSection> {
    public HtmlSection(Widget... widgets) throws WidgetConfigurationException {
        this("", widgets);
    }

    public HtmlSection(String name, Widget... widgets) throws WidgetConfigurationException {
        super(WidgetType.SECTION);
        addAll(widgets);
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return true;
    }
}
