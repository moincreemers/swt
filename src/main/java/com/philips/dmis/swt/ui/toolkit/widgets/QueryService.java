package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ErrorEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.RefreshEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.ResponseEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import org.springframework.http.HttpMethod;

import java.net.URI;
import java.util.List;
import java.util.*;

/**
 * A DataSource widget that sends an HTTP request.
 */
public class QueryService extends DataSourceWidget implements HasURL {
    static boolean isRelativeURL(String url) {
        return (url != null &&
                URI.create(url).getScheme() == null);
    }

    private String url;
    private HttpMethod httpMethod = HttpMethod.GET;
    private ContentType contentType = ContentType.NONE;
    private final Map<String, java.util.List<ValueStatement>> httpHeaders = new LinkedHashMap<>();
    private final java.util.List<Parameter> parameters = new ArrayList<>();

    public QueryService(String url) {
        this(url, isRelativeURL(url));
    }

    public QueryService(String url, boolean expectServiceResponse) {
        super(WidgetType.QUERY_SERVICE, expectServiceResponse);
        setURL(url);
    }

    public QueryService(String url, boolean expectServiceResponse, boolean autoRefresh) {
        super(WidgetType.QUERY_SERVICE, expectServiceResponse, autoRefresh);
        setURL(url);
    }

    public QueryService onRefresh(RefreshEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public QueryService onResponse(ResponseEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public QueryService onError(ErrorEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    @Override
    public void addParameter(String name, String defaultValue) {
        parameters.add(new Parameter(name, defaultValue));
    }

    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }


    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void setURL(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("invalid URL");
        }
        if(url.contains("?")||url.contains("#")){
            throw new IllegalArgumentException("URL contains query or hash");
        }
        this.url = url;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public Map<String, List<ValueStatement>> getHttpHeaders() {
        return httpHeaders;
    }

    @Override
    public java.util.List<ValueStatement> getHttpHeader(String name) {
        return httpHeaders.get(name);
    }

    @Override
    public void addHttpHeader(String name, ValueStatement value) {
        java.util.List<ValueStatement> headers = httpHeaders.computeIfAbsent(name, k -> new ArrayList<>());
        headers.add(value);
    }

    @Override
    public void setHttpHeader(String name, ValueStatement value) {
        httpHeaders.put(name, Arrays.asList(value));
    }
}
