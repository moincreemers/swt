package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class SpinnerExample extends Page {
    public SpinnerExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Spinner"));

        add(new HtmlParagraph("Sometimes its useful to provide feedback to the user that something takes some time. " +
                "For this you can use the M.ShowProgress and M.HideProgress statements."));

        HtmlButton showButton = add(new HtmlButton("Show spinner"));
        HtmlButton hideButton = add(new HtmlButton("Hide spinner"));

        Panel panel = add(new Panel(PanelType.BANNER));

        showButton.onClick(new ClickEventHandler(
                M.ShowProgress(panel)
        ));
        hideButton.onClick(new ClickEventHandler(
                M.HideProgress(panel)
        ));

    }
}