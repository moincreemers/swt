package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;

public class XhrResponse implements Serializable {
    private int status;
    private String url;
    private ContentType contentType = new ContentType();
    private String responseText;

    public XhrResponse(){
        // for serializable
    }

    public XhrResponse(int status, String url, ContentType contentType, String responseText) {
        this.status = status;
        this.url = url;
        this.contentType = contentType;
        this.responseText = responseText;
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

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}
