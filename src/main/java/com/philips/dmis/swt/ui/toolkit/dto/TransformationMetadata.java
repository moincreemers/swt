package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;

public class TransformationMetadata implements Serializable {
    private String id;
    private String type;

    public TransformationMetadata() {
        // for serializable
    }

    public TransformationMetadata(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
