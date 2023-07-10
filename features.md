# Singular - Features

## Defects

- When the maven (development) webserver is running, recompiling sometimes results in a validation error on a page
  reference specifically. Unclear why. Re-compiling always succeeds! Sometimes Maven already does a recompile but its
  unclear why it does that.
- GetCurrentPage does not work in the Page.onDeactivate event (the hash has already changed when this event is fired)
- Global events are no longer dispatched to a widget other than the page. Basically, sending global events back to
  widgets on which the subscription took place is disabled. This is because of refactoring in GlobalEvents.java.

## VIEWER STATUS

- For Viewer pages:
    - CORS is not enabled from server. Needs correct HTTP Headers to allow cross-domain requests to the API if we want
      to run the frontend from another host. If we can run both from the same domain, this should not be a problem.
    - ForView API still requires session cookie.
    - The URL hashing in document retrieve endpoint could be somewhat problematic. This makes caching impractical. There
      is no mechanism that provides an expiration time or anything like that.
    - Some APIs return HL7 data structures that need to be parsed to be useful. This is a bit of extra work (
      HL7Lib.java). This work is incomplete at this time so things like patient email / phone is not parsed yet.
    - WADO client works but is very, very basic right now.
    - CDA's render ok, but not great. CSS/layout seems a bit 'off'. Needs a closer inspection. Everything currently just
      opens in another tab/window. This could be reasonable but we can provide an iframe instead.
    - Features missing:
        - Session timeout --> automatic logout with warning dialog
        - Disclaimers
        - Multiple (configurable) Document Lists
        - Document Actions
        - Delete document
        - Add document / forms
        - Document Filters (client-side). Note that the original feature supports customization of the filters.
        - Patient consent (should NOT be part of ForView anyway and we already have a new Patient-facing Consent App)
        - Patient Identifiers. Original feature supports adding/removing.
        - DICOM Export
        - User settings
        - About screen (licences, use policy, 3rd party notices)
        - Image Upload. This is currently a plugin on top of the old ForView but should probably be an integral part of
          the viewer and not a plugin. Or alternatively, this should be a standalone application.
        - No XDW client. As a result, e-Referral is not supported. Should be a standalone application.
        - WADO: no reports, on-screen image meta data, greyscale, overlays or flv (video). Is this really needed for a
          non-diagnostic viewer? Reports yes, but the rest?
        - DSUB (notification when document list has changed)

## Started

- KeyShortcutWidget that can be configured to capture certain key strokes and then raise an event. Even double keyed
  shortcuts could easily be captured.
- iFrame with data binding support: started.
- responsive css. on hold: difficult to define exact requirements
- unit tests that can run/verify generated JS
- FileUpload widget (and Binary upload). Widget completed, not tested.
- Download method when using code to load an image.

## Backlog

- Create a development/debug widget that can work with the tracing? Also: instrumentation module for UI testing
- make open/selection widgets in tables focusable (class/attribute)
- make timer interval gettable and settable in JS.
- htmlTable: conditional formatting (based on data)
- htmlTable: record selection. should lead to event handler on TableBody that provides an event with the selection
  data and the record being (de-)selected.
- htmlTable: perhaps a CSS style for selected records?
- MapDataAdapter, add option to combine multiple columns into one or split a column into multiple columns
- join-data-adapter
- Configuration options through beans (using @Component and injecting into ToolkitController?)
- more support for page layout
- disable/enable individual options in checkbox/radiobutton lists
- breadcrumb Widget
- limit htmlTable loading to a number of records and then show a "load more records" htmlButton or something. This is
  needed to protect against huge responses.
- htmlTable header/footer for Grid
- tree widget? And in combination with htmlTable?
- htmlTable header with hierarchy. Data view structure support this.
- css support & responsive. Theme support?
- adding fonts? not arbitrary but with indirection. So a way to define "serif", "sans-serif", "monospace" and "icons" (
  for example). Then add an Enum to set desired font in widgets.
- right-to-left?
- Notification panel. Non-modal. With or without a timeout.
- RegEx comparator Statement
- Access control
- Write JS into separate file (browser caching strategy)
- Input validation. Has to be more advanced than single input field validation. Input validation using statement?
  onValidate customEvent?

## Completed

- Integration with Spring-Boot
- Automatic mapping of JS Client rendering/download via default path (i.e., "/"). This is now implemented by the
  developer. The developer needs to create the application controller where the mapping is declared.
- Widget hierarchy with containers and non-containers: DONE.
- Automatic discovery of Pages using the @Component attribute: DONE
- Automatic validation of all pages, widgets and statements when starting application: DONE
- Icon font: DONE.
- Button types: DONE.
- Panel types: DONE.
- Data Sources: DONE
- Data Provider: DONE
- static Data Source: DONE.
- Data Binding on widgets: DONE.
- iFrame: DONE
- Table with DataBinding: DONE.
- non data driven Tables: DONE. Implemented Grid.
- Numbered paragraphs: DONE.
- ClickHandler, ChangeHandler on certain widgets: DONE.
- Statements: DONE.
- Fixed page header and/or Footer independent of scrolling of page: DONE.
- Page controller: DONE.
- Modal 'Dialog' pages: DONE
- Scroll panel: DONE. Panel.setOverflowAndSize
- Text widgets protect against HTML injection: DONE (but could be better).
- Data from input widgets is collected per container. Data can be returned on a per-widget, per-container or page basis
  resulting in a JSON document: DONE.
- DataAdapter to 'find' htmlTable column names based on property names of 'record' objects: DONE
- DataAdapter that maps foreign JSON documents to a record set structure: DONE
- Radiobutton lists can act as Tab strip widget to facilitate a tabular layout: DONE
- loading screen: DONE.
- Proper HTML encoding when setting element.innerHTML=<some unicode text> (or element.textContent=...): DONE
- Secure HTML injection for paragraphs: DONE. HTML injection is allowed but HTML is sanitized.
- dialogs can open on top of other dialogs: DONE.
- ClosePage statement goes back to the page that opened the dialog and goes back to the page where we came from. Has
  default page option in case no previous page exists: DONE.
- Timer widget: DONE.
- Format code (JS, Java): DONE
- Dialogs are not in the correct position when the page is scrolled. The scrollbar is then removed but the dialog may be
  out of the viewport. Sidebar dialogs do the same thing. Preferred solution is CSS (if possible).: DONE.
- Dialogs can return decision and other data to the page that owns them: Partially done. We need some way to use
  the data returned in QueryService parameters and statements etc.: DONE. See V.GetReturnValue.
- filter adapter: DONE
- groupBy adapter: DONE
- aggregate adapter: DONE
- htmlTable footer: DONE
- DataAdapter filter or non-filter, this is confusing: DONE. Filter flag removed.
- DataAdapter disabling not working (see pages.UpdateFunction): DONE
- properly implemented attributes on HTML widgets (such as required, min, max etc.) also statements to set them.
- date, time and date-time widgets
- DataProxy widget which is both a DataSource and DataBoundWidget
- allow parameters in DataSourceWidget
- allow parameters in DataSourceWidget to be updatable using a statement
- Radiobutton and Checkbox lists value get/set.
- ServiceResponse data structure also allows for views to be stored.
- Added widgets to support many of the standard HTML elements
- Added File widget
- if dialog then z-index of active dialog must be higher then everything else. Currently, the tk-page-active class sets
  z-index:1000 but this does not work on the non-active dialogs. Order of opening should be preserved. This can only be
  done by the controller.
- Size reduction: Started with adding scope to JsMember meaning that every member decides to which widget it belongs.
  This shaves off approx. 30% of the code size.
- removed htmlTable editing, fixed columns and display type. replaced with view metadata.
- DataAdapter that can map values
- Composite widget allows combining multiple widgets to create common functionality such as a tab-page widget.
- Views in the dataset
- Numbered headings do not work on WidgetGallery page, class assignment "page-{pageid}" was incorrectly implemented.
- Widget now has a 'pack' method which is (recursively) called after the build phase. This allows the widget to do any
  tasks that require the parent-child relationship to be valid (and other things).
- Os light/dark theme on load and response to change now supported. However, since one stylesheet needs to be disabled
  to switch to light. re-enabling dark mode does not require a round trip to the web server.
- htmlTable header sorting. Add SortingDataAdapter to data source which takes a widget that implements
  HasOrderingControls. The data adapter automatically binds to the widget to get the desired ordering information.
  HasOrderingControls adds the onOrderChange even handler which should be used to refresh the data source when the
  ordering changes. There is an OrderedTableHeader composite widget to make this very simple. Ordering information stays
  with the ordering controls. This means that the selected ordering remains after refreshing the data source.
- Added ListType to replace the 'numbered' argument. Added MENU and INLINE_MENU and CSS styles. A List with INLINE_MENU
  and Panel with PanelType.TOOLBAR can be combined.
- image input widget
- keyboard key binding on page level, ok-primary and escape-cancel to register itself to the Enter key for example
- Close dialogs when clicking outside of dialog
- GROUP panel type, just for keeping widgets together
- Code is now a module that can contain multiple functions. the module can be added to a page as a library but will be
  rendered only once. The call statement now takes the Code widget as an argument.
- local cache has a CacheType to support background-refresh. This means the cached data is displayed first, then the
  data source performs a refresh.
- data bound widgets are now cleared (htmlTable) before the data source is refreshing.
- htmlTable: open record by clicking a specified cell (Link) and option to add a column with a Link or Button to execute
  a statement. this should lead to an event handler on TableBody that provides an event with the record data.
- Table ordering now supports ability to display a column but use the data from another for the sorting, this is
  useful
  when the data being displayed is formatted in a way that does not support normal sorting such as formatted
  dates.
- data transfer between pages using session storage. The state is still referenced in the hash (d) but the value
  is a token. Use V.GetPageArgumentValue to retrieve the actual data. V.GetReturnValue removed.
- added PageHeaders module to handle window resize to set the correct padding on page_inner when page header and/or
  footer are present.
- navigation panels (left/right) similar to page header/footer
- Select boxes that have a value selected other than the first item, are reset to the first item when a page is
  activated. This is presumably caused by data source refresh.
- limit size of the JS by reducing widget model size. Move code to global module
- error handler Method. M.Try is now a method.
- global 'model' store arbitrary values in? Statement.Get/Set should work. Scope to page/application option.
  M.SetGlobalValue can be used to store values in SessionStorage. There is M.DeclarePageVariable, M.SetPageVariable and
  V.GetPageVariable that provides variables scoped to the current page.
- Templates:
    - There is now a M.CloneWidget method that can be used to manually copy a widget.
    - The M.CloneWidget method takes a 'data-key' to identify the clone.
    - The clone will have an ID starting with 'g' instead of 'w'.
    - The template widget will no longer be usable as a normal widget.
    - M.SetValue and all other methods that affect a widget can be used on the template widget. In event handlers, use
      the M.WithDayaKey method.
    - Additionally, a DataTemplateWidget has been added that is a data consumer. The widget takes one or more template
      widgets, a field that selects the template based on a field in the data source, a target container widget to which
      the cloned widgets should be added and a data key field in the data source.
    - Event handlers on the template widget will be used by cloned widgets.
    - There is a M.RemoveClone method to remove a single clone or M.RemoveAllClones to remove all clones. The method
      works on the template that was used to create the clones.
- There is now a 'D' static class that has statements that work directly on the DOM.
- DataAdapter 'framework' has been refactored. DataSourceUsage has been redefined to be more specific. That is: some
  values for DataSourceUsage are only applicable to some widgets such as Value or Options. Therefore, the addDatasource
  method now has a typed DataSourceUsage that only has the applicable values that correspond to the DataSourceUsage.
- DataSourceUsage also has a priority now. This means that data adapters are called in a specific order determined by
  this priority. This is necessary because DataSourceUsage.TEMPLATE_LIST_ITEMS is used to generate clones. This should
  happen before any other data adapters are invoked.
- The original design concept of some data adapters converting the data into a different data structure specifically for
  DataSourceUsage.VALUE has been replaced by a more consistent design. All DataAdapters now operate on the existing
  ServiceResponse data structure. They either modify the instance provided or recreate a new instance but the structure
  must stay the same. The only exception is IMPORT data adapters.
- The PathDataAdapter has been renamed ValueDataAdapter and instead ADDS a field to each data item
  with a constant name. This field is referenced by ValueWidgets to grab the value they should be set to. If there are
  multiple data items. The first one is used.
- A DataConsumerWidget has been added. This is an invisible widget that simply acts as a data consumer similar to a
  DataBound widget except that there is no visible output. The purpose is to allow switching from a purely Data driven
  approach to a procedural approach. The idea is to use the onUpdate method of the DataConsumerWidget to start
  responding to the data using statements.
- Tracing has been improved. There is also a JsLogFilter class that can be set globally to configure tracing.
- Fixed defect in data adapters on data consumers where there are multiple data adapters for the same usage with
  different data sources. This would be the case with a SELECT that has two data sources that need a different
  KeyValueListDataAdapter. The data adapter variable now groups data adapters by dataSourceId and during updates, the
  dataSourceId is used to invoke the correct data adapters. For data adapters on data sources, the constant
  dataSourceId "self" is used instead.
- Templates: When using DataTemplateWidget with more than one template and refreshing, RemoveAllClones is now called on
  all templates. Note that this means that developers should not use the same template from multiple DataTemplateWidgets
  if it is not the intention to remove all clones every time the data is updated.
- update service support for other HTTP methods and encoding
- Tracing. Methods can now call JsWriter.trace(JsMethod) -> js.trace(this). This will add tracing code to the top of the
  method. Then subsequent js.trace(format, args[]) can be used to log messages that will appear only when the method is
  whitelisted in JsLogFilter. This way we can target console logging to specific functions.
- GetPageArgument throws when more than one 'd' argument is present on the hash.
- TextArea widget
- Much better spinner/loader
- Language support. The way this is implemented is as follows:
    - Only ISO language codes are supported, e.g., "en". There is currently no support for differentiating between "
      en-GB" and "en-US" for example.
    - The toolkit is set to a default language. This is always English ("en").
    - When constants are declared, for example setting text of an HtmlParagraph, the constant is assumed to have the
      default language.
    - The value of the constant in the default language is the key to that constant.
    - When the application code is generated and the DEBUG flag is set, the toolkit will output all constants in the
      default language. This can be copied and pasted into a resource file. Each constant token is prefixed with the
      language, "en" by default. This needs to be replaced with a new language and then the value translated into that
      language.
    - The toolkit provides a method to add a language file. This needs to be a simple Java properties file. A good place
      to declare these language files is in the application controller class.
    - Depending on the language that the user has selected in the browser, the constant is looked up in that language if
      available. If not, the default language is used.
    - It is technically allowed to declare a constant in a language file for the default language ("en") which overrides
      any constant set in the code. However, this is not the intended use.
    - The application needs to be reloaded for a language change to take effect.
    - When the constant value changes in the default language, e.g., a button used to have the text "Ok" and it is
      now changed into "Save" then be aware that the token will change and therefore, the token of all translations must
      be updated accordingly.

## Rejected

- set a 'media' setting on containers so that when printing, they can be hidden
- support for image map. Not a commonly used feature of HTML and also not a good practise in terms of user experience.