package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class XhrResponse implements Serializable {
    private int status;
    private String url;
    private ContentType contentType = new ContentType();
    private Object data;
    private Map<String, String> arguments = new LinkedHashMap<>();

    public XhrResponse() {
        // for serializable
    }

    public XhrResponse(int status, String url, ContentType contentType, String data) {
        this.status = status;
        this.url = url;
        this.contentType = contentType;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArgument(String name, String value) {
        arguments.put(name, value);
    }
}
