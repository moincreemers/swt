package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.css.CssFunction;
import com.philips.dmis.swt.ui.toolkit.css.CssLength;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class GridExample extends Page {
    public GridExample() throws Exception {
        super(Constants.isDemo(GridExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        add(new HtmlHeading("Grid"));

        add(new HtmlParagraph("""
                The Grid widget is used for layoutType. Grid uses the CSS display type "grid" to distribute space on a page.
                This widget is useful when you need to control the layoutType of both rows and columns.<br><br>
                                
                Widgets are rendered as a direct child of the grid element depending on the 'layoutType' setting of the child element.
                Using Layout.NO_RESIZE, a child widget is 'encapsulated' by an additional element.<br><br>
                                
                In this example:
                """));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, """
                Grid grid = add(new Grid(3));
                grid.setColumnWidth(0, new CssMinMax(
                    new CssLength(1, CssUnit.REM), new CssLength(300, CssUnit.PIXEL)));
                grid.addAll(new Panel(PanelType.INFO, new HtmlButton("Button")),
                        new HtmlTextInput(),
                        new HtmlTextAreaInput(),
                        new Panel(PanelType.INFO, new HtmlParagraph("Lorem ipsum dolor sit amet...")),
                        new Panel(PanelType.BANNER, new HtmlParagraph("In this example...")),
                        new Panel(PanelType.BANNER)
                        new Panel(PanelType.INFO),
                        new Panel(PanelType.INFO)
                );
                """));

        Grid grid = add(new Grid(3));
        grid.setColumnWidth(0, CssFunction.minMax(CssLength.rem(1), CssLength.px(300)));
        grid.addAll(new Panel(PanelType.INFO, new HtmlButton("Button")),
                new HtmlTextInput(),
                new HtmlTextAreaInput(),
                new Panel(PanelType.INFO, new HtmlParagraph("""
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                        """)),
                new Panel(PanelType.BANNER, new HtmlParagraph("""
                        In this example:
                        <ol>
                        <li>We create a Grid with 3 columns. The Grid constructor allows setting a number for column and/or row. When either number is >0, the Grid will set the corresponding templates</li>
                        <li>The default 'width' of each column is always set to <code>1fr</code>. To change that, we set the column template for the first column to a width of at least <code>1rem</code> and at most <code>300px</code></li>
                        <li>We added only 8 widgets. The result is that the Grid will simply not have a 9th 'cell'</li>
                        <li>As you can see, panels and some input widgets are 'stretched' to match the size of the grid but not all widgets are. This behaviour depends on the layoutType property that all widgets have</li>
                        <li>The gap between the widgets is set to 1rem by default. Use <code>Grid.setColumnGap</code> and <code>Grid.setRowGap</code> to change that.
                        </ol>
                        """)),
                new Panel(PanelType.INFO),
                new Panel(PanelType.INFO),
                new Panel(PanelType.INFO)
        );
    }
}
