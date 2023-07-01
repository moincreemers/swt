package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.CloseDialogEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ColorSchemeChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class HtmlDialogExamplePage extends Page {
    public HtmlDialogExamplePage() throws Exception {
        super(Constants.isDemo(HtmlDialogExamplePage.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        onColorSchemeChange(ColorSchemeChangeEventHandler.getDefaultHandler());

        add(new HtmlHeading("Dialog widget"));

        add(new HtmlParagraph("""
                HtmlDialog is a container widget that can be used to display a dialog.
                                
                <pre>
                HtmlDialog dialog = add(new HtmlDialog());
                ...
                HtmlButton showDialogButton = add(new HtmlButton("Show dialog"));
                HtmlButton cancelDialogButton = buttonPanel.add(new HtmlButton("Cancel"));
                HtmlButton okDialogButton = buttonPanel.add(new HtmlButton("OK"));
                showDialogButton.onClick(new ClickEventHandler(
                    M.ShowDialog(dialog)
                ));
                cancelDialogButton.onClick(new ClickEventHandler(
                    M.CloseDialog(dialog, V.Cancel)
                ));
                okDialogButton.onClick(new ClickEventHandler(
                    M.CloseDialog(dialog, V.Ok)
                ));
                </pre>
                """));

        HtmlDialog dialog = add(new HtmlDialog());
        dialog.add(new HtmlParagraph("""
                This is a simple dialog.<br/><br/>
                                
                Note that the dialog is modal, meaning that<br/>
                the rest of the page is not accessible until<br/>
                the dialog is closed.<br/><br/>
                                
                You can always use the ESC key to close a<br/>
                modal dialog.
                """));

        Panel buttonPanel = dialog.add(new Panel());
        HtmlButton showDialogButton = add(new HtmlButton("Show dialog"));
        HtmlButton cancelDialogButton = buttonPanel.add(new HtmlButton("Cancel"));
        HtmlButton okDialogButton = buttonPanel.add(new HtmlButton("OK"));
        showDialogButton.onClick(new ClickEventHandler(
                M.OpenDialog(dialog)
        ));
        cancelDialogButton.onClick(new ClickEventHandler(
                M.CloseDialog(dialog, V.Cancel)
        ));
        okDialogButton.onClick(new ClickEventHandler(
                M.CloseDialog(dialog, V.Ok)
        ));
        dialog.onClose(new CloseDialogEventHandler(
                M.Iif(V.Is(V.GetReturnValue(dialog), V.Ok)).True(
                        M.Log(V.Const("OK"))
                ),
                M.Iif(V.Is(V.GetReturnValue(dialog), V.Cancel)).True(
                        M.Log(V.Const("Cancel"))
                )
        ));

        add (new HtmlHeading("Responding to the Dialog", 2));

        add(new HtmlParagraph("""
                HtmlDialog supports two events that are useful:
                <code>OpenDialogEventHandler</code> and <code>CloseDialogEventHandler</code>
                <br/>
                <br/>
                For example, the following code could be used to handle the result of the dialog above:
                <pre>
                dialog.onClose(new CloseDialogEventHandler(
                        M.Iif(V.Is(V.GetReturnValue(dialog), V.Ok)).True(
                                ...
                        ),
                        M.Iif(V.Is(V.GetReturnValue(dialog), V.Cancel)).True(
                                ...
                        )
                ));
                </pre>
                """));


    }
}
