package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class SelectFoodTypesDialog extends Page {
    public SelectFoodTypesDialog() throws Exception {
        super(ViewType.DIALOG, ViewPosition.DIALOG_CENTER);
    }

    @Override
    protected void build() throws Exception {
        StaticData foodOptions = add(new StaticData(
                DataBuilder.values("Apples", "Bananas", "Oranges").getData()
        ));

        Panel headingPanel = add(new Panel(PanelType.INFO));
        headingPanel.add(new HtmlHeading("Select Food Type"));

        Panel bodyPanel = add(new Panel(PanelType.PADDED));
        HtmlSelect fruitType = new HtmlSelect().addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, foodOptions, new KeyValueListDataAdapter());
        bodyPanel.add(new HtmlLabel(fruitType, "Please select a food type:"));

        bodyPanel.add(new HtmlParagraph("The Ok button below works like this:"));
        bodyPanel.add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                "ListBox fruitType = new ListBox().addDataSource(foodOptions, new ArrayDataAdapter());\n" +
                        "Button.closePage(ButtonType.PRIMARY, \"Ok\", V.Get(fruitType));"));

        bodyPanel.add(new HtmlParagraph("Select a food type in the listbox and click Ok. " +
                "Then inspect the state of the view controller in the address bar. " +
                "The value passed to M.ClosePage (the fruit type) is stored in sessionStorage. " +
                "The key to that item is the \"d\" argument in the address bar."));

        Panel footerPanel = add(new Panel(PanelType.PADDED));
        footerPanel.add(HtmlButton.closePage(ButtonType.PRIMARY, "Ok", V.GetValue(fruitType)));
        footerPanel.add(HtmlButton.closePage("Cancel", V.Cancel));
    }
}
