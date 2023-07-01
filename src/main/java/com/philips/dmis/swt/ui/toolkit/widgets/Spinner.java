package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class Spinner extends Widget {
    public Spinner() {
        super(WidgetType.SPINNER);
        // default
        setVisible(false);
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        super.renderStaticInnerHtml(toolkit, html);
        html.append("<div class=\"spinner\"/>");
    }
}
