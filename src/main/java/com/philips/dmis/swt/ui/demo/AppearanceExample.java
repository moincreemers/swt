package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class AppearanceExample extends Page {
    public AppearanceExample() throws Exception {
        super(Constants.isDemo(AppearanceExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        add(new HtmlHeading("Appearance"));

        Grid grid = add(new Grid(2));
        grid.setAppearance(WidgetAppearance.BORDERED, WidgetAppearance.ROUNDED_CORNERS);

        grid.addAll(new Panel(PanelType.BANNER, new HtmlLabel("Label")).setAppearance(WidgetAppearance.BORDERED),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.BORDERED)"));

        grid.addAll(new Panel(PanelType.INFO, new HtmlLabel("Label")).setAppearance(WidgetAppearance.ROUNDED_CORNERS),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.ROUNDED_CORNERS)"));

        grid.addAll(new Panel(
                        new HtmlTextInput().setAppearance(WidgetAppearance.HARD_CORNERS),
                        new HtmlButton("Button").setAppearance(WidgetAppearance.HARD_CORNERS)
                ),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.HARD_CORNERS)"));

        grid.addAll(new HtmlButton("Large Font").setAppearance(WidgetAppearance.LARGE_FONT),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.LARGE_FONT)"));

        grid.addAll(new HtmlButton("Small Font").setAppearance(WidgetAppearance.SMALL_FONT),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.SMALL_FONT)"));

        grid.addAll(new HtmlLabel("Label").setAppearance(WidgetAppearance.BLOCK, WidgetAppearance.BORDERED),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.BLOCK)"));

        grid.addAll(new Panel(PanelType.INFO, new HtmlLabel("Label")).setAppearance(WidgetAppearance.INLINE_BLOCK),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.INLINE_BLOCK)"));

        grid.addAll(new Panel(PanelType.BANNER, new HtmlLabel("Label")).setAppearance(WidgetAppearance.CENTER),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.CENTER)"));

        grid.addAll(new Panel(PanelType.BANNER, new HtmlLabel("Label")).setAppearance(WidgetAppearance.ALIGN_RIGHT),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.ALIGN_RIGHT)"));

        Grid someGrid = new Grid(2);
        someGrid.setAppearance(WidgetAppearance.FIT_CONTENT, WidgetAppearance.BORDERED);
        someGrid.addAll(new HtmlLabel("Label"), new HtmlLabel("Label"));
        grid.addAll(someGrid,
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.FIT_CONTENT)"));

        grid.addAll(new HtmlLabel("Label").setAppearance(WidgetAppearance.FLOAT_RIGHT, WidgetAppearance.BORDERED),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.FLOAT_RIGHT)"));

        grid.addAll(new Panel(PanelType.INFO, new HtmlLabel("Label").setAppearance(WidgetAppearance.FILL_BACKGROUND)),
                new HtmlPreformatted("Widget.setAppearance(WidgetAppearance.FILL_BACKGROUND)"));
    }
}
