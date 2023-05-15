package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class DialogOnTopOfAnotherDialogExample extends Page {
    public DialogOnTopOfAnotherDialogExample() throws Exception {
        super(ViewType.DIALOG, ViewPosition.DIALOG_CENTER);
    }

    @Override
    protected void build() throws Exception {
        Panel headingPanel = add(new Panel(PanelType.ERROR));
        headingPanel.add(new HtmlHeading("Another Dialog (" + getId() + ")"));

        Panel bodyPanel = add(new Panel(PanelType.PADDED));
        bodyPanel.add(new HtmlParagraph("This is another dialog on top of a dialog."));

        Panel footerPanel = add(new Panel(PanelType.PADDED));
        footerPanel.add( HtmlButton.closePage(ButtonType.PRIMARY, "Ok", V.Ok));
        footerPanel.add( HtmlButton.closePage("Cancel", V.Cancel));
        footerPanel.add(HtmlButton.openPage(ButtonType.ERROR, "Open sidebar dialog", SidebarLeftDialogExample.class));

        footerPanel.add(HtmlButton.openPage(ButtonType.INFO, "Open Center again", DialogCenterExample.class));
    }
}
