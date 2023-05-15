package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SelectFoodTypesDialog extends Page {
    public SelectFoodTypesDialog() throws Exception {
        super(ViewType.DIALOG, ViewPosition.DIALOG_CENTER);
    }

    @Override
    protected void build() throws Exception {
        StaticData foodOptions = add(new StaticData(Arrays.asList("Apples", "Bananas", "Oranges")));

        Panel headingPanel = add(new Panel(PanelType.INFO));
        headingPanel.add(new HtmlHeading("Select Food Type"));

        Panel bodyPanel = add(new Panel(PanelType.PADDED));
        HtmlSelect fruitType = new HtmlSelect().addDataSource(foodOptions);
        bodyPanel.add(new HtmlLabel(fruitType, "Please select a food type:"));

        bodyPanel.add(new HtmlParagraph("The Ok button below works like this:"));
        bodyPanel.add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "ListBox fruitType = new ListBox().addDataSource(foodOptions);\n" +
                "Button.closePage(ButtonType.PRIMARY, \"Ok\", V.Get(fruitType));"));

        bodyPanel.add(new HtmlParagraph("Select a food type in the listbox and click Ok. " +
                "Then inspect the state of the view controller in the address bar. " +
                "The selected value should appear as an argument."));

        Panel footerPanel = add(new Panel(PanelType.PADDED));
        footerPanel.add(HtmlButton.closePage(ButtonType.PRIMARY, "Ok", V.GetValue(fruitType)));
        footerPanel.add(HtmlButton.closePage("Cancel", V.Cancel));
    }
}
