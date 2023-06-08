package com.philips.dmis.swt.ui.template;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class TemplateDemoPage extends Page {
    public TemplateDemoPage() throws Exception {
        super(Constants.isDemo(TemplateDemoPage.class));
    }

    @Override
    protected void build() throws Exception {
        IconsWidget icons = add(new IconsWidget("MaterialSymbolsSharp.woff2"));


        add(new HtmlHeading("Template Demo"));

        add(new HtmlParagraph("The toolkit supports templates. To keep things simple, " +
                "any widget can be used as a template. We can use the M.CloneWidget statement to create " +
                "a new widget 'instance'."));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "M.CloneWidget(template, target, V.Random())"));

        add(new HtmlParagraph("Calling this method will take a widget called 'template', make a copy and " +
                "add it to another widget called 'target'. The last argument generates a random data-key."));

        add(new HtmlParagraph("Each instance is not a true, standalone copy. " +
                "The code that is added to the template is re-used by the instances. The reason is that this is " +
                "much more efficient."));

        add(new HtmlParagraph("Data binding works in a similar way. When the template (or a widget inside " +
                "of the template) is data bound, " +
                "then every instance will be updated in the same way, but only when they exist at the time the " +
                "data source refreshes. Ensure that instances are 'generated' before " +
                "data sources refresh or call M.Refresh manually."));

        StaticData staticData = add(new StaticData(
                DataBuilder.values()
                        .add("Apples")
                        .add("Bananas")
                        .add("Kiwis")
                        .getData()
        ));

        Panel template = add(new Panel(PanelType.PADDED));
        template.setName("template");
        HtmlTextInput text1 = template.add(new HtmlTextInput());
        text1.setName("someTextField");
        HtmlSelect select1 = template.add(new HtmlSelect());
        select1.setName("someList");
        select1.addDataSource(staticData);
        HtmlButton btn1 = template.add(new HtmlButton("Echo"));
        btn1.onClick(new ClickEventHandler(
                M.Alert(V.GetValue(text1))
        ));
        HtmlButton btn2 = template.add(new HtmlButton("Who am I?"));
        btn2.onClick(new ClickEventHandler(
                M.SetValue(text1, V.GetDataKey())
        ));
        add(new HtmlLabel(icons, "info", "In this demo the template itself is visible " +
                "but you should probably hide the template. Instances will always be visible.").setAppearance(WidgetAppearance.BLOCK));

        HtmlButton copy = add(new HtmlButton("Create new instance"));
        Panel target = add(new Panel(PanelType.BANNER));
        target.setName("panel");
        target.add(new HtmlLabel("This is the target panel"));
        copy.onClick(new ClickEventHandler(
                M.CloneWidget(template, target, V.Random()), // just generate a random dataKey for this demo
                M.Refresh(staticData)
        ));

        add(new HtmlParagraph("Create some instances, fill in some data and click the button below to test " +
                "what the data retrieved from the panel looks like."));

        HtmlButton getDataButton = add(new HtmlButton("Retrieve data from the above panel"));
        HtmlPreformatted data = add(new HtmlPreformatted(TextFormatType.JSON));
        getDataButton.onClick(new ClickEventHandler(
                M.SetText(data, V.GetValue(target))
        ));

        add(new HtmlParagraph("The toolkit will use the data-key to differentiate between instances. " +
                "Note that in this case the data-key is a random number but this could also correspond to " +
                "a record id in a database. Click the 'Who am I' button to reveal the data-key. This is done using " +
                "V.GetDataKey:"));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "M.SetValue(textBox, V.GetDataKey())"));

        add(new HtmlParagraph("Removing an instance is easy but you need the data-key. To do it, use the " +
                "M.RemoveClone method on the template widget."));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "M.RemoveClone(template, 'data-key')"));

        Panel p = add(new Panel(PanelType.PADDED));
        HtmlTextInput dataKeyText = p.add(new HtmlTextInput());
        HtmlButton removeBtn = p.add(new HtmlButton("Remove"));
        removeBtn.setEnabled(false);
        dataKeyText.onInput(new InputEventHandler(
                M.SetDisabled(removeBtn, V.IsEmpty(V.GetValue(dataKeyText)))
        ));
        removeBtn.onClick(new ClickEventHandler(
                M.RemoveClone(template, V.GetValue(dataKeyText))
        ));
    }
}
