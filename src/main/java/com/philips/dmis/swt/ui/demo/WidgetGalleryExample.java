package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ColorSchemeChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InitEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class WidgetGalleryExample extends Page {
    private static final String[] LARGE_FRUITS = new String[]{
            "Abiu", "Açaí", "Acerola", "Akebi", "Ackee",
            "African Cherry Orange", "American Mayapple",
            "Apple", "Apricot", "Aratiles", "Araza",
            "Atis (Annona Squamosa)", "Avocado", "Banana",
            "Bilberry", "Blackberry", "Blackcurrant",
            "Black sapote", "Blueberry", "Boysenberry",
            "Breadfruit", "Buddha's hand (fingered citron)",
            "Cacao", "Cactus pear", "Canistel (egg fruit)",
            "Catmon", "Cempedak", "Cherimoya (Custard Apple)",
            "Cherry", "Chico fruit", "Cloudberry", "Coco de mer",
            "Coconut", "Crab apple", "Cranberry", "Currant",
            "Damson", "Date", "Dragonfruit (or Pitaya)",
            "Durian", "Elderberry", "Feijoa", "Fig",
            "Finger Lime (or Caviar Lime)", "Goji berry",
            "Gooseberry", "Grape", "Raisin", "Grapefruit",
            "Grewia asiatica (phalsa or falsa)", "Guava",
            "Guyabano", "Hala Fruit",
            "Honeyberry", "Huckleberry", "Jabuticaba (Plinia)",
            "Jackfruit", "Jambul", "Japanese plum", "Jostaberry",
            "Jujube", "Juniper berry", "Kaffir Lime",
            "Kiwano (horned melon)", "Kiwifruit", "Kumquat",
            "Lanzones", "Lemon", "Lime", "Loganberry", "Longan",
            "Loquat", "Lulo", "Lychee", "Magellan Barberry",
            "Macopa (Wax Apple)", "Mamey Apple", "Mamey Sapote",
            "Mango", "Mangosteen", "Marionberry", "Melon",
            "Cantaloupe", "Galia melon", "Honeydew", "Mouse melon",
            "Musk melon", "Watermelon", "Miracle fruit",
            "Momordica fruit", "Monstera deliciosa", "Mulberry",
            "Nance", "Nectarine", "Orange", "Blood orange",
            "Clementine", "Mandarine", "Tangerine", "Papaya",
            "Passionfruit", "Pawpaw", "Peach", "Pear", "Persimmon",
            "Plantain", "Plum", "Prune (dried plum)", "Pineapple",
            "Pineberry", "Plumcot (or Pluot)", "Pomegranate",
            "Pomelo", "Purple mangosteen", "Quince", "Raspberry",
            "Salmonberry", "Rambutan (or Mamin Chino)", "Redcurrant",
            "Rose apple", "Salal berry", "Salak",
            "Santol (Cotton Fruit)", "Sampaloc", "Sapodilla",
            "Sapote", "Sarguelas (Red Mombin)", "Satsuma", "Soursop",
            "Star apple", "Star fruit", "Strawberry", "Surinam cherry",
            "Tamarillo", "Tamarind", "Tangelo", "Tayberry",
            "Tambis (Water Apple)", "Ugli fruit", "White currant",
            "White sapote", "Ximenia", "Yuzu"
    };


    public WidgetGalleryExample() throws Exception {
        super(Constants.isDemo(WidgetGalleryExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        onColorSchemeChange(ColorSchemeChangeEventHandler.getDefaultHandler());

        add(new HtmlHeading("Widgets"));

        StaticData options = add(new StaticData(DataBuilder.values("Apple", "Banana", "Orange").getData()));
        IconsWidget icons = add(new IconsWidget("MaterialSymbolsSharp.woff2"));

        add(new HtmlHeading("Text Widgets", 3));
        Grid textWidgetsGrid = add(new Grid(3));
        textWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);

        textWidgetsGrid.addAll(new HtmlLabel("Example"), new HtmlLabel("HTML Equivalent"), new HtmlLabel("Description"));
        textWidgetsGrid.addAll(new Panel(new HtmlHeading("Heading 1", 1), new HtmlHeading("Heading 2", 2), new HtmlHeading("Heading 3", 3), new HtmlHeading("Heading 4", 4), new HtmlHeading("Heading 5", 5), new HtmlHeading("Heading 6", 6)), new HtmlParagraph("H1, H2, H3, H4, H5, H6"), new HtmlParagraph(HtmlHeading.class.getSimpleName() + " widget results in the HTML H{1-6} elements."));
        textWidgetsGrid.addAll(new Panel(
                new HtmlHeading("Numbered heading 1", 1, true),
                new HtmlHeading("Numbered heading 2", 2, true),
                new HtmlHeading("Numbered heading 3", 3, true),
                new HtmlHeading("Numbered heading 4", 4, true),
                new HtmlHeading("Numbered heading 5", 5, true),
                new HtmlHeading("Numbered heading 6", 6, true)
        ), new HtmlParagraph(""), new HtmlParagraph("Numbered headings are often used for (technical) documentation. Note that numbering is scoped to a page."));
        textWidgetsGrid.addAll(new HtmlParagraph(HtmlParagraph.class.getSimpleName()), new HtmlParagraph("P"), new HtmlParagraph(HtmlParagraph.class.getSimpleName() + " is the only widget that allows HTML content. Note that HTML is sanitized which means that potentially dangerous elements are removed automatically."));
        textWidgetsGrid.addAll(new HtmlHorizontalRule(), new HtmlParagraph("HR"), new HtmlParagraph(HtmlHorizontalRule.class.getSimpleName() + " is a horizontal rule that is typically used to indicate a division on a page."));
        textWidgetsGrid.addAll(new HtmlLineBreak(), new HtmlParagraph("BR"), new HtmlParagraph(HtmlLineBreak.class.getSimpleName() + " is a simple line break"));
        textWidgetsGrid.addAll(new HtmlPreformatted("Preformatted"), new HtmlParagraph("PRE"), new HtmlParagraph(HtmlPreformatted.class.getSimpleName() + " is meant for things like code. Wraps text at spaces. You can use TextFormat.JSON to display JSON documents. The toolkit will then format the documents for you."));
        textWidgetsGrid.addAll(new ListContainer(new HtmlParagraph("Item 1"), new HtmlParagraph("Item 2"), new HtmlParagraph("Item 3")), new HtmlParagraph("UL"), new HtmlParagraph(ListContainer.class.getSimpleName() + " is a container widget that displays items as an unordered list by default."));
        textWidgetsGrid.addAll(new ListContainer(ListType.ORDERED, new HtmlParagraph("Item 1"), new HtmlParagraph("Item 2"), new HtmlParagraph("Item 3")), new HtmlParagraph("OL"), new HtmlParagraph("ListType.ORDERED"));
        textWidgetsGrid.addAll(new ListContainer(ListType.MENU, new HtmlLink("Item 1"), new HtmlLink("Item 2"), new HtmlLink("Item 3"), new HtmlButton("Item 4")), new HtmlParagraph("UL"), new HtmlParagraph("ListType.MENU"));
        textWidgetsGrid.addAll(new ListContainer(ListType.INLINE_MENU, new HtmlLink("Item 1"), new HtmlLink("Item 2"), new HtmlLink("Item 3"), new HtmlButton("Item 4")), new HtmlParagraph("UL"), new HtmlParagraph("ListType.INLINE_MENU"));


        add(new HtmlHeading("Input Widgets", 3));
        Grid inputWidgetsGrid = add(new Grid(3));
        inputWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);
        inputWidgetsGrid.addAll(new Panel(PanelType.BANNER, new HtmlLabel(new HtmlTextInput(), HtmlLabel.class.getSimpleName())), new HtmlParagraph("LABEL"), new HtmlParagraph(HtmlLabel.class.getSimpleName() + " widgets are typically used in combination with an input field. But they can also be used without. Labels also support icons."));
        inputWidgetsGrid.addAll(new HtmlLink(HtmlLink.class.getSimpleName()), new HtmlParagraph("A"), new HtmlParagraph("Links are functionally equivalent to buttons. Links support icons."));
        inputWidgetsGrid.addAll(new HtmlButton(HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.DEFAULT."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.PRIMARY, HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.PRIMARY."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.SUCCESS, HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.SUCCESS."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.INFO, HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.INFO."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.WARNING, HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.WARNING."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.ERROR, HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.ERROR."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.DEFAULT, icons, "search", HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.DEFAULT + icon."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.ERROR, icons, "error", HtmlButton.class.getSimpleName()), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.ERROR + icon"));
        inputWidgetsGrid.addAll(new HtmlImageButton("example.jpg").setImageSize(ImageSize.customSize("150px", "")).setAppearance(WidgetAppearance.ROUNDED_CORNERS), new HtmlParagraph("INPUT type=image"), new HtmlParagraph(HtmlImageButton.class.getSimpleName() + " is an image that can be used instead of a button to submit a form or just used to display an image. The widget supports data binding so this can be used if the datasource contains an URL for example."));
        inputWidgetsGrid.addAll(new HtmlCheckInput(), new HtmlParagraph("INPUT type=checkbox"), new HtmlParagraph(HtmlCheckInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlTextInput(HtmlTextInput.class.getSimpleName()), new HtmlParagraph("INPUT, type=text"), new HtmlParagraph(HtmlTextInput.class.getSimpleName() + " is a simple text input field."));
        inputWidgetsGrid.addAll(new HtmlPasswordInput(), new HtmlParagraph("INPUT, type=password"), new HtmlParagraph(HtmlPasswordInput.class.getSimpleName() + " is a simple password input field."));
        inputWidgetsGrid.addAll(new HtmlDateInput(), new HtmlParagraph("INPUT, type=date"), new HtmlParagraph(HtmlDateInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlTimeInput(), new HtmlParagraph("INPUT, type=time"), new HtmlParagraph(HtmlTimeInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlDateTimeInput(), new HtmlParagraph("INPUT, type=datetime-local"), new HtmlParagraph(HtmlDateTimeInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlMonthInput(), new HtmlParagraph("INPUT, type=month"), new HtmlParagraph(HtmlMonthInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlWeekInput(), new HtmlParagraph("INPUT, type=week"), new HtmlParagraph(HtmlWeekInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlColor(), new HtmlParagraph("INPUT, type=color"), new HtmlParagraph(HtmlColor.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlEmailInput(), new HtmlParagraph("INPUT, type=email"), new HtmlParagraph(HtmlEmailInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlTelephoneInput(), new HtmlParagraph("INPUT, type=tel"), new HtmlParagraph(HtmlTelephoneInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlURLInput(), new HtmlParagraph("INPUT, type=url"), new HtmlParagraph(HtmlURLInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlSearchInput(), new HtmlParagraph("INPUT, type=search"), new HtmlParagraph(HtmlSearchInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlNumberInput(), new HtmlParagraph("INPUT, type=number"), new HtmlParagraph(HtmlNumberInput.class.getSimpleName()));

        HtmlFile htmlFileInput = new HtmlFile();
        HtmlButton fileSelect = new HtmlButton("Browse...");
        fileSelect.onClick(new ClickEventHandler(
                M.Click(htmlFileInput)
        ));
        HtmlTextInput filesSelected = new HtmlTextInput();
        htmlFileInput.onChange(new ChangeEventHandler(
                M.SetValue(filesSelected, V.GetValue(htmlFileInput))
        ));

        inputWidgetsGrid.addAll(new Panel(htmlFileInput, fileSelect, filesSelected), new HtmlParagraph("INPUT, type=file"), new HtmlParagraph(HtmlFile.class.getSimpleName() + ". The file upload widget itself is invisible. Use M.Click to open the file dialog."));
        inputWidgetsGrid.addAll(new HtmlRangeInput(), new HtmlParagraph("INPUT, type=range"), new HtmlParagraph(HtmlRangeInput.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlMeter(), new HtmlParagraph("METER"), new HtmlParagraph(HtmlMeter.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlProgress(), new HtmlParagraph("PROGRESS"), new HtmlParagraph(HtmlProgress.class.getSimpleName()));
        inputWidgetsGrid.addAll(new HtmlSelect().addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()), new HtmlParagraph("SELECT"), new HtmlParagraph(HtmlSelect.class.getSimpleName() + " is a standard drop down list."));
        inputWidgetsGrid.addAll(new HtmlSelect(3).addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()), new HtmlParagraph("SELECT, size=3"), new HtmlParagraph(HtmlSelect.class.getSimpleName() + " with size 3. Note that the " + SingleChoice.class.getSimpleName() + " widget probably provides a better User Experience."));
        inputWidgetsGrid.addAll(new HtmlSelect(3, true).addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()), new HtmlParagraph("SELECT, size=3, muliple"), new HtmlParagraph(HtmlSelect.class.getSimpleName() + " with size 3 that allows multiple values to be selected. Note that the " + MultipleChoice.class.getSimpleName() + " widget probably provides a better User Experience."));
        inputWidgetsGrid.addAll(new SingleChoice().addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph(SingleChoice.class.getSimpleName() + " is a widget that allows the user to select one option. Uses Radio buttons and Labels."));
        inputWidgetsGrid.addAll(new SingleChoice(SingleChoiceAppearance.INLINE).addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph("SingleChoiceAppearance.INLINE."));
        inputWidgetsGrid.addAll(new Panel(new SingleChoice(SingleChoiceAppearance.TABS).addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()).setValue("Apple")), new HtmlParagraph(""), new HtmlParagraph("SingleChoiceAppearance.TABS."));
        inputWidgetsGrid.addAll(new MultipleChoice().addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph(MultipleChoice.class.getSimpleName() + " is a widget that allows the user to select zero or more options. Uses Check boxes and Labels."));
        inputWidgetsGrid.addAll(new MultipleChoice(MultipleChoiceAppearance.INLINE).addDataSource(ValueAndItemsDataSourceUsage.ITEMS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph("MultipleChoiceAppearance.INLINE."));

        add(new HtmlHeading("Grid and Table Widgets", 3));
        Grid tableWidgetsGrid = add(new Grid(3));
        tableWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);

        SingleRowPanel singleRowPanel = tableWidgetsGrid.add(new SingleRowPanel(PanelType.TOOLBAR));
        singleRowPanel.addAll(new HtmlButton(icons, "undo"), new HtmlButton(icons, "redo"), new HtmlButton(icons, "save"));
        tableWidgetsGrid.addAll(singleRowPanel, new HtmlParagraph("TABLE or CSS-table"), new HtmlParagraph(SingleRowPanel.class.getSimpleName() + " is a Panel that displays widgets horizontally without breaking to the next line. In this example the PanelType.TOOLBAR is used."));

        Grid gridExample = new Grid(2);
        gridExample.setAppearance(WidgetAppearance.BORDERED);
        gridExample.addAll(new HtmlLabel("Selection"), new HtmlLabel("Description"));
        gridExample.addAll(new HtmlCheckInput(), new HtmlParagraph("Some text 1"));
        gridExample.addAll(new HtmlCheckInput(), new HtmlParagraph("Some text 2"));
        gridExample.addAll(new HtmlCheckInput(), new HtmlParagraph("Some text 3"));
        tableWidgetsGrid.addAll(gridExample, new HtmlParagraph("TABLE or CSS-table"), new HtmlParagraph(Grid.class.getSimpleName() + " is a container widget. Implemented as a CSS-Table."));

        tableWidgetsGrid.addAll(new HtmlPreformatted(HtmlTable.class.getSimpleName()), new HtmlParagraph("TABLE"), new HtmlParagraph(""));
        tableWidgetsGrid.addAll(new HtmlPreformatted(HtmlTableHeader.class.getSimpleName()), new HtmlParagraph("THEAD"), new HtmlParagraph(""));
        tableWidgetsGrid.addAll(new HtmlPreformatted(HtmlTableBody.class.getSimpleName()), new HtmlParagraph("TBODY"), new HtmlParagraph("Any number of TableBody widgets may be added to a Table widget. Supports data-binding."));
        tableWidgetsGrid.addAll(new HtmlPreformatted(HtmlTableFooter.class.getSimpleName()), new HtmlParagraph("TFOOT"), new HtmlParagraph(""));
        tableWidgetsGrid.addAll(new HtmlPreformatted(HtmlTableCaption.class.getSimpleName()), new HtmlParagraph("CAPTION"), new HtmlParagraph("A container widget. You can use this to add a row to a table to use for a caption but also other widgets."));

        add(new HtmlHeading("Panel Widgets", 3));
        Grid panelWidgetsGrid = add(new Grid(3));
        panelWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);

        panelWidgetsGrid.addAll(new Panel(PanelType.DEFAULT, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.DEFAULT is a simple panel that has no padding, margin or border."));
        panelWidgetsGrid.addAll(new HtmlParagraph("Heading/Footer Panel"), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.PAGE_HEADER, PanelType.PAGE_FOOTER, PanelType.NAV_LEFT and PanelType.NAV_RIGHT. Must be added to a page, i.e., not to another container on the page. These 4 panels result in a fixed panel that is not affected by scrolling."));
        panelWidgetsGrid.addAll(new Panel(PanelType.PADDED, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.PADDED"));
        panelWidgetsGrid.addAll(new Panel(PanelType.TAB_PAGE, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.TAB_PAGE"));
        panelWidgetsGrid.addAll(new Panel(PanelType.BORDERED, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.BORDERED"));
        panelWidgetsGrid.addAll(new Panel(PanelType.TOOLBAR, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.TOOLBAR"));
        panelWidgetsGrid.addAll(new Panel(PanelType.BANNER, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.BANNER"));
        panelWidgetsGrid.addAll(new Panel(PanelType.SUCCESS, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.SUCCESS"));
        panelWidgetsGrid.addAll(new Panel(PanelType.INFO, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.INFO"));
        panelWidgetsGrid.addAll(new Panel(PanelType.WARNING, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.WARNING"));
        panelWidgetsGrid.addAll(new Panel(PanelType.ERROR, new HtmlParagraph(Panel.class.getSimpleName())), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.ERROR"));

        add(new HtmlHeading("Special Widgets", 3));
        Grid specialWidgetsGrid = add(new Grid(3));
        specialWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);

        HtmlFrame htmlFrame = new HtmlFrame();
        htmlFrame.onInit(new InitEventHandler(M.SetValue(htmlFrame, V.Const("example.html"))));
        specialWidgetsGrid.addAll(htmlFrame, new HtmlParagraph("IFRAME"), new HtmlParagraph("A standard IFRAME element. Iframes have an explicit border to serve as a visual clue that this is in fact an iframe."));

        StaticData dataForStaticDataList = add(new StaticData(DataBuilder.array(LARGE_FRUITS).getData()));
        HtmlDataList htmlDataList = add(new HtmlDataList().addDataSource(dataForStaticDataList, new KeyValueListDataAdapter()));
        HtmlTextInput htmlTextInputForDataList = new HtmlTextInput();
        htmlTextInputForDataList.setList(htmlDataList);

        specialWidgetsGrid.addAll(htmlTextInputForDataList, new HtmlParagraph("DATALIST"), new HtmlParagraph("DataList is used to store a list of options. Use HasList.setList(DataList) to reference a DataList. This can be used on a variety of input widgets such as TextBox but also Date, Time or Color widgets etc. Click on the text field on the left to display the list."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(IconsWidget.class.getSimpleName()), new HtmlParagraph(""), new HtmlParagraph("Adds a font to the page. The font file provided should contain symbols rather than normal characters and should support ligatures."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(Code.class.getSimpleName()), new HtmlParagraph("SCRIPT"), new HtmlParagraph("Creates a javascript function that can be invoked using the \"Call\" statement."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(StaticData.class.getSimpleName()), new HtmlParagraph(""), new HtmlParagraph("Creates a data source on the page that contains static data."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(CalculatedValueWidget.class.getSimpleName()), new HtmlParagraph(""), new HtmlParagraph("Creates a data source on the page that contains a value. The value is obtained using a ValueStatement such as: <code>V.DateNow</code>."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(QueryService.class.getSimpleName()), new HtmlParagraph(""), new HtmlParagraph("Creates a data source on the page that receives data from a service."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(UpdateService.class.getSimpleName()), new HtmlParagraph(""), new HtmlParagraph("Creates a data provider on the page that sends data to a service. This is almost the same as the " + QueryService.class.getSimpleName() + " except that this always performs a HTTP POST and that it does not result in a data source refresh. The data provider does raise the onResponse event that can be used to evaluate a response."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(Timer.class.getSimpleName()), new HtmlParagraph(""), new HtmlParagraph("Creates a timer."));
        specialWidgetsGrid.addAll(new HtmlPreformatted(DataTemplateWidget.class.getSimpleName()), new HtmlParagraph(""), new HtmlParagraph("Creates a widget that acts as a data consumer for a container and generates clones of a specified template widget given a data-set."));
    }
}
