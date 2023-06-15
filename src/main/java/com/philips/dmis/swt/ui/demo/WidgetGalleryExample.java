package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ColorSchemeChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InitEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
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
        textWidgetsGrid.addAll(new Panel(new HtmlHeading("Heading 1", 1), new HtmlHeading("Heading 2", 2), new HtmlHeading("Heading 3", 3), new HtmlHeading("Heading 4", 4), new HtmlHeading("Heading 5", 5), new HtmlHeading("Heading 6", 6)), new HtmlParagraph("H1, H2, H3, H4, H5, H6"), new HtmlParagraph("Normal headings."));
        textWidgetsGrid.addAll(new Panel(
                new HtmlHeading("Numbered heading 1", 1, true),
                new HtmlHeading("Numbered heading 2", 2, true),
                new HtmlHeading("Numbered heading 3", 3, true),
                new HtmlHeading("Numbered heading 4", 4, true),
                new HtmlHeading("Numbered heading 5", 5, true),
                new HtmlHeading("Numbered heading 6", 6, true)
        ), new HtmlParagraph(""), new HtmlParagraph("Numbered headings are often used for (technical) documentation. Note that numbering is scoped to a page."));
        textWidgetsGrid.addAll(new HtmlParagraph("Paragraph"), new HtmlParagraph("P"), new HtmlParagraph("Paragraphs are the only widgets that allow HTML content. However, only the following HTML elements are allowed: a, b, i, u. Additionally, a link may only have a \"href\" attribute that contains an HTTP or HTTPS URL. If the HTML is considered invalid, it will be replaced by the phrase \"unsafe content\"."));
        textWidgetsGrid.addAll(new HtmlPreformatted("Preformatted"), new HtmlParagraph("PRE"), new HtmlParagraph("Preformatted text is meant for things like code. Wraps text at spaces. You can use TextFormat.JSON to display JSON documents. The toolkit will then format the documents for you."));
        textWidgetsGrid.addAll(new ListContainer(new HtmlParagraph("Item 1"), new HtmlParagraph("Item 2"), new HtmlParagraph("Item 3")), new HtmlParagraph("UL"), new HtmlParagraph("List is a container widget that displays list items. List shows as an unordered list by default."));
        textWidgetsGrid.addAll(new ListContainer(ListType.ORDERED, new HtmlParagraph("Item 1"), new HtmlParagraph("Item 2"), new HtmlParagraph("Item 3")), new HtmlParagraph("OL"), new HtmlParagraph("ListType.ORDERED"));
        textWidgetsGrid.addAll(new ListContainer(ListType.MENU, new HtmlLink("Item 1"), new HtmlLink("Item 2"), new HtmlLink("Item 3")), new HtmlParagraph("UL"), new HtmlParagraph("ListType.MENU"));
        textWidgetsGrid.addAll(new ListContainer(ListType.INLINE_MENU, new HtmlLink("Item 1"), new HtmlLink("Item 2"), new HtmlLink("Item 3")), new HtmlParagraph("UL"), new HtmlParagraph("ListType.INLINE_MENU"));

        add(new HtmlHeading("Input Widgets", 3));
        Grid inputWidgetsGrid = add(new Grid(3));
        inputWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);
        inputWidgetsGrid.addAll(new Panel(PanelType.BANNER, new HtmlLabel(new HtmlTextInput(), "Label")), new HtmlParagraph("LABEL"), new HtmlParagraph("Label elements are typically used in combination with an input field. But they can also be used without. Labels also support icons."));
        inputWidgetsGrid.addAll(new HtmlLink("Link"), new HtmlParagraph("A"), new HtmlParagraph("Links are functionally equivalent to buttons. Links support icons."));
        inputWidgetsGrid.addAll(new HtmlButton("Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.DEFAULT."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.PRIMARY, "Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.PRIMARY."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.SUCCESS, "Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.SUCCESS."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.INFO, "Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.INFO."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.WARNING, "Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.WARNING."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.ERROR, "Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.ERROR."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.DEFAULT, icons, "search", "Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.DEFAULT + icon."));
        inputWidgetsGrid.addAll(new HtmlButton(ButtonType.ERROR, icons, "error", "Button"), new HtmlParagraph("BUTTON"), new HtmlParagraph("ButtonType.ERROR + icon"));
        inputWidgetsGrid.addAll(new HtmlImageButton("example.jpg").setImageSize(ImageSize.customSize("150px", "")).setAppearance(WidgetAppearance.ROUNDED_CORNERS), new HtmlParagraph("INPUT type=image"), new HtmlParagraph("Image input can be used instead of a button to submit a form or just used to display an image. The widget supports data binding so this can be used if the datasource contains an URL for example."));
        inputWidgetsGrid.addAll(new HtmlCheckInput(), new HtmlParagraph("INPUT type=checkbox"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlTextInput("Some text"), new HtmlParagraph("INPUT, type=text"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlPasswordInput(), new HtmlParagraph("INPUT, type=password"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlDateInput(), new HtmlParagraph("INPUT, type=date"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlTimeInput(), new HtmlParagraph("INPUT, type=time"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlDateTimeInput(), new HtmlParagraph("INPUT, type=datetime-local"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlMonthInput(), new HtmlParagraph("INPUT, type=month"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlWeekInput(), new HtmlParagraph("INPUT, type=week"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlColor(), new HtmlParagraph("INPUT, type=color"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlEmailInput(), new HtmlParagraph("INPUT, type=email"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlTelephoneInput(), new HtmlParagraph("INPUT, type=tel"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlURLInput(), new HtmlParagraph("INPUT, type=url"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlSearchInput(), new HtmlParagraph("INPUT, type=search"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlNumberInput(), new HtmlParagraph("INPUT, type=number"), new HtmlParagraph(""));

        HtmlFile htmlFileInput = new HtmlFile();
        HtmlButton fileSelect = new HtmlButton("Browse...");
        fileSelect.onClick(new ClickEventHandler(
                M.Click(htmlFileInput)
        ));
        HtmlTextInput filesSelected = new HtmlTextInput();
        htmlFileInput.onChange(new ChangeEventHandler(
                M.SetValue(filesSelected, V.GetValue(htmlFileInput))
        ));

        inputWidgetsGrid.addAll(new Panel(htmlFileInput, fileSelect, filesSelected), new HtmlParagraph("INPUT, type=file"), new HtmlParagraph("File upload. The file upload widget itself is invisible. Use M.Click to open the file dialog."));
        inputWidgetsGrid.addAll(new HtmlRangeInput(), new HtmlParagraph("INPUT, type=range"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlMeter(), new HtmlParagraph("METER"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlProgress(), new HtmlParagraph("PROGRESS"), new HtmlParagraph(""));
        inputWidgetsGrid.addAll(new HtmlSelect().addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()), new HtmlParagraph("SELECT"), new HtmlParagraph("Standard drop down list"));
        inputWidgetsGrid.addAll(new HtmlSelect(3).addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()), new HtmlParagraph("SELECT, size=3"), new HtmlParagraph("ListBox with size 3. Note that the SingleChoice widget probably provides a better User Experience."));
        inputWidgetsGrid.addAll(new HtmlSelect(3, true).addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()), new HtmlParagraph("SELECT, size=3, muliple"), new HtmlParagraph("ListBox with size 3 that allows multiple values to be selected. Note that the MultipleChoice widget probably provides a better User Experience."));
        inputWidgetsGrid.addAll(new SingleChoice().addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph("A single choice widget using Radio buttons and Labels."));
        inputWidgetsGrid.addAll(new SingleChoice(SingleChoiceAppearance.INLINE).addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph("SingleChoiceAppearance.INLINE."));
        inputWidgetsGrid.addAll(new Panel(new SingleChoice(SingleChoiceAppearance.TABS).addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()).setValue("Apple")), new HtmlParagraph(""), new HtmlParagraph("SingleChoiceAppearance.TABS."));
        inputWidgetsGrid.addAll(new MultipleChoice().addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph("A multiple choice widget using CheckBoxes and Labels."));
        inputWidgetsGrid.addAll(new MultipleChoice(MultipleChoiceAppearance.INLINE).addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, options, new KeyValueListDataAdapter()), new HtmlParagraph(""), new HtmlParagraph("MultipleChoiceAppearance.INLINE."));

        add(new HtmlHeading("Grid and Table Widgets", 3));
        Grid tableWidgetsGrid = add(new Grid(3));
        tableWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);

        SingleRowPanel singleRowPanel = tableWidgetsGrid.add(new SingleRowPanel(PanelType.TOOLBAR));
        singleRowPanel.addAll(new HtmlButton(icons, "undo"), new HtmlButton(icons, "redo"), new HtmlButton(icons, "save"));
        tableWidgetsGrid.addAll(singleRowPanel, new HtmlParagraph("TABLE or CSS-table"), new HtmlParagraph("SingleRowPanel is a Panel that displays widgets horizontally without breaking to the next line. In this example the PanelType.TOOLBAR is used."));

        Grid gridExample = new Grid(2);
        gridExample.setAppearance(WidgetAppearance.BORDERED);
        gridExample.addAll(new HtmlLabel("Selection"), new HtmlLabel("Description"));
        gridExample.addAll(new HtmlCheckInput(), new HtmlParagraph("Some text 1"));
        gridExample.addAll(new HtmlCheckInput(), new HtmlParagraph("Some text 2"));
        gridExample.addAll(new HtmlCheckInput(), new HtmlParagraph("Some text 3"));
        tableWidgetsGrid.addAll(gridExample, new HtmlParagraph("TABLE or CSS-table"), new HtmlParagraph("Grid is a container widget. Implemented as a CSS-Table."));

        tableWidgetsGrid.addAll(new HtmlPreformatted("Table"), new HtmlParagraph("TABLE"), new HtmlParagraph(""));
        tableWidgetsGrid.addAll(new HtmlPreformatted("TableHeader"), new HtmlParagraph("THEAD"), new HtmlParagraph(""));
        tableWidgetsGrid.addAll(new HtmlPreformatted("TableBody"), new HtmlParagraph("TBODY"), new HtmlParagraph("Any number of TableBody widgets may be added to a Table widget. Supports data-binding."));
        tableWidgetsGrid.addAll(new HtmlPreformatted("TableFooter"), new HtmlParagraph("TFOOT"), new HtmlParagraph(""));
        tableWidgetsGrid.addAll(new HtmlPreformatted("Caption"), new HtmlParagraph("CAPTION"), new HtmlParagraph("A container widget. You can use this to add a row to a table to use for a caption but also other widgets."));

        add(new HtmlHeading("Panel Widgets", 3));
        Grid panelWidgetsGrid = add(new Grid(3));
        panelWidgetsGrid.setAppearance(WidgetAppearance.BORDERED);

        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.DEFAULT, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.HEADING. Must be added to a page, i.e., not to another container on the page."));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.DEFAULT, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.FOOTER. Must be added to a page, i.e., not to another container on the page."));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.PADDED, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.PADDED"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.TAB_PAGE, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.TAB_PAGE"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.BORDERED, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.BORDERED"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.TOOLBAR, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.TOOLBAR"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.BANNER, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.BANNER"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.SUCCESS, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.SUCCESS"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.INFO, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.INFO"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.WARNING, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.WARNING"));
        panelWidgetsGrid.addAll(new Panel(WidgetType.PANEL, PanelType.ERROR, new HtmlParagraph("Panel")), new HtmlParagraph("DIV"), new HtmlParagraph("PanelType.ERROR"));

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

        specialWidgetsGrid.addAll(htmlTextInputForDataList, new HtmlParagraph("DATALIST"), new HtmlParagraph("DataList is used to store a list of options. Use HasList.setList(DataList) to reference a DataList. This can be used on a variety of input widgets such as TextBox but also Date, Time or Color widgets etc."));
        specialWidgetsGrid.addAll(new HtmlPreformatted("IconsWidget"), new HtmlParagraph(""), new HtmlParagraph("Adds a font to the page. The font file provided should contain symbols rather than normal characters and should support ligatures."));
        specialWidgetsGrid.addAll(new HtmlPreformatted("CodeWidget"), new HtmlParagraph("SCRIPT"), new HtmlParagraph("Creates a javascript function that can be invoked using the \"Call\" statement."));
        specialWidgetsGrid.addAll(new HtmlPreformatted("Data"), new HtmlParagraph(""), new HtmlParagraph("Creates a data source on the page that contains static data."));
        specialWidgetsGrid.addAll(new HtmlPreformatted("QueryService"), new HtmlParagraph(""), new HtmlParagraph("Creates a data source on the page that receives data from a service."));
        specialWidgetsGrid.addAll(new HtmlPreformatted("UpdateService"), new HtmlParagraph(""), new HtmlParagraph("Creates a data provider on the page that sends data to a service."));
        specialWidgetsGrid.addAll(new HtmlPreformatted("TimerWidget"), new HtmlParagraph(""), new HtmlParagraph("Creates a timer."));
    }
}
