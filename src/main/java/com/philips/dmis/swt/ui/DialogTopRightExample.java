package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class DialogTopRightExample extends Page {
    public DialogTopRightExample() throws Exception {
        super(ViewType.DIALOG, ViewPosition.DIALOG_TOP_RIGHT);
    }

    @Override
    protected void build() throws Exception {
        Panel headingPanel = add(new Panel(PanelType.WARNING));
        headingPanel.add(new HtmlHeading("Dialog (" + getId() + ")"));

        Panel bodyPanel = add(new Panel(PanelType.PADDED));
        bodyPanel.add(new HtmlParagraph("This is a simple dialog page."));
        Panel footerPanel = add(new Panel(PanelType.PADDED));
        HtmlButton ok = footerPanel.add(new HtmlButton("Ok"));
        HtmlButton cancel = footerPanel.add(new HtmlButton("Cancel"));
        ok.onClick(new ClickEventHandler(
                M.ClosePage(V.Ok)
        ));
        cancel.onClick(new ClickEventHandler(
                M.ClosePage(V.Cancel)
        ));
        footerPanel.add(HtmlButton.openPage(ButtonType.INFO, "Open Another Dialog", DialogOnTopOfAnotherDialogExample.class));
    }
}
