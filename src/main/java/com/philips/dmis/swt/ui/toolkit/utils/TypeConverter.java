package com.philips.dmis.swt.ui.toolkit.utils;

import com.philips.dmis.swt.ui.toolkit.css.CssExpressionParser;
import com.philips.dmis.swt.ui.toolkit.css.CssValue;
import org.xml.sax.SAXException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TypeConverter {
    public static final String STRING_ARRAY_SEPARATOR = ",";

    public static TypeConverter getInstance(String position) {
        return new TypeConverter(position);
    }

    private final String position;

    public TypeConverter(String position) {
        this.position = position;
    }

    public Object convert(Class<?> type, String value) throws SAXException {
        if (value == null) {
            return null;
        }
        if (type == String.class) {
            return value;
        }
        if (type == String[].class) {
            return value.split(STRING_ARRAY_SEPARATOR);
        }
        if (type == Byte.class || type == byte.class) {
            return Byte.parseByte(value);
        }
        if (type == Byte[].class || type == byte[].class) {
            // note: special case: it is assumed this will be a base64 encoded byte array
            return Base64.getDecoder().decode(value);
        }
        if (type == Character.class || type == char.class) {
            if (value.isEmpty()) {
                throw new NumberFormatException("Value out of range. Value:\"" + value + "\" " + position);
            }
            return value.charAt(0);
        }
        if (type == Character[].class || type == char[].class) {
            return value.toCharArray();
        }
        if (type == Boolean.class || type == boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (type == Boolean[].class || type == boolean[].class) {
            List<Boolean> booleans = new ArrayList<>();
            for (String val : value.split(STRING_ARRAY_SEPARATOR)) {
                booleans.add(Boolean.parseBoolean(val));
            }
            return toArray(boolean.class, booleans.toArray());
        }
        if (type == Short.class || type == short.class) {
            return Short.parseShort(value);
        }
        if (type == Short[].class || type == short[].class) {
            List<Short> shorts = new ArrayList<>();
            for (String val : value.split(STRING_ARRAY_SEPARATOR)) {
                shorts.add(Short.parseShort(val));
            }
            return toArray(short.class, shorts.toArray());
        }
        if (type == Integer.class || type == int.class) {
            return Integer.parseInt(value);
        }
        if (type == Integer[].class || type == int[].class) {
            List<Integer> ints = new ArrayList<>();
            for (String val : value.split(STRING_ARRAY_SEPARATOR)) {
                ints.add(Integer.parseInt(val));
            }
            return toArray(int.class, ints.toArray());
        }
        if (type == Long.class || type == long.class) {
            return Long.parseLong(value);
        }
        if (type == Long[].class || type == long[].class) {
            List<Long> longs = new ArrayList<>();
            for (String val : value.split(STRING_ARRAY_SEPARATOR)) {
                longs.add(Long.parseLong(val));
            }
            return toArray(long.class, longs.toArray());
        }
        if (type == Float.class || type == float.class) {
            return Float.parseFloat(value);
        }
        if (type == Float[].class || type == float[].class) {
            List<Float> floats = new ArrayList<>();
            for (String val : value.split(STRING_ARRAY_SEPARATOR)) {
                floats.add(Float.parseFloat(val));
            }
            return toArray(float.class, floats.toArray());
        }
        if (type == Double.class || type == double.class) {
            return Double.parseDouble(value);
        }
        if (type == Double[].class || type == double[].class) {
            List<Double> doubles = new ArrayList<>();
            for (String val : value.split(STRING_ARRAY_SEPARATOR)) {
                doubles.add(Double.parseDouble(val));
            }
            return toArray(double.class, doubles.toArray());
        }
        if (!type.isArray() && type.isEnum()) {
            return findEnumConstant(type, value);
        }
        if (type.isArray() && type.getComponentType().isEnum()) {
            List<Object> enums = new ArrayList<>();
            for (String val : value.split(STRING_ARRAY_SEPARATOR)) {
                enums.add(findEnumConstant(type.getComponentType(), val.trim()));
            }
            return toArray(type.getComponentType(), enums.toArray());
        }

        // custom types
        if (type == CssValue.class) {
            return CssExpressionParser.getInstance().parse(value);
        }

        return null;
    }

    @SuppressWarnings("SuspiciousSystemArraycopy")
    Object toArray(Class<?> type, Object[] arr) {
        Object output = Array.newInstance(type, arr.length);
        System.arraycopy(arr, 0, output, 0, arr.length);
        return output;
    }

    Object findEnumConstant(Class<?> enumType, String value) throws SAXException {
        for (Object enumConst : enumType.getEnumConstants()) {
            if (enumConst != null && ((Enum<?>) enumConst).name().equalsIgnoreCase(value)) {
                return enumConst;
            }
        }
        throw new SAXException("constant \"" + value + "\" not found in enum \"" + enumType.getSimpleName() + "\" "
                + position);
    }
}
