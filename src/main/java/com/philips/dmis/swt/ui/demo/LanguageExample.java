package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class LanguageExample extends Page {
    public LanguageExample() throws Exception {
        super(Constants.isDemo(LanguageExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        add(new HtmlHeading("Language support"));

        add(new HtmlParagraph("The toolkit supports multiple languages."));

        add(new HtmlParagraph("""
                <ul>
                    <li>Only ISO language codes are supported, e.g., "en". There is currently no support for differentiating between "en-GB" and "en-US" for example.</li>
                    <li>The toolkit is set to a default language. This is always english ("en").</li>
                    <li>When constants are declared, for example setting text of an HtmlParagraph, the constant is assumed to have the default language.</li>
                    <li>The value of the constant in the default language is the key to that constant.</li>
                    <li>When the application code is generated and the DEBUG flag is set, the toolkit will output all constants in the default language. This can be copied and pasted into a resource file. Each constant token is prefixed with the language. This needs to be replaced with a new language and then the value translated into that language.</li>
                    <li>Finally, the toolkit provides a method to add a language file. This needs to be a simple Java properties file.</li>
                    <li>Depending on the language that the user has selected in the browser, the constant is looked up in that language if available. If not, the default language is used.</li>
                    <li>It is technically allowed to declare a constant in a language file for the default language ("en") which overrides any constant set in the code. However, this is not the intended use.</li>
                    <li>The application needs to be reloaded for a language change to take effect.</li>
                    <li>When the constant value changes in the default language, e.g., a button used to have the text "Ok" and it is changed into "Save" then be aware that the token will change and therefore, the token of all translations must be updated accordingly.</li>
                </ul>
                """));
    }
}
