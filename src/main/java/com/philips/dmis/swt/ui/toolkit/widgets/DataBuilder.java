package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class DataBuilder {
    public static DataBuilderValues values() {
        return new DataBuilderValues();
    }

    public static DataBuilderKeyValue keyValue() {
        return new DataBuilderKeyValue();
    }

    private DataBuilder() {
    }

    public static class DataBuilderValues extends DataBuilder {
        private final java.util.List<Object> values = new ArrayList<>();

        public DataBuilderValues add(Object value) {
            values.add(value);
            return this;
        }

        public DataBuilderValues addAll(Object... values) {
            this.values.addAll(Arrays.asList(values));
            return this;
        }

        public DataBuilderValues add(java.util.List<?> values) {
            this.values.addAll(values);
            return this;
        }

        @Override
        public Object getData() {
            return values;
        }
    }

    public static class DataBuilderKeyValue extends DataBuilder {
        private final java.util.List<Pair<Object, Object>> values = new ArrayList<>();

        public DataBuilderKeyValue add(Object key, Object value) {
            values.add(new Pair<>(key, value));
            return this;
        }

        public DataBuilderKeyValue add(java.util.Map<?, ?> keyValues) {
            for (Object key : keyValues.keySet()) {
                add(key, keyValues.get(key));
            }
            return this;
        }

        @Override
        public Object getData() {
            return values;
        }
    }


    public abstract Object getData();
}
