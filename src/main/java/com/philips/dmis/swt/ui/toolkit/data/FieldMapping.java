package com.philips.dmis.swt.ui.toolkit.data;

import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.Format;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;

public class FieldMapping {
    public static FieldMapping map(String from, String to, DataType dataType) {
        return new FieldMapping(FieldMappingType.SIMPLE, from, to, to, dataType, null, null);
    }

    public static FieldMapping map(String from, String to, String name, DataType dataType) {
        return new FieldMapping(FieldMappingType.SIMPLE, from, to, name, dataType, null, null);
    }

    public static FieldMapping map(String from, String to, DataType dataType, Format format) {
        return new FieldMapping(FieldMappingType.SIMPLE, from, to, to, dataType, format, null);
    }

    public static FieldMapping map(String from, String to, String name, DataType dataType, Format format) {
        return new FieldMapping(FieldMappingType.SIMPLE, from, to, name, dataType, format, null);
    }

    public static FieldMapping indexArray(String from, String to, String arrayIndexer) {
        return new FieldMapping(FieldMappingType.INDEX_ARRAY, from, to, to, DataType.OBJECT, null, arrayIndexer);
    }

    private final FieldMappingType fieldMappingType;
    private final String from;
    private final String to;
    private final String name;
    private final DataType dataType;
    private final Format format;
    private final String arrayIndexer;

    private String orderSource;
    private ViewAppearance appearance = ViewAppearance.DEFAULT;

    public FieldMapping(FieldMappingType fieldMappingType, String from, String to, String name, DataType dataType, Format format, String arrayIndexer) {
        this.fieldMappingType = fieldMappingType;
        this.from = from;
        this.to = to;
        this.name = name;
        this.dataType = dataType;
        this.format = format;
        this.arrayIndexer = arrayIndexer;
        validate();
    }

    private void validate() {
        if (fieldMappingType == null) {
            throw new IllegalArgumentException("missing 'type'");
        }
        if (from == null || from.isEmpty()) {
            throw new IllegalArgumentException("missing 'from'");
        }
        if (!from.startsWith(".")) {
            throw new IllegalArgumentException(String.format("expected '%s' to start with .", from));
        }
        if (to == null || to.isEmpty()) {
            throw new IllegalArgumentException("missing 'to'");
        }
        if (to.contains(".") || to.contains("[") || to.contains("]")) {
            throw new IllegalArgumentException(String.format("expected valid object identifier '%s'", to));
        }
        if (from.contains("(") || from.contains(")")) {
            throw new IllegalArgumentException(String.format("object identifier '%s' contains unsafe characters", from));
        }
        if (fieldMappingType == FieldMappingType.INDEX_ARRAY && (arrayIndexer == null || arrayIndexer.isEmpty())) {
            throw new IllegalArgumentException("missing array indexer name");
        }
    }

    public FieldMappingType getFieldMappingType() {
        return fieldMappingType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getName() {
        return name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public DataType getFormatType() {
        return format == null ? dataType : format.getFormatType();
    }

    public Format getFormat() {
        return format;
    }

    public String getArrayIndexer() {
        return arrayIndexer;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public ViewAppearance getAppearance() {
        return appearance;
    }

    public void setAppearance(ViewAppearance appearance) {
        this.appearance = appearance;
    }
}
