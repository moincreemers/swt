package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.events.ActivateEventHandler;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class ViewControlExample extends Page {
    public ViewControlExample() throws Exception {
        super(true);
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("The View Controller"));

        add(new HtmlParagraph("The view controller component allows switching between pages."));

        add(new HtmlHeading("Default Page", 2));

        add(new HtmlParagraph("The initial page of the application is determined by the \"default page\" argument of the Page class:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "public SomePage() throws Exception {\n" +
                "super(true);\n" +
                "}"));

        add(new HtmlHeading("Opening and Closing a Page", 2));

        add(new HtmlParagraph("To switch to any other page, use M.OpenPage in an event handler:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "button.onClick(new ClickEventHandler(\n" +
                "M.OpenPage(DialogCenterExample.class)\n" +
                "));"));

        add(new HtmlParagraph("Button and Link also have static methods for this:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "Button.openPage(\"Open a Dialog\", DialogCenterExample.class)"));

        add(new HtmlParagraph("To switch back to the previous page, use M.ClosePage in an event handler:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "button.onClick(new ClickEventHandler(\n" +
                "M.ClosePage()\n" +
                "));"));
        add(new HtmlParagraph("The ClosePage statement knows which page was the previous by looking at the breadcrumb " +
                "trail stored by the view controller. " +
                "All view controller state is stored in the browser history so the Browser Back and Forward functions " +
                "work normally. It will look something like this:"));

        add(new HtmlPreformatted(TextFormatType.TEXT, "https://localhost/#p=w321&o=w123&o=w452&o=w23"));
        add(new HtmlParagraph("This means that the current page is \"w321\" and the previous page is \"w23\". Opening " +
                "another page will set \"p\" to the new page id and will add the current page id to the history. " +
                "Closing the page will move the last page in the history to \"p\" and remove it from the history."));

        add(new HtmlParagraph("Note that this also means that the \"hash\" portion of the URL is used by the " +
                "View Controller for navigation and cannot be used to link to a location in the HTML document."));

        add(new HtmlHeading("Sending information", 2));
        add(new HtmlParagraph("The OpenPage and ClosePage statements also allow you to " +
                "send additional information to the page:"));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "Button.openPage(\"Select Food Type\", DialogCenterExample.class, V.Const(\"fruit\"))"));

        add(HtmlButton.openPage("Select Food Types", SelectFoodTypesDialog.class, V.Const("fruits")));

        add(new HtmlParagraph("Click the button to open the Dialog and then inspect the state of the view controller " +
                "in the address bar. The value \"fruits\" should appear as an argument."));

        add(new HtmlParagraph("Using the return value of another page " +
                "can be done using the onActivate event (see below) of the page and the GetReturnValue statement:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                "onActivate(\n" +
                    "  new ActivateEventHandler(\n" +
                    "    M.Set(selectedFoodTypeLabel,\n" +
                    "      V.Concat(\n" +
                    "        V.Const(\"The selected food type is: \"),\n" +
                    "        V.GetReturnValue()\n" +
                    "    )\n" +
                    "  )\n" +
                    ")"));

        HtmlLabel selectedFoodTypeHtmlLabel = add(new HtmlLabel("The selected food type is: (not selected yet)"));
        onActivate(new ActivateEventHandler(
                M.SetText(selectedFoodTypeHtmlLabel,
                        V.StringConcat(
                                V.Const("The selected food type is: "),
                                V.GetPageArgument()
                        )
                )
        ));

        add(new HtmlParagraph("This works because the onActivate event is fired for any page that " +
                "becomes the active page. As soon as the Dialog is closed, the previous page becomes the activate page " +
                "and we can then use the return value."));

        add(new HtmlHeading("Page events", 2));
        add(new HtmlParagraph("The view controller raises these events when switching between pages:"));
        add(new ListContainer(
                new HtmlParagraph("onShow: raised when the page is made visible."),
                new HtmlParagraph("onActivate: raised when the page is activated."),
                new HtmlParagraph("onDeactivate: raised when the page is deactivated."),
                new HtmlParagraph("onHide: raised when the page is hidden.")));
    }
}
