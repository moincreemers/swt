# Singular UI Toolkit - User Guide

## Getting started

1. Add dependency to your Spring-Boot project
2. Create your first Page and go from there

## Using the Toolkit

### Creating a View

Views are represented by the `Page` widget. To create a new page, create a new class and extend the `
Page` class. For example:

```java
@Component
public class Home extends Page {
    public Home() throws Exception {
        super("home", true);
    }
}
```

You must add the `@Component` attribute (Spring-Boot). This allows the toolkit to add this component to the JS client.

The JS client will consider one page as the default page. That is, the page that is displayed when the user navigates to
the default address which is "/" by default. To mark a page as the default page, pass true in the call to the superclass
constructor as shown in the example.

Note that marking more than one page as the default will work, but the first page that is encountered will become the
default page. Which page is encountered first is determined by Spring-Boot.

### Adding widgets

The `Page` class requires that you implement the `build` method. For example:

```java
@Component
public class HelloWorld extends Page {
    public HelloWorld() throws Exception {
        super("helloWorld");
    }

    @Override
    protected void build() throws Exception {
        Button button1 = add(new Button("Click me!"));
        button1.onClick(new ClickEventHandler(
                Statement.Alert("Hello world!")
        ));
    }
}
```

Note that the `add` method is called to add the htmlButton to the page.

You are also allowed to first create the htmlButton and then call `add(button1);` on a separate line of course.
The API has been designed so that shorthand code can be used like:

```java
add(new Button("Click me!").onClick(
    new ClickHandler(Statement.Alert("Hello world!"))));
```

If you do not add a widget to a container (such as `Page`) but you reference the widget in a statement or
anywhere else, an exception will be thrown when starting the application. The error will point out that this happened,
what type of widget was missing and which reference triggered the error.

<a id="available-widgets" class="caption" style="font-size:small;font-style:italic">Available widgets:</a>

| Widget         | Container | Input   | DataBound | DataSource | DataProvider | Events          | Icon | Text |
|----------------|-----------|---------|-----------|------------|--------------|-----------------|------|------|
| Button         |           |         | yes       |            |              | Click, Update   | yes  | yes  |
| CheckBox       |           | yes     | yes       |            |              | Change, Update  |      |      |
| CodeWidget (3) |           |         |           |            |              |                 |      |      |
| Grid           | yes       |         |           |            |              | Change          |      |      |
| Heading        |           |         | yes       |            |              | Update          | yes  | yes  |
| Label          |           |         | yes       |            |              | Update          | yes  | yes  |
| Link           |           |         | yes       |            |              | Click, Update   | yes  | yes  |
| List           | yes       |         |           |            |              |                 |      |      |
| ListBox        |           | yes     | yes       |            |              | Change, Update  |      |      |
| MultipleChoice |           | yes     | yes       |            |              | Change          |      |      |
| SingleChoice   |           | yes     | yes       |            |              | Change          |      |      |
| Page           | yes       |         |           |            |              | Change          |      |      |
| Panel          | yes       |         |           |            |              | Change          |      |      |
| Paragraph      |           |         | yes       |            |              | Update          |      | yes  |
| Preformatted   |           |         | yes       |            |              | Update          |      | yes  |
| Password       |           | yes     | yes       |            |              | Update          |      |      |
| Table          | yes (2)   |         |           |            |              | Change, Click   |      |      |
| TableBody      |           | yes (1) | yes       |            |              | Update          |      |      |
| TableCaption   | yes       |         |           |            |              | Change          |      |      |
| TableFooter    |           |         | yes       |            |              | Update          |      |      |
| TableHeader    |           |         | yes       |            |              | Update          |      |      |
| TextBox        |           | yes     | yes       |            |              | Change, Update  |      |      |
| Data           |           |         |           | yes        |              |                 |      |      |
| QueryService   |           |         |           | yes        |              |                 |      |      |
| UpdateService  |           |         |           |            | yes          | Response, Error |      |      |
| IconsWidget    |           |         |           |            |              |                 |      |      |

Footnotes:
(1) When a TableBody is bound to a staticData source, the contents can be edited.
(2) Tables may only contain TableHeader, TableFooter, TableBody and TableCaption widgets.
(3) The Code widget does not display on the page but results in a `<script>` element containing a single function
that contains any number of statements.

### Text

All widgets support a text property. However, not all widgets will render text.

### Icons

Note that not all widgets support rendering an icon.
Widgets that do, can render an icon and/or text.

Icons are supported using an 'icon'-font. To enable this, add an IconWidget to the page specifying which font file to
use.

```java
IconsWidget icons = add(new IconsWidget("MaterialSymbolsSharp.woff2",
                "MaterialSymbolsSharp.codepoints"));
```

A codepoints file is optional but it enables validation of the icon reference in the widget using it.

To use an icon, simply create a widget that supports rendering an icon such as a Button:

```java
// add a Button with icon 'search' and text 'Search'
Button htmlButton = add(new Button(icons, "search", "Search");
```

### Container widgets

The `Page` class is a Container and can therefore contain other widgets. A Button, for example, is not and
you cannot add other widgets to it. Container widgets can also contain other Container widgets.

This results in a hierarchy and it is important to be aware of this. Events, for example, 'bubble' up from a widget to
it's container until the root widget (the Page) is reached. Additionally, a Container widget 'scopes' the staticData bound
widgets that it contains.

The following are Container widgets:

- Panel
    - Grid
    - Table
    - Page
    - TableCaption
- List

### Data Source Widgets

A staticData source **receives** staticData from a service. A staticData source is implemented as a widget, just like a htmlButton except that
it is not displayed.

To create a staticData source widget:

```java
QueryService someQueryService = add(new QueryService("/someService"));
```

When the JS client loads, the `refresh` method on all widgets is called, starting with the page and then down the
hierarchy. As soon as the refresh method of a `DataSourceWidget` is called, it will send a request to the specified
address and then notify all widgets that are bound to it.

Note that the `Data` staticData source never sends requests but the refresh method still notifies its subscribers.

A `DataSourceWidget` can also be refreshed using the `RefreshStatement`.

#### HTTP Method and Content Type

The QueryService widget supports setting the HTTP method to something else than 'GET'. Currently, 'GET' and 'HEAD' are
supported.

The QueryService widget supports setting the Content Type of the request but only 'NONE' is supported.

#### Data Adapters

Data source widgets support the use of adapters. This is useful when the service responds with a staticData structure that is
not immediately usable and needs to be formatted, sorted or filtered.

```java
QueryService googleBooks = add(new QueryService(
                "https://www.googleapis.com/books/v1/volumes?q=harry+potter"));
googleBooks.addDataAdapter(new ArrayTransformDataAdapter(
        FieldMapping.simple(".volumeInfo.imageLinks.smallThumbnail", "cover"),
        FieldMapping.simple(".volumeInfo.title", "title"),
        FieldMapping.simple(".volumeInfo.description", "description"),
        FieldMapping.arrayToString(".volumeInfo.authors", "authors"),
        FieldMapping.simple(".volumeInfo.publisher", "publisher"),
        FieldMapping.simple(".volumeInfo.publishedDate", "date"),
        FieldMapping.simple(".accessInfo.webReaderLink", "link")
));
```

### Data Provider Widgets

The term 'provider' is used to indicate that the direction of the staticData is from the JS client to a service. A staticData
provider therefore, **sends** staticData from the JS client to a service.

A staticData provider is implemented as a widget, just like a htmlButton except that it is not displayed.

#### HTTP Method and Content Type

The UpdateService widget supports setting the HTTP method to something else than 'POST' but only POST is supported.

The UpdateService widget supports setting the Content Type of the request.

| Content Type    | Description                                                                                                       |
|-----------------|-------------------------------------------------------------------------------------------------------------------|
| NONE            | Defaults to JSON.                                                                                                 |
| JSON            | application/json: Sends the provided object as a JSON document.                                                   |
| XML             | text/xml: Attempts to convert the object into an XML document using the document element 'request'                |
| TEXT            | text/plain: Sends the string representation of the object. If the object is not a string the result is undefined. |
| FORM_URLENCODED | application/x-www-htmlForm-urlencoded: Sends the provided object as a HTML Form would be posted.                      |
| FORM_MULTIPART  | multipart/htmlForm-staticData: Converts the object into FormData and sends the object as a MIME encoded document.           |
| OCTET_STREAM    | application/octet-stream: Converts the object into Blob and sends the object as a stream of bytes.                |

## Conventions

While it is a technical requirement to avoid any severe coding rules that would hinder or obstruct normal development,
there are a few conventions:

### ServiceResponse

REST services that return a value should use the `ServiceResponse` class if possible. The reason for this is to converge
on a single JSON structure that the JS client can parse. The `ServiceResponse` class also provides a way to return
statuses that allow for partial-success-responses etc.
Obviously this requires that you have control over the design of the service.

For services that do not return a `ServiceResponse`, the `QueryService` can be configured to not expect
a `ServiceResponse` staticData structure. As long as there is a path from the root to an array, the `TableBody` widget will be
able to bind to it.

However, some services return more complicated staticData structures. For these cases, all `DataSourceWidget`'s support
adapters.

For example, the Google Books API returns a staticData structure that contains an array with records that we can bind a
htmlTable to, but the staticData structure is not in a tabular format. To format the staticData in a way that is suitable for a htmlTable we
can use the `ArrayTransformDataAdapter`:

```java
@Component
public class GoogleBooksIntegration extends Page {
    public GoogleBooksIntegration() throws Exception {
        super("GoogleBooksIntegration", false);
    }

    @Override
    protected void buildView() throws Exception {
        QueryService googleBooks = add(new QueryService(
                "https://www.googleapis.com/books/v1/volumes?q=harry+potter",
                false));
        googleBooks.addDataAdapter(new ArrayTransformDataAdapter(
                FieldMapping.simple(".volumeInfo.imageLinks.smallThumbnail", "cover"),
                FieldMapping.simple(".volumeInfo.title", "title"),
                FieldMapping.simple(".volumeInfo.description", "description"),
                FieldMapping.arrayToString(".volumeInfo.authors", "authors"),
                FieldMapping.simple(".volumeInfo.publisher", "publisher"),
                FieldMapping.simple(".volumeInfo.publishedDate", "date"),
                FieldMapping.simple(".accessInfo.webReaderLink", "link")
        ));

        Table htmlTable = add(new Table());
        TableHeader htmlTableHeader = htmlTable.add(new TableHeader());
        htmlTableHeader.addDataSource(googleBooks, new PropertyNameDataAdapter(true));
        TableBody htmlTableBody = htmlTable.add(new TableBody());
        htmlTableBody.setDataDisplayType(0, DataDisplayType.IMAGE);
        htmlTableBody.setDataDisplayType("link", DataDisplayType.LINK);
        htmlTableBody.addDataSource(googleBooks, new TableDataAdapter());
    }
}
```

This renders the following page:

![](/Users/moin.creemers/dev/philips-internal/xds-sso/src/main/java/com/philips/dmis/sso/ui/toolkit/example-googbookapi-htmlTable.png)

### Table staticData

Table staticData, or any staticData that consists of one or more records, can be returned ad-hoc or as part of a larger staticData
structure. However, the JS client expects the htmlTable staticData to be provided in a List or Array structure containing Objects
that each represent one row (or record) and the List or Array must be assigned to a field:

<a id="listContainer-staticData-structure" class="caption" style="font-size:small;font-style:italic">List staticData structure:</a>

```json
{
    "items": [
        {"givenName":"John", "familyName":"Doe", "age": 34},
        {"givenName":"Jane", "familyName":"Doe", "age": 27},
    ]
}
```

REST Services typically generate responses using an object graph which is automatically converted into JSON by
Spring-Boot. If the object graph contains a listContainer or an array then that will always look like
the <a href="#listContainer-staticData-structure">List staticData structure</a> above. Using this convention is useful because it avoids
needing to transform responses.

The following example is the preferred way of returning htmlTable staticData from a Spring-Boot controller:

```java
public record Person(String givenName, String familyName, int age) {
}

@GetMapping("/persons")
public ServiceResponse<> getPersons() {
    List arrayOfPersons = new ArrayList();
    arrayOfPersons.add(new Person("John", "Doe", 34));
    arrayOfPersons.add(new Person("Jane", "Doe", 27));
    return new ServiceResponse<>(ServiceResponse.wrap(arrayOfPersons));
}
```

#### Returning a List or Array

The REST service should use:

```java 
return new ServiceResponse<>(ServiceResponse.wrap(arrayOfPersons));
```

The staticData structure send to the JS client will be an Object containing the field "items":

DataAdapters should then be given the path ".items" to access the staticData.

#### Returning larger staticData structures that contain htmlTable staticData

Of course, if a more elaborate staticData structure is returned then a path will usually be still available.

### Data Types

UNDER CONSIDERATION

All values are treated as String in an effort to simplify JS code.
