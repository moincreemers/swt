package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEvent;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class DialogKeyPressEventExample extends Page {
    public DialogKeyPressEventExample() throws Exception {
        super(ViewType.DIALOG, ViewPosition.DIALOG_BOTTOM_RIGHT);
    }

    @Override
    protected void build() throws Exception {
        Panel headingPanel = add(new Panel(PanelType.ERROR));
        headingPanel.add(new HtmlHeading("Dialog (" + getId() + ")"));

        Panel bodyPanel = add(new Panel(PanelType.PADDED));
        bodyPanel.add(new HtmlParagraph("In this dialog we subscribe to the same KeyPress event."));

        HtmlTextInput htmlTextInput = bodyPanel.add(new HtmlTextInput());
        onKeyPress(new KeyPressEventHandler(
                M.SetValue(htmlTextInput, V.GetEvent(KeyPressEvent.KEY))
        ));

        Panel footerPanel = add(new Panel(PanelType.PADDED));
        HtmlButton ok = footerPanel.add(new HtmlButton("Close"));
        ok.onClick(new ClickEventHandler(
                M.ClosePage(V.Ok)
        ));
    }
}
