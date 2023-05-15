package com.philips.dmis.swt.ui.toolkit.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;

import java.io.Serializable;

public abstract class Format implements Serializable {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String valueOf(Format format) throws JsRenderException {
        if (format == null) {
            return "null";
        }
        CompactMap compactMap = new CompactMap();
        format.getProperties(compactMap);
        try {
            return OBJECT_MAPPER.writeValueAsString(compactMap);
        } catch (JsonProcessingException e) {
            throw new JsRenderException(e);
        }
    }

    public abstract DataType getFormatType();

    public abstract boolean isValidDataType(DataType dataType);

    public abstract void getProperties(CompactMap properties);
}
