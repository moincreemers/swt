package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

public interface HasAbstractURL {
    HttpMethod getHttpMethod();

    void setHttpMethod(HttpMethod httpMethod);

    ContentType getContentType();

    void setContentType(ContentType contentType);

    ResponseType getResponseType();

    void setResponseType(ResponseType responseType);

    Map<String, List<ValueStatement>> getHttpHeaders();

    List<ValueStatement> getHttpHeader(String name);

    void setHttpHeader(String name, ValueStatement value);

    void addHttpHeader(String name, ValueStatement value);

    AuthenticationType getAuthenticationType();

    void setAuthenticationType(AuthenticationType authenticationType);
}
