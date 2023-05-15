package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.events.ClickOutsideDialogEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEvent;
import com.philips.dmis.swt.ui.toolkit.events.KeyDownEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class DialogCenterExample extends Page {
    public DialogCenterExample() throws Exception {
        super(ViewType.DIALOG, ViewPosition.DIALOG_CENTER);
    }

    @Override
    protected void build() throws Exception {
        IconsWidget icons = add(new IconsWidget("MaterialSymbolsSharp.woff2"));

        Panel headingPanel = add(new Panel(PanelType.INFO));
        headingPanel.add(new HtmlHeading(icons, "info", "Dialog (" + getId() + ")"));

        Panel bodyPanel = add(new Panel(PanelType.PADDED));
        bodyPanel.add(new HtmlParagraph("This is a simple dialog page."));

        ListContainer listContainer = bodyPanel.add(new ListContainer());
        listContainer.add(new HtmlParagraph("A dialog opens in the context of the page that opened it. This means that page will remain visible."));
        listContainer.add(new HtmlParagraph("To close a page, use the M.ClosePage statement. The page that opened the dialog will become the active page again."));
        listContainer.add(new HtmlParagraph("When switching pages, information can be provided to the next page."));
        listContainer.add(new HtmlParagraph("The dialog will always open in the current viewport even when the page is scrolled so it is always visible."));
        listContainer.add(new HtmlParagraph("A dialog can also open another popup-dialog or sidebar-dialog."));
        listContainer.add(new HtmlParagraph("On this dialog, a KeyDownEventHandler is used to detect when the user presses Escape or Enter which is then used to close the dialog."));
        listContainer.add(new HtmlParagraph("A ClickOutsideDialogEventHandler is used to close the dialog when the user clicks outside the dialog."));

        Panel footerPanel = add(new Panel(PanelType.PADDED));
        footerPanel.setAppearance(WidgetAppearance.ALIGN_RIGHT);
        footerPanel.add(HtmlButton.closePage(ButtonType.PRIMARY, "Ok", V.Ok));
        footerPanel.add(HtmlButton.closePage("Cancel", V.Cancel));
        footerPanel.add(HtmlButton.openPage(ButtonType.INFO, "Open Another Dialog", DialogOnTopOfAnotherDialogExample.class));

        onKeyDown(new KeyDownEventHandler(
                M.Log(V.GetEvent(KeyDownEvent.KEY_CODE)),
                M.Iif(V.Is(V.GetEvent(KeyDownEvent.KEY_CODE), V.Const(KeyDownEvent.VK_ESCAPE))).True(M.ClosePage(V.Cancel)),
                M.Iif(V.Is(V.GetEvent(KeyDownEvent.KEY_CODE), V.Const(KeyDownEvent.VK_ENTER))).True(M.ClosePage(V.Ok))
        ));

        onClickOutsideDialog(new ClickOutsideDialogEventHandler(
                M.ClosePage(V.Cancel)
        ));
    }
}
