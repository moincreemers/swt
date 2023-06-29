package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class SmartGridExample extends Page {
    public SmartGridExample() throws Exception {
        super(Constants.isDemo(SmartGridExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        add(new HtmlHeading("Smart Grid Example"));

        SmartGrid grid = add(new SmartGrid(4));

    }
}
