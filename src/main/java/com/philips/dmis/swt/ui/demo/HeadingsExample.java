package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class HeadingsExample extends Page {
    public HeadingsExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Headings"));

        add(new HtmlPreformatted("Heading(String text, int level)"));
        add(new HtmlHeading("Header level 1", 1));
        add(new HtmlHeading("Header level 2", 2));
        add(new HtmlHeading("Header level 3", 3));
        add(new HtmlHeading("Header level 4", 4));
        add(new HtmlHeading("Header level 5", 5));
        add(new HtmlHeading("Header level 6", 6));
    }
}
