package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class PagesExamplePage extends Page {
    public PagesExamplePage() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));

        add(new HtmlHeading("Pages"));

        add(new HtmlParagraph("Almost all applications are composed of one or more Views. A \"View\" has a specific " +
                "role in the traditional " +
                "<a href=\"https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller\">Model-View-Controller</a> " +
                "pattern. How this pattern is implemented exactly is different between platforms and environments but " +
                "generally speaking, a View is the component that is responsible for the interaction with users."));

        add(new HtmlParagraph("Singular also uses this pattern and all views are called \"Pages\"."));

        add(new HtmlParagraph("A Page can be easily created by creating a Java class and letting it extend the " +
                "Page class."));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "public class MyHomePage extends Page {\n" +
                "  protected void build() {\n" +
                "    ...\n" +
                "  }"));

    }
}
