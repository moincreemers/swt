package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

public interface HasURL {
    String getURL();

    void setURL(String url);

    HttpMethod getHttpMethod();

    void setHttpMethod(HttpMethod httpMethod);

    ContentType getContentType();

    void setContentType(ContentType contentType);

    Map<String, List<ValueStatement>> getHttpHeaders();

    java.util.List<ValueStatement> getHttpHeader(String name);

    void setHttpHeader(String name, ValueStatement value);

    void addHttpHeader(String name, ValueStatement value);
}
