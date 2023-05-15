package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class AppearanceExample extends Page {
    public AppearanceExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Appearance"));

        Grid grid = add(new Grid(2));
        grid.setAppearance(WidgetAppearance.ROUNDED_CORNERS);

        grid.addAll(new Panel(PanelType.INFO).setAppearance(WidgetAppearance.ROUNDED_CORNERS),
                new HtmlPreformatted("panel.setAppearance(WidgetAppearance.ROUNDED_CORNERS)"));

        grid.addAll(new Panel(PanelType.BANNER).setAppearance(WidgetAppearance.BORDERED),
                new HtmlPreformatted("panel.setAppearance(WidgetAppearance.BORDERED)"));

        grid.addAll(new Panel(PanelType.WARNING).setAppearance(WidgetAppearance.ROUNDED_CORNERS, WidgetAppearance.BORDERED),
                new HtmlPreformatted("panel.setAppearance(WidgetAppearance.ROUNDED_CORNERS, WidgetAppearance.BORDERED)"));

        grid.addAll(new HtmlButton("Click Me").setAppearance(WidgetAppearance.HARD_CORNERS, WidgetAppearance.LARGE_FONT),
                new HtmlPreformatted("button.setAppearance(WidgetAppearance.HARD_CORNERS, WidgetAppearance.LARGE_FONT)"));

        grid.addAll(new HtmlButton("Click Me").setAppearance(WidgetAppearance.SMALL_FONT),
                new HtmlPreformatted("button.setAppearance(WidgetAppearance.SMALL_FONT)"));

    }
}
