package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.widgets.HtmlHeading;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlLink;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import org.springframework.stereotype.Component;

@Component
public class EventsExample extends Page {
    public EventsExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Events"));


    }
}
