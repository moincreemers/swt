package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

@PageXmlElement("bottom")
public class HtmlTableCaption extends Panel {
    private boolean bottom;

    public HtmlTableCaption(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.TABLE_CAPTION);
    }

    public HtmlTableCaption(boolean bottom) {
        super(WidgetType.TABLE_CAPTION);
        setBottom(bottom);
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
        if (bottom) {
            addClassName("table-caption-bottom");
        } else {
            removeClassName("table-caption-bottom");
        }
    }
}
