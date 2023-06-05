package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class IconsExample extends Page {
    public IconsExample() throws Exception {
        super(Constants.isDemo(IconsExample.class));
    }

    @Override
    protected void build() throws Exception {
        if(!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Icons and Text"));

        IconsWidget icons = add(new IconsWidget("MaterialSymbolsSharp.woff2"));

        add(new HtmlHeading("Widgets that support Icons", 2));
        Grid grid = add(new Grid(3));
        grid.setAppearance(WidgetAppearance.BORDERED);
        grid.addAll(new HtmlParagraph("Heading"), new HtmlHeading(icons, "folder", "Folder"), new HtmlParagraph("Icons scale with the font."));
        grid.addAll(new HtmlParagraph("Button"), new Panel(new HtmlButton(icons, "search", "Search"), new HtmlButton(icons, "edit")), new HtmlParagraph("Buttons with an Icon and Text."));
        grid.addAll(new HtmlParagraph("Link"), new HtmlLink(icons, "home", "Home"), new HtmlParagraph("Link with an icon."));
        grid.addAll(new HtmlParagraph("Label"), new Panel(new HtmlLabel(new HtmlTextInput(), icons, "unknown_document", "Textbox1")), new HtmlParagraph("A Label with an Icon."));

        add(new HtmlHeading("Changing the Icon or Text", 2));
        Grid grid2 = add(new Grid(3));
        grid2.setAppearance(WidgetAppearance.BORDERED);
        HtmlButton htmlButtonIconText = new HtmlButton(icons, "search", "Search");
        grid2.addAll(htmlButtonIconText, new Panel(
                new HtmlButton("Change icon").onClick(new ClickEventHandler(M.SetIcon(htmlButtonIconText, V.Const("edit")))),
                new HtmlButton("Change text").onClick(new ClickEventHandler(M.SetText(htmlButtonIconText, V.Const("Edit"))))
        ), new HtmlParagraph("The Icon and Text can be changed independently using M.SetIcon and M.Set respectively."));

    }
}
