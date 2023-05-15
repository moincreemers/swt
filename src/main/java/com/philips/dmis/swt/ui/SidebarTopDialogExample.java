package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class SidebarTopDialogExample extends Page {
    public SidebarTopDialogExample() throws Exception {
        super(ViewType.SIDEBAR_DIALOG, ViewPosition.SIDEBAR_TOP);
    }

    @Override
    protected void build() throws Exception {
        add(new HtmlHeading("Sidebar dialog (" + getId() + ")"));

        add(new HtmlParagraph("This is a simple sidebar dialog page."));

        add(HtmlLink.openPage("Back to Examples", ExamplesPage.class));

        add(HtmlButton.closePage("Close", V.Ok));
    }
}
