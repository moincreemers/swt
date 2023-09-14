package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlLineBreak extends Widget {
    public HtmlLineBreak(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.BR);
    }

    public HtmlLineBreak() {
        super(WidgetType.BR);
    }
}
