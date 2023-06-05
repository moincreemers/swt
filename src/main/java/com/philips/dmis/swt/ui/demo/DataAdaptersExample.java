package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class DataAdaptersExample extends Page {
    public DataAdaptersExample() throws Exception {
        super(Constants.isDemo(DataAdaptersExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Data Adapters"));

//        Data dataConsumersData = add(new Data(Arrays.asList(
//                "Heading", "Paragraph", "Preformatted", "Label", "Button", "Link",
//                "SingleChoice", "MultipleChoice", "TextBox", "Password", "CheckBox",
//                "ListBox", "Frame", "TableHeader", "TableBody", "TableFooter")));

    }
}
