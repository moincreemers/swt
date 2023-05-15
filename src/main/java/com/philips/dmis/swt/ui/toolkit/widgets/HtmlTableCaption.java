package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlTableCaption extends Panel {
    public HtmlTableCaption(boolean bottom) {
        super(WidgetType.TABLE_CAPTION);
        if (bottom) {
            addClassName("table-caption-bottom");
        }
    }
}
