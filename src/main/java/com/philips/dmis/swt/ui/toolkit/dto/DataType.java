package com.philips.dmis.swt.ui.toolkit.dto;

import com.philips.dmis.swt.ui.toolkit.js.JsType;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public enum DataType {
    VIEW,
    BOOLEAN,
    NUMBER,
    DATE,
    STRING,
    URL,
    OBJECT,
    ARRAY,
    ;

    private static final List<Class<?>> NUMBERS = Arrays.asList(byte.class, int.class, Integer.class, long.class, Long.class,
            float.class, Float.class, double.class, Double.class);
    private static final List<Class<?>> BOOLEANS = Arrays.asList(boolean.class, Boolean.class);

    public static DataType fromJavaClass(Class<?> type) {
        if (type.isArray()) {
            return ARRAY;
        }
        if (type == String.class) {
            return STRING;
        }
        if (type == Date.class) {
            return DATE;
        }
        if (type == java.net.URI.class
                || type == java.net.URL.class) {
            return URL;
        }
        if (type.isPrimitive()) {
            if (NUMBERS.contains(type)) {
                return NUMBER;
            }
            if (BOOLEANS.contains(type)) {
                return BOOLEAN;
            }
        }
        return OBJECT;
    }

    public static DataType fromJsType(JsType type) {
        switch (type) {
            case STRING:
                return DataType.STRING;

            case NUMBER:
            case BIGINT:
                return DataType.NUMBER;

            case BOOLEAN:
                return DataType.BOOLEAN;

            case ARRAY:
                return DataType.ARRAY;

            case DATE:
                return DataType.DATE;

            case SYMBOL:
            case OBJECT:
            case FILE:
            case FUNCTION:
            case UNDEFINED:
            case NULL:
            case REGEX:
            case VOID:
            default:
                return DataType.OBJECT;
        }
    }
}
