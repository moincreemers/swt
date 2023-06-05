package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.events.ColorSchemeChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class OsLightAndDarkThemeExample extends Page {
    public OsLightAndDarkThemeExample() throws Exception {
        super(Constants.isDemo(OsLightAndDarkThemeExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        onColorSchemeChange(ColorSchemeChangeEventHandler.getDefaultHandler());

        add(new HtmlHeading("OS light and dark color scheme"));

        add(new HtmlParagraph("Most operating systems support a light or dark color scheme. " +
                "The toolkit provides a global event to respond to this. Add the following line to any page:"));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "onColorSchemeChange(ColorSchemeChangeEventHandler.getDefaultHandler());"));

        add(new HtmlParagraph("The event handler has been added to this page. Try changing the color scheme."));

        add(new HtmlParagraph("On macOS: Open the General Settings and select the light or dark theme."));

        add(new HtmlParagraph("On Windows: Open the Settings, Choose Personalization, Colors. " +
                "In the list for Choose your mode, select light or dark."));
    }
}
