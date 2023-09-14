package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.utils.PageBuilder;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlLink;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import org.springframework.stereotype.Component;

@Component
public class AppearanceExample extends Page {
    public AppearanceExample() throws Exception {
        super(Constants.isDemo(AppearanceExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        PageBuilder.getInstance().loadFromXml(this);
    }
}
