package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class NumberedHeadingsExample extends Page {
    public NumberedHeadingsExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Numbered Headings"));

        add(new HtmlParagraph("A feature that is missing from HTML is numbered headings. When the text needs to break to the next line, it will left-align correctly. Use the constructor to enable numbering. " +
                "Note that the numbering sequence is scoped to the page."));
        add(new HtmlPreformatted("Heading(String text, int level, boolean numbered)"));
        add(new HtmlHeading("Heading 1", 1, true));
        add(new HtmlHeading("Heading 2", 2, true));
        add(new HtmlHeading("Heading 2", 2, true));
        add(new HtmlHeading("Heading 2", 2, true));
        add(new HtmlHeading("Heading 3", 3, true));
        add(new HtmlHeading("Heading 3", 3, true));
        add(new HtmlHeading("Heading 3", 3, true));
        add(new HtmlHeading("Heading 4", 4, true));
        add(new HtmlHeading("Heading 4", 4, true));
        add(new HtmlHeading("Heading 4", 4, true));
        add(new HtmlHeading("Heading 5", 5, true));
        add(new HtmlHeading("Heading 5", 5, true));
        add(new HtmlHeading("Heading 5", 5, true));
        add(new HtmlHeading("Heading 6", 6, true));
        add(new HtmlHeading("Heading 6", 6, true));
        add(new HtmlHeading("Heading 6", 6, true));
        add(new HtmlHeading("Heading 2", 2, true));
        add(new HtmlHeading("Heading 1", 1, true));
        add(new HtmlHeading("Heading 1", 1, true));
    }
}
