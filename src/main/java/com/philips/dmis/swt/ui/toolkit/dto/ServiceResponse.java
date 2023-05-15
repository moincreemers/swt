package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;
import java.util.*;

public class ServiceResponse implements Serializable {
    public static final String META_DATASOURCE_ID = "dataSource";
    public static final String META_RESULT_SIZE = "queryResultSize";
    public static final String META_NEXT_RESULT_URL = "nextQueryResultURL";
    public static final String META_TRANSFORMATIONS = "transformations";
    public static final String META_DEFAULT_VIEW_ID = "defaultView";
    public static final String META_SELECTED_VIEW_ID = "selectedView";
    public static final String META_ORDERING_ENABLED = "orderingEnabled";

    public static ArrayWrapper wrap(Object data) {
        if (data.getClass().isArray()) {
            return wrap((Object[]) data);
        }
        if (data instanceof List) {
            return wrap((List<Object>) data);
        }
        if (data instanceof Set) {
            return wrap((Set<Object>) data);
        }
        if (data instanceof String || data instanceof Date || data.getClass().isPrimitive()) {
            return new ArrayWrapper(Collections.singletonList(
                    new ValueWrapper(data)));
        }
        return new ArrayWrapper(Collections.singletonList(
                data));
    }

    public static ArrayWrapper empty() {
        return new ArrayWrapper(new ArrayList<>());
    }

    public static ArrayWrapper wrap(List<Object> data) {
        return new ArrayWrapper(data);
    }

    public static ArrayWrapper wrap(Set<Object> data) {
        return new ArrayWrapper(data);
    }

    public static ArrayWrapper wrap(Object[] data) {
        return new ArrayWrapper(data);
    }

    private Map<String, Object> meta = new LinkedHashMap<>();
    private Map<String, View> views = new LinkedHashMap<>();
    private List<Status> statuses = new ArrayList();
    private final Object data;

    public ServiceResponse() {
        this.data = ServiceResponse.empty();
    }

    public ServiceResponse(Object data) {
        if (data == null) {
            throw new IllegalArgumentException("do not pass null");
        }
        if (data.getClass().isArray() || data instanceof Collection) {
            this.data = ServiceResponse.wrap(data);
            return;
        }
        this.data = data;
    }

    public void addStatus(StatusType statusType, String message) {
        statuses.add(new Status(statusType, message));
    }

    public boolean isWarning() {
        return statuses.stream().filter(s -> s.getType() == StatusType.WARNING).findAny().isPresent();
    }

    public boolean isError() {
        return statuses.stream().filter(s -> s.getType() == StatusType.ERROR).findAny().isPresent();
    }

    public void addMeta(String key, Object value) {
        meta.put(key, value);
    }

    public void addView(View view) {
        this.views.put(view.getId(), view);
    }


    // do not remove
    public List<Status> getStatus() {
        return statuses;
    }

    // do not remove
    public Map<String, Object> getMeta() {
        return meta;
    }

    // do not remove
    public Object getData() {
        return data;
    }

    // do not remove
    public Map<String, View> getViews() {
        return views;
    }
}
