package com.philips.dmis.swt.ui.demo;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.data.FieldMapping;
import com.philips.dmis.swt.ui.toolkit.data.ImportArrayDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.dto.URLFormat;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEvent;
import com.philips.dmis.swt.ui.toolkit.events.KeyPressEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ResponseEventHandler;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.stereotype.Component;

@Component
public class GoogleBooksExample extends Page {
    public GoogleBooksExample() throws Exception {
        super(Constants.isDemo(GoogleBooksExample.class));
    }

    @Override
    protected void build() throws Exception {
        if (!isDefault()) {
            add(HtmlLink.closePage("Back to Examples"));
        }
        add(new HtmlHeading("Integrating a Third-Party API"));

        // note: set to manual refresh because this is an external service
        QueryService googleBooks = add(new QueryService(
                "https://www.googleapis.com/books/v1/volumes", false, false));
        googleBooks.addDataAdapter(new ImportArrayDataAdapter(".items")
                .add(FieldMapping.map(".volumeInfo.imageLinks.smallThumbnail", "cover", "Cover",
                        DataType.URL,
                        new URLFormat().setAppearance(URLAppearanceType.IMAGE).setImageWidth("200px").setImageBorderRadius("0.5em")))
                .add(FieldMapping.map(".volumeInfo.title", "title", "Title",
                        DataType.STRING))
                .add(FieldMapping.map(".volumeInfo.description", "description", "Description",
                        DataType.STRING))
                .add(FieldMapping.map(".volumeInfo.authors", "authors", "Authors",
                        DataType.STRING))
                .add(FieldMapping.map(".volumeInfo.publisher", "publisher", "Publisher",
                        DataType.STRING))
                .add(FieldMapping.map(".volumeInfo.publishedDate", "publishedDate", "Date published",
                        DataType.STRING))
                .add(FieldMapping.map(".accessInfo.webReaderLink", "webReaderLink", "Web reader",
                        DataType.URL,
                        new URLFormat().setAppearance(URLAppearanceType.ANCHOR).setText("Open"))));

        add(new HtmlParagraph("This page contains a QueryService which interacts with the " +
                "<a href=\"https://developers.google.com/books\">Google Books API</a>. "));

        add(new HtmlParagraph(
                "The QueryService has been set to <b>not</b> refresh when the application starts " +
                        "or when the page loads. " +
                        "Instead, the search field below uses the M.SetDataSourceParameter to add the parameter \"q\" to the " +
                        "QueryService. Then, the M.Refresh statement is used to tell the QueryService to send a request " +
                        "to the Google Books API."));

        TabWidget tabWidget = add(new TabWidget(
                "Search Google Books", "QueryService response"));

        HtmlTable htmlTable = tabWidget.panel(0).add(new HtmlTable());
        HtmlTableCaption htmlTableCaption = htmlTable.add(new HtmlTableCaption(false));

        HtmlSearchInput queryParameter = htmlTableCaption.add(new HtmlSearchInput());
        queryParameter.setPlaceholder("Search Google Books");
        onKeyPress(new KeyPressEventHandler(
                M.Iif(V.Is(V.GetEvent(KeyPressEvent.KEY_CODE), V.Const(KeyPressEvent.VK_ENTER))).True(
                        M.SetQueryParameter(googleBooks, V.Const("q"), V.GetValue(queryParameter)),
                        M.Refresh(googleBooks),
                        M.SetDisabled(queryParameter)
                )
        ));

        HtmlTableHeader htmlTableHeader = htmlTable.add(new HtmlTableHeader());
        htmlTableHeader.addDataSource(googleBooks);

        HtmlTableBody htmlTableBody = htmlTable.add(new HtmlTableBody());
        htmlTableBody.addDataSource(googleBooks);

        HtmlPreformatted apiResponse = tabWidget.panel(1).add(new HtmlPreformatted(TextFormatType.JSON));
        apiResponse.addDataSource(googleBooks);

        googleBooks.onResponse(new ResponseEventHandler(
                M.SetEnabled(queryParameter)
        ));
    }
}
