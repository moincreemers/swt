package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.data.DtoViewDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class TableExample extends Page {
    public TableExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Tables"));

        add(new HtmlParagraph("Tables are used to display data. Data does not only consist of text and depending on use " +
                "case, the formatting of the data can be different. The toolkit supports several ways to format data."));

        add(new HtmlHeading("Text Fields", 2));

        class StringRecord {
            public String text = "This text is aligned to the left. This is an HTML default. By making this " +
                    "text a little larger we can demonstrate the alignment options in the other columns. The toolkit " +
                    "supports aligning to the top-left, top-center, top-right, middle-left (default), " +
                    "middle-center, middle-right, bottom-left, bottom-center and bottom-right. The toolkit also " +
                    "supports setting text- and background color and a maximum width and height which only applies to table cells.";

            public String topLeft = "Top-left";
            public String topCenter = "Top-center";
            public String topRight = "Top-right";
            public String middleLeft = "Middle-left";
            public String middleCenter = "Middle-center";
            public String middleRight = "Middle-right";
            public String bottomLeft = "Bottom-left";
            public String bottomCenter = "Bottom-center";
            public String bottomRight = "Bottom-right";
        }
        TabWidget stringTabWidget = add(new TabWidget("Table", "Data"));
        stringTabWidget.panel(1).setOverflowAndSize(Overflow.FIXED_SIZE, Size.getDefaultTabPage());
        StaticData stringStaticData = add(new StaticData(Arrays.asList(new StringRecord())));
        DtoViewDataAdapter stringDataAdapter = new DtoViewDataAdapter(StringRecord.class);
        stringDataAdapter.setFormat("text", new TextFormat().setCellMaxWidth("20ch").setCellColor("white").setCellBackgroundColor("green"));
        stringDataAdapter.setFormat("topLeft", new TextFormat().setCellAlignment(TextAlignmentType.TOP_LEFT));
        stringDataAdapter.setFormat("topCenter", new TextFormat().setCellAlignment(TextAlignmentType.TOP_CENTER));
        stringDataAdapter.setFormat("topRight", new TextFormat().setCellAlignment(TextAlignmentType.TOP_RIGHT));
        stringDataAdapter.setFormat("middleLeft", new TextFormat().setCellAlignment(TextAlignmentType.MIDDLE_LEFT));
        stringDataAdapter.setFormat("middleCenter", new TextFormat().setCellAlignment(TextAlignmentType.MIDDLE_CENTER));
        stringDataAdapter.setFormat("middleRight", new TextFormat().setCellAlignment(TextAlignmentType.MIDDLE_RIGHT));
        stringDataAdapter.setFormat("bottomLeft", new TextFormat().setCellAlignment(TextAlignmentType.BOTTOM_LEFT));
        stringDataAdapter.setFormat("bottomCenter", new TextFormat().setCellAlignment(TextAlignmentType.BOTTOM_CENTER));
        stringDataAdapter.setFormat("bottomRight", new TextFormat().setCellAlignment(TextAlignmentType.BOTTOM_RIGHT));
        stringStaticData.addDataAdapter(stringDataAdapter);
        HtmlTable stringHtmlTable = stringTabWidget.panel(0).add(new HtmlTable());
        stringHtmlTable.add(new HtmlTableHeader().addDataSource(stringStaticData));
        stringHtmlTable.add(new HtmlTableBody().addDataSource(stringStaticData));
        stringTabWidget.panel(1).add(new HtmlPreformatted(TextFormatType.JSON).addDataSource(stringStaticData));

        add(new HtmlHeading("Boolean Fields", 2));

        class BooleanRecord {
            public boolean noFormatFalse = false;
            public boolean noFormatTrue = true;
            public boolean circleFalse = false;
            public boolean circleTrue = true;
            public boolean squareFalse = false;
            public boolean squareTrue = true;
        }
        TabWidget booleanTabWidget = add(new TabWidget("Table", "Data"));
        booleanTabWidget.panel(1).setOverflowAndSize(Overflow.FIXED_SIZE, Size.getDefaultTabPage());
        StaticData booleanStaticData = add(new StaticData(Arrays.asList(new BooleanRecord())));
        DtoViewDataAdapter booleanDataAdapter = new DtoViewDataAdapter(BooleanRecord.class);
        booleanDataAdapter.setFormat("circleFalse", new BooleanFormat().setAppearance(BooleanAppearanceType.CIRCLE));
        booleanDataAdapter.setFormat("circleTrue", new BooleanFormat().setAppearance(BooleanAppearanceType.CIRCLE));
        booleanDataAdapter.setFormat("squareFalse", new BooleanFormat().setAppearance(BooleanAppearanceType.SQUARE));
        booleanDataAdapter.setFormat("squareTrue", new BooleanFormat().setAppearance(BooleanAppearanceType.SQUARE));
        booleanStaticData.addDataAdapter(booleanDataAdapter);
        HtmlTable booleanHtmlTable = booleanTabWidget.panel(0).add(new HtmlTable());
        booleanHtmlTable.add(new HtmlTableHeader().addDataSource(booleanStaticData));
        booleanHtmlTable.add(new HtmlTableBody().addDataSource(booleanStaticData));
        booleanTabWidget.panel(1).add(new HtmlPreformatted(TextFormatType.JSON).addDataSource(booleanStaticData));

        add(new HtmlHeading("Number Fields", 2));

        class NumberRecord {
            static final double N = Math.PI;
            public double noFormat = N;
            public double twoDecimals = N;
            public double scientific = N;
            public double accounting = -N;
            public double percent = 0.5;
            public double currency = N;
            public double anotherCurrency = N;
            public double unit = N;
        }
        TabWidget numberTabWidget = add(new TabWidget("Table", "Data"));
        numberTabWidget.panel(1).setOverflowAndSize(Overflow.FIXED_SIZE, Size.getDefaultTabPage());
        StaticData numberStaticData = add(new StaticData(Arrays.asList(new NumberRecord())));
        DtoViewDataAdapter numberDataAdapter = new DtoViewDataAdapter(NumberRecord.class);
        numberDataAdapter.setFormat("twoDecimals", new NumberFormat().setMaximumFractionDigits(2));
        numberDataAdapter.setFormat("scientific", new NumberFormat().setNotation(NotationType.SCIENTIFIC));
        numberDataAdapter.setFormat("accounting", new NumberFormat().setStyle(StyleType.CURRENCY).setCurrency("USD").setCurrencySign(CurrencySignType.ACCOUNTING).setMaximumFractionDigits(2));
        numberDataAdapter.setFormat("percent", new NumberFormat().setStyle(StyleType.PERCENT));
        numberDataAdapter.setFormat("currency", new NumberFormat().setStyle(StyleType.CURRENCY).setCurrency("USD"));
        numberDataAdapter.setFormat("anotherCurrency", new NumberFormat().setStyle(StyleType.CURRENCY).setCurrency("JPY"));
        numberDataAdapter.setFormat("unit", new NumberFormat().setStyle(StyleType.UNIT).setUnit("kilometer-per-hour"));
        numberStaticData.addDataAdapter(numberDataAdapter);
        HtmlTable numberHtmlTable = numberTabWidget.panel(0).add(new HtmlTable());
        numberHtmlTable.add(new HtmlTableHeader().addDataSource(numberStaticData));
        numberHtmlTable.add(new HtmlTableBody().addDataSource(numberStaticData));
        numberTabWidget.panel(1).add(new HtmlPreformatted(TextFormatType.JSON).addDataSource(numberStaticData));

        add(new HtmlHeading("Date Fields", 2));

        class DateRecord {
            public Date noFormat = new Date();
            public Date formatted = new Date();
        }
        TabWidget dateTabWidget = add(new TabWidget("Table", "Data"));
        dateTabWidget.panel(1).setOverflowAndSize(Overflow.FIXED_SIZE, Size.getDefaultTabPage());
        StaticData dateStaticData = add(new StaticData(Arrays.asList(new DateRecord())));
        DtoViewDataAdapter dateDataAdapter = new DtoViewDataAdapter(DateRecord.class);
        dateDataAdapter.setFormat("formatted", new DateTimeFormat().setDateStyle(DateStyleType.FULL));
        dateStaticData.addDataAdapter(dateDataAdapter);
        HtmlTable dateHtmlTable = dateTabWidget.panel(0).add(new HtmlTable());
        dateHtmlTable.add(new HtmlTableHeader().addDataSource(dateStaticData));
        dateHtmlTable.add(new HtmlTableBody().addDataSource(dateStaticData));
        dateTabWidget.panel(1).add(new HtmlPreformatted(TextFormatType.JSON).addDataSource(dateStaticData));

        add(new HtmlHeading("URL Fields", 2));

        class URLRecord {
            public String textURL = "http://example.com/";
            public String linkURL = "http://example.com/";
            public String buttonURL = "http://example.com/";
            public String imageURL = "/example.jpg";
        }
        TabWidget urlTabWidget = add(new TabWidget("Table", "Data"));
        urlTabWidget.panel(1).setOverflowAndSize(Overflow.FIXED_SIZE, Size.getDefaultTabPage());
        StaticData urlStaticData = add(new StaticData(Arrays.asList(new URLRecord())));
        DtoViewDataAdapter urlDataAdapter = new DtoViewDataAdapter(URLRecord.class);
        urlDataAdapter.setFormat("linkURL", new URLFormat().setAppearance(URLAppearanceType.ANCHOR));
        urlDataAdapter.setFormat("buttonURL", new URLFormat().setAppearance(URLAppearanceType.BUTTON).setText("Click me"));
        urlDataAdapter.setFormat("imageURL", new URLFormat().setAppearance(URLAppearanceType.IMAGE).setImageWidth("100px").setImageBorderRadius("0.5em"));
        urlStaticData.addDataAdapter(urlDataAdapter);
        HtmlTable urlHtmlTable = urlTabWidget.panel(0).add(new HtmlTable());
        urlHtmlTable.add(new HtmlTableHeader().addDataSource(urlStaticData));
        urlHtmlTable.add(new HtmlTableBody().addDataSource(urlStaticData));
        urlTabWidget.panel(1).add(new HtmlPreformatted(TextFormatType.JSON).addDataSource(urlStaticData));

        add(new HtmlHeading("Array Fields", 2));

        class ArrayRecord {
            public String[] join = new String[]{"One", "Two", "Three"};
            public String[] text = new String[]{"One", "Two", "Three"};
            public String[] first = new String[]{"One", "Two", "Three"};
            public String[] last = new String[]{"One", "Two", "Three"};
        }
        TabWidget arrayTabWidget = add(new TabWidget("Table", "Data"));
        arrayTabWidget.panel(1).setOverflowAndSize(Overflow.FIXED_SIZE, Size.getDefaultTabPage());
        StaticData arrayStaticData = add(new StaticData(Arrays.asList(new ArrayRecord())));
        DtoViewDataAdapter arrayDataAdapter = new DtoViewDataAdapter(ArrayRecord.class);
        arrayDataAdapter.setFormat("join", new ArrayFormat().setAppearance(ArrayAppearanceType.JOIN).setSeparator("; "));
        arrayDataAdapter.setFormat("text", new ArrayFormat().setAppearance(ArrayAppearanceType.TEXT));
        arrayDataAdapter.setFormat("first", new ArrayFormat().setAppearance(ArrayAppearanceType.FIRST));
        arrayDataAdapter.setFormat("last", new ArrayFormat().setAppearance(ArrayAppearanceType.LAST));
        arrayStaticData.addDataAdapter(arrayDataAdapter);
        HtmlTable arrayHtmlTable = arrayTabWidget.panel(0).add(new HtmlTable());
        arrayHtmlTable.add(new HtmlTableHeader().addDataSource(arrayStaticData));
        arrayHtmlTable.add(new HtmlTableBody().addDataSource(arrayStaticData));
        arrayTabWidget.panel(1).add(new HtmlPreformatted(TextFormatType.JSON).addDataSource(arrayStaticData));
    }
}
