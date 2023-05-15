package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.data.PathDataAdapter;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class TextExample extends Page {
    public TextExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Text-Rich Pages"));

        add(new HtmlParagraph("HTML content in paragraphs is correctly displayed and special characters (beyond \\u0255) are correctly transferred:"));

        StaticData staticData = add(new StaticData("The Netherlands (<a href='https://en.wikipedia.org/wiki/Dutch_language'>Dutch</a>: Nederland [ˈneːdərlɑnt]), " +
                "informally Holland, is a country located in <a href='https://en.wikipedia.org/wiki/Northwestern_Europe'>northwestern Europe</a> with overseas " +
                "territories in the <a href='https://en.wikipedia.org/wiki/Caribbean'>Caribbean</a>. It is the largest of four constituent countries of the " +
                "<a href='https://en.wikipedia.org/wiki/Kingdom_of_the_Netherlands'>Kingdom of the Netherlands</a>. The Netherlands consists of <a href='https://en.wikipedia.org/wiki/Provinces_of_the_Netherlands'>twelve provinces</a>; it borders " +
                "<a href='https://en.wikipedia.org/wiki/Germany'>Germany</a> to the east, and Belgium to the south, with a North Sea coastline to the north and west. " +
                "It shares maritime borders with the United Kingdom, Germany and Belgium in the North Sea. " +
                "The country's official language is Dutch, with West Frisian as a secondary official language " +
                "in the province of Friesland. Dutch, English and Papiamento are official in the Caribbean " +
                "territories."));

        StaticData dangerousStaticData = add(new StaticData("<script>alert('this message should never be displayed');</script>"));

        Panel panel1 = add(new Panel(PanelType.PADDED));
        HtmlParagraph p1 = panel1.add(new HtmlParagraph());
        p1.addDataSource(staticData, new PathDataAdapter());
        p1.setGenerateLinks(true);
        panel1.add(new Caption("Source: Wikipedia"));

        add(new HtmlParagraph("Note that HTML is sanitized. If the data contains dangerous content, like:"));
        add(new HtmlPreformatted("&ltscript&gt;alert('this message should never be displayed');&lt;/script&gt;"));
        add(new HtmlParagraph("then the content is displayed as:"));

        Panel panel2 = add(new Panel(PanelType.PADDED));
        HtmlParagraph p2 = panel2.add(new HtmlParagraph());
        p2.addDataSource(dangerousStaticData, new PathDataAdapter());
        p2.setGenerateLinks(true);
    }
}
