package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class MainDemoPage extends Page {
    public MainDemoPage() throws Exception {
        super(Constants.isDemo(MainDemoPage.class));
    }

    @Override
    protected void build() throws Exception {
        add(new HtmlHeading("Examples"));

        ListContainer listContainer = add(new ListContainer());
        listContainer.add(HtmlLink.openPage("Hello world", HelloWorldExample.class));
        listContainer.add(HtmlLink.openPage("The View Controller", ViewControlExample.class));
        listContainer.add(HtmlLink.openPage("Data Binding", DataBindingExample.class));
        //listContainer.add(HtmlLink.openPage("Data Adapters", DataAdaptersExample.class));
        listContainer.add(HtmlLink.openPage("Tables", TableExample.class));
        listContainer.add(HtmlLink.openPage("Text-Rich Pages", TextExample.class));
        listContainer.add(HtmlLink.openPage("Headings", HeadingsExample.class));
        listContainer.add(HtmlLink.openPage("Numbered headings", NumberedHeadingsExample.class));
        listContainer.add(HtmlLink.openPage("Widget Gallery", WidgetGalleryExample.class));
        listContainer.add(HtmlLink.openPage("Modifying Appearance", AppearanceExample.class));
        listContainer.add(HtmlLink.openPage("Pages", PagesExamplePage.class));
        listContainer.add(HtmlLink.openPage("Dialogs", DialogsExamplePage.class));
        listContainer.add(HtmlLink.openPage("Icons", IconsExample.class));
        listContainer.add(HtmlLink.openPage("Integrating a Third-Party API", GoogleBooksExample.class));
        listContainer.add(HtmlLink.openPage("Image", ImageExample.class));
        listContainer.add(HtmlLink.openPage("Frames", FrameExample.class));
        listContainer.add(HtmlLink.openPage("Header & Footer Panels", HeaderFooterPanelExample.class));
        listContainer.add(HtmlLink.openPage("Timers", TimerExample.class));
        listContainer.add(HtmlLink.openPage("Using Statements", StatementsExample.class));
        listContainer.add(HtmlLink.openPage("Global Events", GlobalEventsExample.class));
        listContainer.add(HtmlLink.openPage("Events", EventsExample.class));
        listContainer.add(HtmlLink.openPage("Timesheet Dashboard", TimesheetDashboardExample.class));
        listContainer.add(HtmlLink.openPage("OS color theme", OsLightAndDarkThemeExample.class));
        listContainer.add(HtmlLink.openPage("Spinner", SpinnerExample.class));
        listContainer.add(HtmlLink.openPage("Templates", TemplateDemoPage.class));
    }
}
