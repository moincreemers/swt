package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.events.KeyPressEvent;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class GlobalEventsExample extends Page {
    public GlobalEventsExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Global Events"));

        add(new HtmlParagraph("The toolkit supports a number of events that can be subscribed to. " +
                "Lets subscribe to a KeyPress event:"));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                "TextBox textBox = add(new TextBox());\n" +
                        "onKeyPress(new KeyPressEventHandler(\n" +
                        "    M.SetValue(textBox, V.GetEvent(KeyPressEvent.KEY))\n" +
                        "));"));

        HtmlTextInput htmlTextInput = add(new HtmlTextInput());
        onKeyPress(new KeyPressEventHandler(
                M.SetValue(htmlTextInput, V.GetEvent(KeyPressEvent.KEY))
        ));

        add(HtmlButton.openPage("Open a dialog with the same event handler", DialogKeyPressEventExample.class));

        add(new HtmlParagraph("Note that onKeyPress adds the event handler to the page and in this case, " +
                "only this page will receive the event when it is the active page."));

        add(new HtmlParagraph("To add an event handler that is always received, use:"));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                "TextBox textBox = add(new TextBox());\n" +
                        "GlobalEvents.onKeyPress(new KeyPressEventHandler(\n" +
                        "    M.SetValue(textBox, V.GetEvent(KeyPressEvent.KEY))\n" +
                        "));"));

        add(new HtmlParagraph("Note that using GlobalEvents is something that is discouraged because this" +
                " can result in activity that the user is not aware of."));

        add(new HtmlHeading("Available global events", 2));
        add(new ListContainer(
                new HtmlParagraph("onBeforePrint"),
                new HtmlParagraph("onAfterPrint"),
                new HtmlParagraph("onBeforeUnload"),
                new HtmlParagraph("onUnload"),
                new HtmlParagraph("onFocus"),
                new HtmlParagraph("onBlur"),
                new HtmlParagraph("onError"),
                new HtmlParagraph("onMessage"),
                new HtmlParagraph("onOnline"),
                new HtmlParagraph("onOffline"),
                new HtmlParagraph("onUndo"),
                new HtmlParagraph("onRedo"),
                new HtmlParagraph("onKeyDown"),
                new HtmlParagraph("onKeyPress"),
                new HtmlParagraph("onKeyUp"),
                new HtmlParagraph("onLanguageChange"),
                new HtmlParagraph("onColorSchemeChange")
        ));

        add(new HtmlParagraph("Note that there are some other events that can be subscribed to such as: " +
                "onInit, onActivate, onShow, onHide and onDeactivate. " +
                "However, these events are not global events."));
    }
}
