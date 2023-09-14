package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlHorizontalRule extends Widget {
    public HtmlHorizontalRule(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.HR);
    }

    public HtmlHorizontalRule() {
        super(WidgetType.HR);
    }
}
