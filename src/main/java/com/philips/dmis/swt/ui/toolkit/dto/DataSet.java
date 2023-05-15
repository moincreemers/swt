package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSet implements Serializable {
    public DataSet() {
        // for serialization
    }

    private Map<String, Object> meta = new LinkedHashMap<>();
    private List<Map<String, Object>> items = new ArrayList<>();

    public Map<String, Object> getMeta() {
        return meta;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }
}
