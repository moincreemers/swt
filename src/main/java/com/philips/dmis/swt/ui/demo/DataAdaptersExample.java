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

        add(new HtmlParagraph("""
                The toolkit provides several strategies to handle data.
                                
                There are Data Sources (any subclass of <code>DataSourceWidget</code>) and Data Consumers
                (any subclass of <code>DataBoundWidget</code>). There is also a widget that is both:
                <code>DataProxy</code>.
                                
                Data Adapters are used on both Data Sources and Data Consumers for different reasons.
                                
                For Data Sources:
                <ul>
                    <li>IMPORT</li>
                    <li>VIEW</li>
                    <li>TRANSFORM</li>
                </ul>
                
                And for Data Consumers:
                <ul>
                    <li>PROCEDURAL</li>
                    <li>TRANSFORM</li>
                    <li>VIEW</li>
                    <li>ITEMS</li>
                    <li>VALUE</li>
                </ul>
                
                Data Adapters implement only one of these strategies. The default strategy which applies to most Data
                Adapters is TRANSFORM.
                """));

        add(new HtmlHeading("Importing data", 2));

        add(new HtmlParagraph("""
                For the toolkit to work with any data, it has to conform to a schema.
                This is defined in the <code>ServiceResponse</code> data type. Whether or not a service returns data
                that conforms to the <code>ServiceResponse</code> schema should be provided when creating a Data Source
                widget:
                <pre>
                QueryService queryService = new QueryService(String url, boolean expectServiceResponse, boolean autoRefresh);
                </pre>
                Set <code>expectServiceResponse = false</code> when the data returned by the service does not conform to the schema.
                """));

        add(new HtmlParagraph("""
                When the data does not conform to the schema, the toolkit expects you to provide a data adapter
                that is capable of transforming the data to the <code>ServiceResponse</code> schema. The data adapter is
                used to IMPORT the data.
                """));

        add(new HtmlParagraph("""
                The toolkit also requires that a View is created that corresponds to the data. This is done
                automatically by the data adapter that performs the IMPORT but if the data returned by the service
                conforms to the schema then no IMPORT is needed. It is possible that the service already
                returns a view as part of the <code>ServiceResponse</code>. However, in case you choose not to do that,
                a view can be created using a separate data adapter.
                
                For example:
                <pre>
                QueryService queryService = add(new QueryService("/employeeService"));
                static class EmployeeDto {
                    public String id;
                    public String displayName;
                }
                queryService.addDataAdapter(new DtoViewDataAdapter(EmployeeDto.class));
                </pre>
                """));

        add(new HtmlHeading("Transforming data", 2));

        add(new HtmlParagraph("""
                After data has been imported, another type of data adapter can be used to transform the data.
                Transformation of data can sometimes be necessary, for example to calculate aggregates, group data or
                sort data.
                """));

        add(new HtmlHeading("Aggregation", 3));

        add(new HtmlParagraph("""
                The <code>AggregateDataAdapter</code> allows aggregation of zero or more fields in a record set. Note
                that the aggregation functions only work on numerical values.
                <pre>
                AggregateDataAdapter aggregateDataAdapter = new AggregateDataAdapter()
                    .addAggregate("Hours", "hours", null, A.Sum());
                </pre>
                """));

        add(new HtmlHeading("Grouping", 3));

        add(new HtmlParagraph("""
                The <code>GroupByDataAdapter</code> takes a GroupBy field (one is currently supported) and concatenates
                the records in a data set such that each distinct value in that field is present once at most. It also
                takes zero or more Aggregate fields that can be displayed. However, aggregates only work on numerical
                values. The output is a record set that contains the GroupBy field and any Aggregate fields.
                <pre>
                GroupByDataAdapter groupByDataAdapter = new GroupByDataAdapter()
                    .addGroupBy("Date", "date", dateTimeFormat, JsType.DATE)
                    .addAggregate("Hours", "hours", null, R.Sum());
                </pre>
                """));

        add(new HtmlHeading("Sorting", 3));

        add(new HtmlParagraph("""
                The <code>InteractiveOrderingDataAdapter</code> sorts records in a data set according to any widget
                that implements <code>HasOrderingControls</code> which is <code>HtmlTableHeader</code>.
                """));

        add(new HtmlHeading("Filtering", 3));

        add(new HtmlParagraph("""
                The <code>FilterDataAdapter</code> is used to filter the recordset. For example:
                <pre>
                HtmlWeekInput htmlWeekInput = add(new HtmlWeekInput());
                FilterDataAdapter filterPeriodDataAdapter = new FilterDataAdapter()
                        .addPredicate("date",
                            P.IsGreaterThanOrEqual(V.DateValue(V.WeekOfYearToDate(V.GetValue(htmlWeekInput)))));
                        .addPredicate("date",
                            P.IsLessThan(V.DateValue(V.ModifyDate(V.WeekOfYearToDate(V.GetValue(htmlWeekInput)), V.OneWeek))));
                </pre>
                """));

        add(new HtmlHeading("Consuming data", 2));

        add(new HtmlHeading("Text", 3));

        add(new HtmlParagraph("""
                Some widgets can be data bound to a Data Source but can only use the data as text that they can display.
                Examples are:
                <ul>
                    <li>Caption</li>
                    <li>HtmlButton</li>
                    <li>HtmlHeading</li>
                    <li>HtmlLabel</li>
                    <li>HtmlLink</li>
                    <li>HtmlParagraph</li>
                    <li>HtmlPreformatted</li>
                </ul>
                                
                To bind one of these widgets to a Data Source, we use the <code>addDataSource</code> method:
                <pre>
                HtmlButton button = add(new HtmlButton());
                button.addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters);
                </pre>
                                
                The <code>dataSourceSupplier</code> is any Data Source widget.
                Then as a second argument, a Data Adapter instance is needed to that provides a 'path' to the
                field or property that we want to use as the text value. The Data Adapter to use in this case is the
                <code>ValueDataAdapter</code>:
                <pre>
                ValueDataAdapter valueDataAdapter = new ValueDataAdapter("some field name");
                button.addDataSource(dataSource, valueDataAdapter);
                </pre>
                """));

        add(new HtmlHeading("Value", 3));

        add(new HtmlParagraph("""
                Almost all of the other widgets that can be data bound to a Data Source are subclasses of
                <code>ValueWidget</code>. Most of these widgets allow editing of the value.
                Examples are:
                <ul>
                <li>HtmlCheckInput</li>
                <li>HtmlColor</li>
                <li>HtmlDateInput</li>
                <li>HtmlDateTimeInput</li>
                <li>HtmlEmailInput</li>
                <li>HtmlFile</li>
                <li>HtmlFrame</li>
                <li>HtmlImageButton</li>
                <li>HtmlMonthInput</li>
                <li>HtmlNumberInput</li>
                <li>HtmlPasswordInput</li>
                <li>HtmlRangeInput</li>
                <li>HtmlSearchInput</li>
                <li>HtmlSelect</li>
                <li>HtmlTelephoneInput</li>
                <li>HtmlTextInput</li>
                <li>HtmlTimeInput</li>
                <li>HtmlURLInput</li>
                <li>HtmlWeekInput</li>
                <li>MultipleChoice</li>
                <li>SingleChoice</li>
                </ul>
                                
                To bind one of these widgets to a Data Source, we use the <code>addDataSource</code> method:
                <pre>
                HtmlTextInput textBox = add(new HtmlTextInput());
                textBox.addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters);
                </pre>
                                
                The <code>dataSourceSupplier</code> is any Data Source widget.
                The second argument is a Data Adapter instance which is needed to provide a 'path' to the
                field or property that we want to use as the value. The Data Adapter to use in this case is the
                <code>ValueDataAdapter</code>:
                <pre>
                ValueDataAdapter valueDataAdapter = new ValueDataAdapter("some field name");
                button.addDataSource(dataSource, valueDataAdapter);
                </pre>
                """));

        add(new HtmlHeading("Items", 3));

        add(new HtmlParagraph("""
                There are a few widgets that provide options for the use to choose from, examples are:
                <ul>
                    <li>HtmlSelect</li>
                    <li>MultipleChoice</li>
                    <li>SingleChoice</li>
                    <li>HtmlDataList</li>
                </ul>
                                
                To bind one of these widgets to a Data Source, we use the <code>addDataSource</code> method:
                <pre>
                HtmlSelect dropDownList = add(new HtmlSelect());
                dropDownList.addDataSource(ValueAndItemsDataSourceUsage dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters);
                </pre>
                or:
                <pre>
                HtmlDataList dataList = add(new HtmlDataList());
                dataList.addDataSource(ItemsDataSourceUsage dataSourceUsage, DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters);
                </pre>
                                
                In this case, we need to tell the toolkit how to use this Data Source. If the widget supports both a
                value and items, we need to pass a <code>DataSourceUsage</code> value. This would be the argument:
                <code>ValueAndItemsDataSourceUsage.ITEMS</code>.
                Note that for these widgets you would typically need to add two data sources with their own data
                adapters.
                                
                If the widget just supports items, which is only the case for the HtmlDataList widget, we use the
                default addDataSource method that uses <code>ItemsDataSourceUsage.ITEMS</code> internally.
                                
                The <code>dataSourceSupplier</code> is any Data Source widget.
                                
                As the third argument, a Data Adapter instance is needed that provides a KEY and VALUE for the
                items. The Data Adapter to use in this case is the <code>KeyValueListDataAdapter</code>:
                <pre>
                KeyValueListDataAdapter keyValueListDataAdapter = new KeyValueListDataAdapter("countryCode", "country");
                dropDownList.addDataSource(ValueAndItemsDataSourceUsage.ITEMS, dataSource, keyValueListDataAdapter);
                </pre>
                                
                Note that if you use a <code>StaticData</code> widget as the Data Source and you provide the data using
                the DataBuilder class then you can use the default KeyValueListDataAdapter constructor, for example:
                <pre>
                StaticData staticData = add(new StaticData(
                    DataBuilder.keyValue().
                        .add("a", "Apple")
                        .add("a", "Pear")
                        .add("a", "Banana")
                        .getData()
                ));
                HtmlSelect selectFruit = add(new HtmlSelect());
                selectFruit.addDatasource(ValueAndItemsDataSourceUsage.ITEMS, staticData,
                    new KeyValueListDataAdapter());
                </pre>
                """));

        add(new HtmlHeading("Procedural", 3));

        add(new HtmlParagraph("""
                Procedural simply instructs the toolkit to stop processing the data using data binding. We can then use
                the <code>onUpdate</code> method of the Data Consumer widget to handle the data. This also works on
                other Data Consumer widgets.
                                
                For example:
                <pre>
                DataConsumerWidget dataConsumer = add(new DataConsumerWidget());
                dataConsumer.addDataSource(someDataSource);
                dataConsumer.onUpdate(new UpdateEventHandler(
                    // loop through each record
                    M.ForEach(UpdateEvent.Items()).Apply(...)
                ));
                </pre>
                Note that the default usage for a <code>DataConsumerWidget</code> is Procedural so it is not specified
                in the example above.
                """));

        add(new HtmlHeading("Disabling Data Adapters", 2));

        add(new HtmlParagraph("""
                A powerful feature of data adapters is the ability to disable and re-enable any data adapter. This can
                be achieved using the <code>M.SetDataAdapterDisabled()</code> or <code>M.SetDataAdapterEnabled()</code>
                methods.
                                
                Disabling a data adapter can be used to switch between a regular view of a set of records and a grouped
                view for example.
                """));


        add(new HtmlHeading("Other Data Adapters", 2));

        add(new HtmlHeading("ExtractDataAdapter", 3));

        add(new HtmlParagraph("""
                This data adapter can be used to execute a number of statements on any number of fields of every record
                in a set of records.
                <pre>
                ExtractDataAdapter extractDataAdapter = new ExtractDataAdapter()
                    .map("some field", ...)
                    .map("some other field", ...);
                </pre>
                """));

        add(new HtmlHeading("FlattenArrayDataAdapter", 3));

        add(new HtmlParagraph("""
                This data adapter flattens a set of records that contains a field that contains an array effectively
                resulting in an <i>inner join</i>.
                                
                Note that any record identifying fields in the original data set may no longer be unique as a
                consequence of this transformation.
                """));
    }
}
