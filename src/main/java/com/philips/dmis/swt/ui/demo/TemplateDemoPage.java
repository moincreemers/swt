package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.data.DtoViewDataAdapter;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.data.ValueDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InputEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TemplateDemoPage extends Page {
    static class OrderLine {
        static int counter = 0;
        public int id;
        public String product;
        public int quantity;

        OrderLine(String product, int quantity) {
            id = ++counter;
            this.product = product;
            this.quantity = quantity;
        }
    }


    public TemplateDemoPage() throws Exception {
        super(Constants.isDemo(TemplateDemoPage.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }

        StaticData products = add(new StaticData(
                DataBuilder.values()
                        .add("Apple")
                        .add("Banana")
                        .add("Kiwi")
                        .getData()
        ));

        StaticData records = add(new StaticData(
                Arrays.asList(new OrderLine("Kiwi", 500), new OrderLine("Banana", 4000), new OrderLine("Apple", 2500)), false
        ));
        records.addDataAdapter(new DtoViewDataAdapter(OrderLine.class));

        Panel target = add(new Panel(PanelType.NAV_RIGHT));
        target.setOverflowAndSize(Overflow.FIXED_SIZE, new Size("33%", Size.AUTO));
        target.setBackgroundColor("steelblue");
        target.add(new HtmlHeading("The target container", 4));
        target.setName("panel");

        add(new HtmlHeading("Template Demo"));

        add(new HtmlParagraph("The toolkit supports templates. To keep things simple, " +
                              "any widget can be used as a template. You can use a single widget or use a panel that " +
                              "contains any number of child widgets. Panels can contain other panels that also contain " +
                              "child widgets. There is no real limit to the depth of the hierarchy in the template. " +
                              "However, very large templates can impact performance." +
                              "<br><br>" +
                              "Creating a copy of a template is called 'cloning' and there are two ways to use it:"));

        ListContainer list = add(new ListContainer(ListType.ORDERED));
        list.add(new HtmlParagraph("Manual Cloning"));
        list.add(new HtmlParagraph("Cloning using Data Binding"));

        add(new HtmlHeading("The template", 2));

        add(new HtmlParagraph("In this demo we will use a panel that represents an order line. " +
                              "The order line has an id field, a product field and a quantity field. " +
                              "The id field is readonly and the product field is a list containing the available " +
                              "options which means there is a data source that contains the options for this list."));

        add(new HtmlParagraph("Each field is bound to a data source and has a name:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                """
                        idLabel.addDataSource(records, new ValueDataAdapter("id"));
                        
                        HtmlSelect product = template.add(new HtmlSelect());
                        product.setName("product");
                        product.addDataSource(ValueAndOptionsDataSourceUsage.OPTIONS, products);
                        product.addDataSource(ValueAndOptionsDataSourceUsage.VALUE, records, new ValueDataAdapter("product"));            
                        
                        HtmlNumberInput quantity = template.add(new HtmlNumberInput());
                        quantity.setName("quantity");
                        quantity.addDataSource(records, new ValueDataAdapter("quantity"));"""));

        Panel template = add(new Panel());
        template.setName("template");
        HtmlLabel idLabel = template.add(new HtmlLabel());
        idLabel.addDataSource(records, new ValueDataAdapter("id"));
        HtmlSelect product = template.add(new HtmlSelect());
        product.setName("product");
        product.addDataSource(ValueAndItemsDataSourceUsage.ITEMS, products, new KeyValueListDataAdapter());
        product.addDataSource(ValueAndItemsDataSourceUsage.VALUE, records, new ValueDataAdapter("product"));
        HtmlNumberInput quantity = template.add(new HtmlNumberInput());
        quantity.setPlaceholder("Quantity");
        quantity.setName("quantity");
        quantity.addDataSource(records, new ValueDataAdapter("quantity"));

        add(new HtmlHeading("Manual Cloning", 2));

        add(new HtmlParagraph("Calling the <code>M.CloneWidget</code> method will take a widget as the template, " +
                              "clone it and add it to a target widget which has to be a container such as a panel." +
                              "The last argument generates a random data-key for this demo."));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "M.CloneWidget(template, target, V.Random())"));

        HtmlButton copy = add(new HtmlButton("Create new instance"));
        copy.onClick(new ClickEventHandler(
                M.CloneWidget(template, target, V.Random()), // just generate a random dataKey for this demo
                M.Refresh(products)
        ));

        add(new HtmlHeading("Getting the data from the clones", 2));

        add(new HtmlParagraph("Because a clone is not a widget that is referencable in code, use " +
                              "<code>V.GetValue</code> on the container."));

        HtmlButton getDataButton = add(new HtmlButton("Retrieve data"));
        HtmlPreformatted data = add(new HtmlPreformatted(TextFormatType.JSON));
        getDataButton.onClick(new ClickEventHandler(
                M.SetText(data, V.GetValue(target))
        ));

        add(new HtmlHeading("Removing a Clone"));

        add(new HtmlParagraph("Removing a clone is easy but you need the data-key. To do it, use the " +
                              "<code>M.RemoveClone</code> method on the template widget."));
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

        add(new HtmlParagraph("Alternatively, you can also remove all clones at once using " +
                              "<code>M.RemoveAllClones</code>."));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "M.RemoveAllClones(template)"));


        HtmlButton removeAll = add(new HtmlButton("Remove all clones"));
        removeAll.onClick(new ClickEventHandler(
                M.RemoveAllClones(template)
        ));


        add(new HtmlHeading("Cloning using Data Binding", 2));

        add(new HtmlParagraph("The second way to use templates is to use data binding to 'generate' clones based " +
                              "on a data source. For this, we need the <code>DataTemplateWidget</code>."));

        add(new HtmlParagraph("The <code>DataTemplateWidget</code> is a " +
                              "special widget that acts as a data consumer because a container widget cannot be bound " +
                              "to a data source."));

        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                """
                        DataTemplateWidget dataTemplateWidget = add(new DataTemplateWidget());
                        dataTemplateWidget.addDataSource(records);
                        dataTemplateWidget.setDefaultTemplateWidget(template);
                        dataTemplateWidget.setDataKeyField("id");
                        dataTemplateWidget.setTemplateTargetWidget(container);
                        
                        // add a second template for when product = 'Apple'
                        dataTemplateWidget.addTemplateWidget(template2, V.Const("Apple"));
                        dataTemplateWidget.setTemplateSelectorField("product");"""));

        add(new HtmlParagraph("The data key field property is important, it should correspond to a field in the " +
                              "data that can be used to identify each record. A record-id for example. " +
                              "Whatever the value, you should make sure the value is unique."));

        Panel template2 = add(new Panel(PanelType.WARNING));
        template2.add(new HtmlParagraph("This is a second template that is selected when the product is 'Apple'"));

        DataTemplateWidget dataTemplateWidget = add(new DataTemplateWidget());
        dataTemplateWidget.addDataSource(records);
        dataTemplateWidget.setDefaultTemplateWidget(template);
        dataTemplateWidget.setDataKeyField("id");
        dataTemplateWidget.setTemplateTargetWidget(target);
        dataTemplateWidget.addTemplateWidget(template2, V.Const("Apple"));
        dataTemplateWidget.setTemplateSelectorField("product");

        HtmlButton refreshRecords = add(new HtmlButton("Refresh data source"));
        refreshRecords.onClick(new ClickEventHandler(
                M.RemoveAllClones(template),
                M.Refresh(records),
                M.Refresh(products)
        ));
        HtmlButton removeAll2 = add(new HtmlButton("Remove all clones from both templates"));
        removeAll2.onClick(new ClickEventHandler(
                M.RemoveAllClones(template),
                M.RemoveAllClones(template2)
        ));
        HtmlButton refreshProducts = add(new HtmlButton("Refresh products"));
        refreshProducts.onClick(new ClickEventHandler(
                M.Refresh(products)
        ));

        HtmlTable table = add(new HtmlTable());
        table.setAppearance(WidgetAppearance.FIT_CONTENT);
        HtmlTableHeader tableHeader = table.add(new HtmlTableHeader());
        tableHeader.addDataSource(records);
        HtmlTableBody tableBody = table.add(new HtmlTableBody());
        tableBody.addDataSource(records);

        add(new HtmlHeading("Additional information", 2));

        add(new HtmlParagraph("It is not recommended to use templates for anything else. For example, " +
                              "events and data binding will not function normally as soon as a Clone exists."));

        add(new HtmlParagraph("The toolkit will use the data-key to differentiate between clones. " +
                              "Note that in this case the data-key is a random number but this could also correspond to " +
                              "a record id in a database. Click the 'Retrieve data' button to reveal the data-key."));

        add(new HtmlParagraph("There is one container in this demo and you can add clones to the same container " +
                              "widget using a data source or manually and using different templates."));

        add(new HtmlParagraph("Note that Data Binding works like it normally does. " +
                              "When the template (or a widget inside of the template) is bound to a data source " +
                              "then every clone will be updated in the same way, but only when they exist at the time " +
                              "the data source refreshes. " +
                              "Ensure that clones are 'generated' before data sources refresh or call the " +
                              "<code>M.Refresh</code> on the data source manually."));

        add(new HtmlParagraph("Each clone is not a complete copy. " +
                              "The events, statements and data sources that are added to the template are re-used by " +
                              "the clones."));

        add(new HtmlParagraph("In this demo the template itself is visible but you should probably hide " +
                              "the template. Clones will automatically be visible regardless."));
    }
}
