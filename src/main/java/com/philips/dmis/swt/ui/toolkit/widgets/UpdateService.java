package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ChangeEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;
import org.springframework.http.HttpMethod;

import java.util.*;
import java.util.List;

@PageXmlElement({"url", "httpMethod", "contentType", "responseType", "authenticationType", "httpHeaders", "parameters"})
public class UpdateService extends DataProviderWidget<UpdateService> implements
        HasURL {
    private String url;
    private HttpMethod httpMethod = HttpMethod.POST;
    private ContentType contentType = ContentType.JSON;
    private ResponseType responseType = ResponseType.DEFAULT;
    private AuthenticationType authenticationType = AuthenticationType.NONE;
    private final Map<String, List<ValueStatement>> httpHeaders = new LinkedHashMap<>();
    private final java.util.List<Parameter> parameters = new ArrayList<>();

    public UpdateService(WidgetConfigurator widgetConfigurator, String url) {
        super(widgetConfigurator, WidgetType.UPDATE_SERVICE);
        setURL(url);
    }

    public UpdateService(String url) {
        super(WidgetType.UPDATE_SERVICE);
        setURL(url);
    }

    @Override
    public UpdateService onChange(ChangeEventHandler eventHandler) {
        getEventHandlers().add(eventHandler);
        return this;
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
        if (url.contains("?") || url.contains("#")) {
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
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
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

    @Override
    public void addParameter(String name, String defaultValue) {
        parameters.add(new Parameter(name, defaultValue));
    }

    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    @Override
    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.OBJECT;
    }
}
