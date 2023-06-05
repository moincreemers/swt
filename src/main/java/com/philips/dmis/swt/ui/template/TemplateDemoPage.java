package com.philips.dmis.swt.ui.template;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class TemplateDemoPage extends Page {
    public TemplateDemoPage() throws Exception {
        super(Constants.isDemo(TemplateDemoPage.class));
    }

    @Override
    protected void build() throws Exception {
        add(new HtmlHeading("Template Demo"));

        HtmlButton template = add(new HtmlButton("Hello World!"));
        Panel target = add(new Panel(PanelType.SUCCESS));
        HtmlButton copy = add(new HtmlButton("Copy"));
        copy.onClick(new ClickEventHandler(
                M.Generate(template, target)
        ));
    }
}
