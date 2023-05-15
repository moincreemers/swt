package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class View implements Serializable {
    public static String getRootViewId(String dataAdapterId) {
        return "view-" + dataAdapterId;
    }

    private String id;
    private String name;
    private String source;
    private String fixedValue;
    private ViewType viewType;
    private List<String> items = new ArrayList<>();
    private DataType dataType;
    private DataType formatType;
    private CompactMap format;
    private Order order;
    private String orderSource;
    private ViewAppearance appearance;

    // todo: how about style? like column width, or color

    public View() {
        // for serialization
    }

    public View(String id, String name, ViewType viewType, DataType dataType) {
        this(id, name, name, viewType, dataType, Order.NONE);
    }

    public View(String id, String name, String source, ViewType viewType, DataType dataType, Order order) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.orderSource = source;
        this.viewType = viewType;
        this.dataType = dataType;
        this.formatType = dataType;
        this.order = order;
        if (viewType == ViewType.SEQUENCE) {
            appearance = ViewAppearance.DISPLAY_WHEN_DATA;
        }
    }

    public void addItem(String id) {
        items.add(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        if (source.isEmpty()) {
            setSource(name);
        }
    }

    public void setSource(String source) {
        this.source = source;
        if (this.orderSource == null) {
            setOrderSource(source);
        }
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public void setFixedValue(String fixedValue) {
        this.fixedValue = fixedValue;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
        if (viewType == ViewType.FIELD) {
            items.clear();
        }
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
        if (format == null) {
            formatType = dataType;
        }
    }

    public void setFormat(Format format) {
        if (format == null) {
            formatType = dataType;
            this.format = null;
            return;
        }
        formatType = format.getFormatType();
        this.format = new CompactMap();
        format.getProperties(this.format);
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAppearance(ViewAppearance appearance) {
        this.appearance = appearance;
    }

    // do not remove the following methods

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public String getFixedValue() {
        return fixedValue;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public List<String> getItems() {
        return items;
    }

    public DataType getDataType() {
        return dataType;
    }

    public CompactMap getFormat() {
        return format;
    }

    public DataType getFormatType() {
        return formatType;
    }

    public Order getOrder() {
        return order;
    }


    public ViewAppearance getAppearance() {
        return appearance;
    }
}
