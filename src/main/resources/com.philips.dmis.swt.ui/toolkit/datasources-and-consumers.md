# Data Sources and Consumers

## Data Source

A data source is a service that produces data. How it does that varies. There are several implementations:

- StaticData
- QueryService
- CalculatedWidget
- Data

The toolkit implements all data source types as a widget that is added to a page so other elements on the page can
interact with that data source.

## Data Consumer

A data consumer is defined as a service that consumes data. Depending on the type of service the implementations vary
but there are two categories:

1. Data Bound Widgets:
    1. HtmlList
    2. DataProxy
    3. HtmlButton
    4. HtmlTableBody
    5. HtmlLabel
    6. HtmlPreformatted
    7. DataTemplateWidget
    8. HtmlTableFooter
    9. DataConsumerWidget
    10. HtmlParagraph
    11. HtmlImage
    12. HtmlTableHeader
    13. HtmlDataList
    14. HtmlMeter
    15. HtmlHeading
    16. Caption
    17. HtmlProgress
    18. HtmlLink

2. Value Widgets:
    1. MultipleChoice
    2. HtmlPasswordInput
    3. HtmlTelephoneInput
    4. HtmlColor
    5. HtmlCheckInput
    6. HtmlURLInput
    7. HtmlImageButton
    8. HtmlNumberInput
    9. HtmlSearchInput
    10. HtmlMonthInput
    11. HtmlRangeInput
    12. HtmlDateTimeInput
    13. HtmlSelect
    14. HtmlWeekInput
    15. SingleChoice
    16. HtmlTimeInput
    17. HtmlFrame
    18. HtmlEmailInput
    19. HtmlDateInput
    20. HtmlFile
    21. HtmlTextInput

All data consumers in the toolkit are implemented as a Widget.

A value widget is a subset of the data bound widget and is special because it interprets the data that it receives as a
value which can be edited. For example a text field.

Non-value widgets therefore, do not allow editing.

There are also data consumers that are not visible at all. These are:

- DataProxy
- DataConsumerWidget

The data proxy is both a data consumer and a data source:

```puml
participant "Data source" as S
participant "Data proxy" as P
participant "Data consumers" as C

S->P: Update
P->P: Data Adapter: Transform
P->P: Serialize data
P->P: Refresh
P->P: Deserialize
P->P: Process
P->P: Data Adapters: Import, View, Transform (self)
P->P: Notify
P->C: Update



```

A data proxy can be useful because the data proxy can apply data adapter(s) that modify the data before data consumers
are updated. Additionally, all data sources use a cache.

## Flow

The interaction follows a specific flow and is invoked in a specific way.

### Invocation

The toolkit defines the start of a data source flow as: _Refresh_. All data sources are refreshed when a page is
_Initialized_. This is the default configuration. However, a data source can be configured to not refresh at this time.
In that case, a manual refresh is needed to start the flow. This is done using the <code>M.Refresh</code> method.

### Flow

```puml
participant "Page" as P
participant "Statement" as M
participant "Data source" as S
participant "Data consumers" as C

opt Page initialization
P->S: Refresh
end
alt Manual refresh
M->S: Refresh
end
S->C: Update
```

## Subscibers

Data consumers subscribe to a data source when the addDataSource method is called:

- <code>HasDataSource.addDataSource(DataSource)</code>
- <code>HasDataSource.addDataSource(HasDataSourceUsage, DataSourceSupplier, DataAdapter...)</code>

### Data Source Usage

The data source usage parameter specifies how the data should be applied to the data consumer:

* PROCEDURAL
* LIST_ITEMS_TEMPLATE
* OPTIONS
* LIST_ITEMS
* TABLE_HEADER
* TABLE_FOOTER
* TABLE_BODY
* TEXT
* VALUE

These do not all apply to all data consumers. Some data consumers such as a text field for example cannot use the
OPTIONS usage.

Technically it is possible to create a DataSource instance with a different data source usage and add it to a
data source. The result of doing this is undefined but will very likely result in errors.

### Unions

Some data consumers can have two data adapters with a different usage, for example the HtmlSelect widget. One data
source is used to set the available OPTIONS, the second to set the VALUE.

Data adapters are also invoked in the order that they are listed here.

It is allowed to specify more than one data adapter with the same usage, but this only applies to:

* PROCEDURAL
* LIST_ITEMS_TEMPLATE
* OPTIONS
* LIST_ITEMS

This can be used to combine data from two data sources into the same data consumer. For example using an HtmlSelect
widget, we could combine options from two services. The toolkit will keep track of the origin of each option so that it
is also possible to refresh each data source separately.

### Special usage options

When a data adapter is added to a data source, there are two usages available:

* IMPORT
* TRANSFORM

Technically it is possible to manually create a DataSource instance with a different data source usage and add it to a
data source. But this data adapter would never be invoked.

## Data Adapters

The toolkit comes with the following data adapters by default:

### Import

* AggregateDataAdapter
* ImportArrayDataAdapter
* ImportObjectDataAdapter

### Transform

* ExtractDataAdapter

### Value

* FilterDataAdapter
* FlattenArrayDataAdapter
* GroupByDataAdapter

* MapDataAdapter
* MetaDataAdapter
* ModifySchemaAdapter
* PropertyNameDataAdapter
* InteractiveOrderingDataAdapter

* KeyValueListDataAdapter
* ValueDataAdapter

### Special data adapters

* DebugDataAdapter
* DtoViewDataAdapter
* KeyValueViewDataAdapter