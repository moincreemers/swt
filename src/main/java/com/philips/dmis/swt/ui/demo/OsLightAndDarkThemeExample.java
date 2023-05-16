package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.widgets.HtmlHeading;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlLink;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlParagraph;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;
import org.springframework.stereotype.Component;

@Component
public class OsLightAndDarkThemeExample extends Page {
    public OsLightAndDarkThemeExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("OS light and dark color scheme"));

        add(new HtmlParagraph("Most operating systems support a light or dark color scheme. " +
                "The toolkit automatically responds to this. Try changing the color scheme."));

        add(new HtmlParagraph("On macOS: Open the General Settings and select the light or dark theme."));

        add(new HtmlParagraph("On Windows: Open the Settings, Choose Personalization, Colors. " +
                "In the list for Choose your mode, select light or dark."));
    }
}
