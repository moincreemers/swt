package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.data.DtoViewDataAdapter;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.data.PathDataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataBindingExample extends Page {
    public static class Address {
        String streetAddress;
        String postalCode;
        String city;
        String state;
        String country;
        String otherCountry;

        public Address() {
            // for serialization only
        }

        public Address(String streetAddress, String postalCode, String city, String state, String country, String otherCountry) {
            this.streetAddress = streetAddress;
            this.postalCode = postalCode;
            this.city = city;
            this.state = state;
            this.country = country;
            this.otherCountry = otherCountry;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getOtherCountry() {
            return otherCountry;
        }

        public void setOtherCountry(String otherCountry) {
            this.otherCountry = otherCountry;
        }
    }

    public DataBindingExample() throws Exception {
    }

    @Override
    protected void build() throws Exception {
        IconsWidget icons = add(new IconsWidget("MaterialSymbolsSharp.woff2"));
        add(HtmlLink.closePage("Back to Examples"));
        add(new HtmlHeading("Data Binding"));


        add(new HtmlHeading("Creating Data Sources", 2));
        add(new HtmlParagraph("This page includes a Data Source for the options of the Country ListBox:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "Data countryOptions = new Data(Arrays.asList(\"United States\", " +
                "\"Netherlands\", \"United Kingdom\", \"Japan\", \"Other\"));"));
        StaticData countryOptions = add(new StaticData(Arrays.asList("United States", "Netherlands", "United Kingdom", "Japan",
                "Other")));
        add(new HtmlParagraph("... and a Data Source for the Address record. Let&#39;s say we implemented the Address " +
                "class. Presumably, this is already part of the Address Service we would like to use. So we could add " +
                "a Java dependency or define it again in our Web application project here:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "public class Address implements Serializable {\n" +
                "  String streetAddress;\n" +
                "  String postalCode;\n" +
                "  String city;\n" +
                "  String state;\n" +
                "  String country;\n" +
                "  String otherCountry;\n\n" +
                "  (some code omitted...) \n\n" +
                "}"));
        add(new HtmlParagraph("We will now create a Data Source with one Address instance:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                "Address address = new Address(\"704 Hauser St.\",\"10001\",\"New York\",\"New York\"," +
                        "\"United States\",\"\");\n" +
                        "Data data = new Data(address);"));
        StaticData staticData = add(new StaticData(new Address("704 Hauser St.", "10001", "New York",
                "New York", "United States", "")));
        add(new HtmlParagraph("The Data Sources in this example are static but these would each be replaced by a " +
                "QueryService (see below) to retrieve the data from a service."));

        //
        //
        //

        add(new HtmlHeading("Creating a Form", 2));
        add(new HtmlParagraph("At this point we have added two Data Sources, " +
                "one for the OPTIONS of the Country ListBox, the other for the VALUES of the form. " +
                "We can now bind the Data Sources to widgets as seen below."));
        add(new Panel(PanelType.INFO, new HtmlLabel(icons, "info",
                "A Data Source can be bound to any number of widgets. When the Data Source changes, " +
                        "each widget that is bound to it will receive an update notification and its " +
                        "value will be automatically updated.")));
        Grid addressGrid = add(new Grid("address", 3));
        HtmlPreformatted streetAddressCode = new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "TextBox streetAddress = new TextBox()\n" +
                "  .addDataSource(data, new PathDataAdapter(\"streetAddress\"));");
        HtmlTextInput streetAddress = new HtmlTextInput("streetAddress")
                .addDataSource(staticData, new PathDataAdapter("streetAddress"));
        addressGrid.addAll(new HtmlLabel(streetAddress, "Street Address"), streetAddressCode);
        HtmlPreformatted postalCodeCode = new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "TextBox postalCode = new TextBox()\n" +
                "  .addDataSource(data, new PathDataAdapter(\"postalCode\"));");
        HtmlTextInput postalCode = new HtmlTextInput("postalCode")
                .addDataSource(staticData, new PathDataAdapter("postalCode"));
        addressGrid.addAll(new HtmlLabel(postalCode, "Postal Code"), postalCodeCode);
        HtmlPreformatted cityCode = new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "TextBox city = new TextBox()\n" +
                "  .addDataSource(data, new PathDataAdapter(\"city\"));");
        HtmlTextInput city = new HtmlTextInput("city")
                .addDataSource(staticData, new PathDataAdapter("city"));
        addressGrid.addAll(new HtmlLabel(city, "City"), cityCode);
        HtmlPreformatted stateCode = new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "TextBox state = new TextBox()\n" +
                "  .addDataSource(data, new PathDataAdapter(\"state\"));");
        HtmlTextInput state = new HtmlTextInput("state")
                .addDataSource(staticData, new PathDataAdapter("state"));
        addressGrid.addAll(new HtmlLabel(state, "State"), stateCode);
        HtmlPreformatted countryCode = new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                "ListBox country = new ListBox()\n" +
                        "  .addDataSource(DataSourceUsage.VALUE, data, new PathDataAdapter(\"country\"))\n" +
                        "  .addDataSource(DataSourceUsage.OPTIONS, countryOptions, new KeyValueListDataAdapter());");
        HtmlSelect country = new HtmlSelect("country")
                .addDataSource(DataSourceUsage.VALUE, staticData, new PathDataAdapter("country"))
                .addDataSource(DataSourceUsage.OPTIONS, countryOptions, new KeyValueListDataAdapter());
        addressGrid.addAll(new HtmlLabel(country, "Country"), countryCode);
        HtmlPreformatted otherCountryCode = new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "TextBox otherCountry = new TextBox()\n" +
                "  .addDataSource(data, new PathDataAdapter(\"otherCountry\"));\n" +
                "    otherCountry.setEnabled(false);\n" +
                "country.onChange(new ChangeEventHandler(\n" +
                "  M.If(country)\n" +
                "    .Equals(V.Const(\"Other\"),\n" +
                "      M.Enable(otherCountry),\n" +
                "      M.Focus(otherCountry))\n" +
                "    .Else(M.Disable(otherCountry))\n" +
                "  ));");
        HtmlTextInput otherCountry = new HtmlTextInput("otherCountry")
                .addDataSource(staticData, new PathDataAdapter("otherCountry"));
        otherCountry.setDisabled(true);
        country.onChange(new ChangeEventHandler(
                M.If(country)
                        .Equals(V.Const("Other"),
                                M.SetEnabled(otherCountry),
                                M.Focus(otherCountry))
                        .Else(M.SetDisabled(otherCountry))
        ));
        addressGrid.addAll(new HtmlLabel(otherCountry, "Other Country"), otherCountryCode);

        //
        //
        //

        add(new HtmlHeading("Working With Data", 2));
        add(new HtmlParagraph("Getting the data from the form is easy. Click the button to do it."));
        HtmlButton getFormData = add(new HtmlButton("Get Form Data"));
        HtmlPreformatted formData = add(new HtmlPreformatted(TextFormatType.JSON));
        getFormData.onClick(new ClickEventHandler(
                M.SetText(formData, V.GetValue(this))
        ));
        add(new HtmlParagraph("The reason this works is because the toolkit works with a hierarchy of widgets. " +
                "We can ask any widget to return whatever data it contains. In this case we ask the Page to " +
                "return it&#39;s data."));
        add(new Panel(PanelType.INFO, new HtmlLabel(icons, "info",
                "It is important to note that Container widgets (e.g., Page, Grid, Panel, Table, List) will " +
                        "translate their hierarchy to the JSON document when requesting its value." +
                        "For this reason, containers also have a Name property and everything with a Name is considered " +
                        "data.")));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "getFormData.onClick(new ClickEventHandler(\n" +
                "  M.SetText(formData, V.GetValue(this))\n" +
                "));"));
        add(new HtmlParagraph("You can also use an event handler to retrieve the data:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "grid.onChange(new ChangeEventHandler(\n" +
                "  M.SetText(formData, V.GetValue(grid))\n" +
                "));"));
        HtmlPreformatted formData2 = add(new HtmlPreformatted(TextFormatType.JSON, ""));
        addressGrid.onChange(new ChangeEventHandler(
                M.SetText(formData2, V.GetValue(addressGrid))
        ));
        add(new HtmlParagraph("The reason this works is because the onChange event is propagated to the parent, " +
                "which in this case is the Grid."));


        //
        //
        //

        add(new HtmlHeading("Sending Data to a Service", 2));
        add(new HtmlParagraph("Obviously, in most cases it is the intention to send this data to a service. " +
                "This is done using the UpdateService widget."));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "UpdateService addressUpdateService = new UpdateService(\"/address\", " +
                "Address.class);"));
        add(new HtmlParagraph("The UpdateService requires the endpoint of the API that will handle the " +
                "update and a DTO. The DTO is an object that is representative of the data, in this case " +
                "our Address class which we defined earlier.\n\n" +
                "Simply use M.Set to tell the UpdateService to send the data:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "grid.onChange(new ChangeEventHandler(\n" +
                "  M.Set(addressUpdateService, V.Get(grid))\n" +
                "));"));
        add(new HtmlParagraph("Alternatively, you could add a \"Save\" button if you prefer."));

        //
        //
        //

        add(new HtmlHeading("Querying Data from a Service", 2));
        add(new HtmlParagraph("To query our address service we need a Data Source. This is the QueryService:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS, "QueryService addressQueryService = new QueryService(\"/address\");"));
        add(new HtmlParagraph("The expectation is that the Address Service will return a JSON document with an array of Address records, for example:"));
        add(new HtmlPreformatted(TextFormatType.JSON,
                "{\"items\":[" +
                        "{\"streetAddress\":\"704 Hauser St.\",\"postalCode\":\"10001\",\"city\":\"New York\",\"state\":\"New York\",\"country\":\"United States\",\"otherCountry\":\"\"}," +
                        "{\"streetAddress\":\"698 Candlewood Lane\",\"postalCode\":\"03909\",\"city\":\"Cabot Cove\",\"state\":\"Maine\",\"country\":\"United States\",\"otherCountry\":\"\"}," +
                        "{\"streetAddress\":\"221B Baker St.\",\"postalCode\":\"\",\"city\":\"London\",\"state\":\"\",\"country\":\"United Kingdom\",\"otherCountry\":\"\"}" +
                        "]}"));
        add(new Panel(PanelType.INFO, new HtmlLabel(icons, "info",
                "It is important to note that the JSON document can be very different from this. The only assumption is that the JSON document contains an array with these records somewhere and that it is preceded by a property name. The path to this property needs to be specified in Data Adapters. Note that \".items\" is the default path.")));

        StaticData addressService = add(new StaticData(Arrays.asList(
                new Address("704 Hauser St.", "10001", "New York", "New York", "United States", ""),
                new Address("698 Candlewood Lane", "03909", "Cabot Cove", "Maine", "United States", ""),
                new Address("221B Baker St.", "", "London", "", "United Kingdom", ""),
                new Address("1600 Pennsylvania Avenue", "", "Washington", "Washington D.C.", "United States", ""),
                new Address("350 Fifth Avenue", "10118", "New York", "New York", "United States", "")
        )));
        addressService.addDataAdapter(new DtoViewDataAdapter(Address.class));

        add(new HtmlParagraph("To display multiple records we use a Table:"));
        add(new HtmlPreformatted(TextFormatType.JAVA_AND_JS,
                "Table addressTable = new Table();" +
                        "TableHeader tableHeader = addressTable.add(new TableHeader());" +
                        "tableHeader.addDataSource(addressService);" +
                        "TableBody tableBody = addressTable.add(new TableBody());" +
                        "tableBody.addDataSource(addressService);"));
        HtmlTable addressHtmlTable = add(new HtmlTable());

        HtmlTableHeader htmlTableHeader = addressHtmlTable.add(new HtmlTableHeader());
        htmlTableHeader.addDataSource(addressService);

        HtmlTableBody htmlTableBody = addressHtmlTable.add(new HtmlTableBody());
        htmlTableBody.addDataSource(addressService);
    }
}
