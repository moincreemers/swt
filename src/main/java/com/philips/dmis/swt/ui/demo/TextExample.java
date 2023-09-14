package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.data.ValueDataAdapter;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class TextExample extends Page {
    public TextExample() throws Exception {
        super(Constants.isDemo(TextExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Text-Rich Pages"));

        add(new HtmlParagraph("HTML content added to a text widget (such as an HtmlParagraph) through data binding is correctly displayed. Special characters (beyond \\u0255) are also correctly transferred:"));

        StaticData staticData = add(new StaticData(
                """
                        <section style="color:orange;font-family:serif">
                            The Netherlands (<a href="https://en.wikipedia.org/wiki/Dutch_language">Dutch</a>: Nederland [ˈneːdərlɑnt]),
                            <a href="https://en.wikipedia.org/wiki/Terminology_of_the_Low_Countries">informally</a> <b>Holland</b>,
                            is a country located in <a href="https://en.wikipedia.org/wiki/Northwestern_Europe">northwestern Europe</a> with overseas
                            territories in the <a href="https://en.wikipedia.org/wiki/Caribbean">Caribbean</a>.
                            It is the largest of four constituent countries of the
                            <a href="https://en.wikipedia.org/wiki/Kingdom_of_the_Netherlands">Kingdom of the Netherlands</a>.
                            The Netherlands consists of <a href="https://en.wikipedia.org/wiki/Provinces_of_the_Netherlands">twelve provinces</a>; it borders
                            <a href="https://en.wikipedia.org/wiki/Germany">Germany</a> to the east, and <a href="https://en.wikipedia.org/wiki/Belgium">Belgium</a> to the south, with a
                            <a href="https://en.wikipedia.org/wiki/North_Sea">North Sea</> coastline to the north and west.
                            It shares <a href="https://en.wikipedia.org/wiki/Maritime_boundary">maritime borders</a> with the <a href="https://en.wikipedia.org/wiki/United_Kingdom">United Kingdom</a>,
                            Germany and Belgium in the North Sea.
                            The country's official language is Dutch, with <a href="https://en.wikipedia.org/wiki/West_Frisian_language">West Frisian</a> as a secondary official language
                            in the province of <a href="https://en.wikipedia.org/wiki/Friesland">Friesland</a>.
                            Dutch, English and <a href="https://en.wikipedia.org/wiki/Papiamento">Papiamento</a> are official in the Caribbean
                            "territories.
                        </section>"""));

        StaticData dangerousStaticData = add(new StaticData("Hello <script>alert('this message should never be displayed');</script>World!"));

        HtmlParagraph p1 = add(new HtmlParagraph());
        p1.addDataSource(staticData, new ValueDataAdapter());
        add(new Caption("Source: Wikipedia"));

        add(new HtmlHeading("Dangerous content", 2));

        add(new HtmlParagraph("Note that HTML is sanitized to protect the user against XSS attacks. If the data contains potentially dangerous content, like:"));
        add(new HtmlPreformatted("Hello &ltscript&gt;alert('this message should never be displayed');&lt;/script&gt;World!"));
        add(new HtmlParagraph("then the content is displayed as:"));

        HtmlParagraph p2 = add(new HtmlParagraph());
        p2.addDataSource(dangerousStaticData, new ValueDataAdapter());
    }
}
