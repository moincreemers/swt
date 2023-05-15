package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class SidebarBottomDialogExample extends Page {
    public SidebarBottomDialogExample() throws Exception {
        super(ViewType.SIDEBAR_DIALOG, ViewPosition.SIDEBAR_BOTTOM);
    }

    @Override
    protected void build() throws Exception {
        add(new HtmlHeading("Sidebar dialog (" + getId() + ")"));

        add(new HtmlParagraph("This is a simple sidebar dialog page."));

        add(HtmlLink.openPage("Back to Examples", ExamplesPage.class));
        add(HtmlButton.closePage("Close", V.Ok));
    }
}
