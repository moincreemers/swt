package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.widgets.HtmlButton;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class SingleChildPage extends Page {
    public SingleChildPage() throws Exception {
        super(true);
    }

    @Override
    protected void build() throws WidgetConfigurationException {
        HtmlButton htmlButton = add(new HtmlButton("Button1"));
    }
}
