package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class FormInsideFrameExample extends Page {
    public FormInsideFrameExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(new HtmlHeading("Example Form", 3));

        Grid grid = add(new Grid(2));

        grid.addAll(new HtmlLabel(new HtmlTextInput(), "Street address"));
        grid.addAll(new HtmlLabel(new HtmlTextInput(), "Postal code"));
        grid.addAll(new HtmlLabel(new HtmlTextInput(), "City"));
        grid.addAll(new HtmlLabel(new HtmlSelect(), "Country"));

        add(new Panel(PanelType.PADDED, new HtmlButton("Save")));
    }
}
