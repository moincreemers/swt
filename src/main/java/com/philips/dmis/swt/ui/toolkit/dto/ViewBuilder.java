package com.philips.dmis.swt.ui.toolkit.dto;

import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.*;

public class ViewBuilder {
    private static int id;
    private static long id2 = System.currentTimeMillis();

    private final Map<String, View> views = new HashMap<>();
    private final View top;

    public ViewBuilder(Class<?> dto) {
        this(dto.getSimpleName());
        for (Member member : DtoUtil.getProperties(dto)) {
            if (member instanceof Field) {
                addField(DtoUtil.getProperName(member),
                        DtoUtil.getJsonKey(member),
                        DataType.fromJavaClass(((Field) member).getType()));
            }
            if (member instanceof Method) {
                addField(DtoUtil.getProperName(member),
                        DtoUtil.getJsonKey(member),
                        DataType.fromJavaClass(((Method) member).getReturnType()));
            }
        }
    }

    public ViewBuilder(String name) {
        Random random = new Random();
        random.setSeed(id2);
        String id = "v" + Integer.toHexString(random.nextInt());

        validateNameIsUnique(name);
        top = new View(id, name, ViewType.SEQUENCE, DataType.VIEW);
        views.put(top.getName(), top);
    }

    public String getId() {
        return top.getId();
    }

    private String nextId() {
        return "v" + (id++) + "." + top.getId();
    }

    public ViewBuilder addBooleanField(String name, String source) {
        return addField(name, source, DataType.BOOLEAN);
    }

    public ViewBuilder addNumberField(String name, String source) {
        return addField(name, source, DataType.NUMBER);
    }

    public ViewBuilder addTextField(String name, String source) {
        return addField(name, source, DataType.STRING);
    }

    public ViewBuilder addDateField(String name, String source) {
        return addField(name, source, DataType.DATE);
    }

    public ViewBuilder addField(String name, DataType dataType) {
        return addField(name, name, dataType);
    }

    public ViewBuilder addField(String name, String source, DataType dataType) {
        validateNameIsUnique(name);
        View field = new View(nextId(), name, source, ViewType.FIELD, dataType, Order.NONE);
        top.addItem(field.getId());
        views.put(field.getName(), field);
        return this;
    }

    public ViewBuilder setNumberFormat(String name, NumberFormat numberFormat) {
        return setFormat(name, numberFormat);
    }

    public ViewBuilder setTextFormat(String name, TextFormat textFormat) {
        return setFormat(name, textFormat);
    }

    public ViewBuilder setDateTimeFormat(String name, DateTimeFormat dateFormat) {
        return setFormat(name, dateFormat);
    }

    public ViewBuilder setFormat(String nameOrSource, Format format) {
        if (format == null) {
            return this;
        }
        String name = validateNameOrSourceExists(nameOrSource);
        View view = views.get(name);
        if (!format.isValidDataType(view.getDataType())) {
            throw new IllegalArgumentException("invalid format for data type '" + view.getDataType().name() + "' for source '" + nameOrSource + "'");
        }
        views.get(name).setFormat(format);
        return this;
    }

    public ViewBuilder setOrder(String nameOrSource, Order order) {
        if (order == null) {
            return this;
        }
        String name = validateNameOrSourceExists(nameOrSource);
        views.get(name).setOrder(order);
        return this;
    }

    public ViewBuilder setOrderSource(String nameOrSource, String orderSource) {
        if (orderSource == null || orderSource.isEmpty()) {
            return this;
        }
        String name = validateNameOrSourceExists(nameOrSource);
        views.get(name).setOrderSource(orderSource);
        return this;
    }

    public ViewBuilder setAppearance(String nameOrSource, ViewAppearance appearance) {
        if (appearance == null) {
            return this;
        }
        String name = validateNameOrSourceExists(nameOrSource);
        views.get(name).setAppearance(appearance);
        return this;
    }

    public void applyToAllFields(Format format) {
        for (View view : views.values()) {
            if (view.getViewType() != ViewType.FIELD) {
                continue;
            }
            view.setFormat(format);
        }
    }

    public void applyToAllFields(ViewAppearance appearance) {
        for (View view : views.values()) {
            if (view.getViewType() != ViewType.FIELD) {
                continue;
            }
            view.setAppearance(appearance);
        }
    }

    public ViewBuilder addAction(String nameOrSource, String name) {
        String f = validateNameOrSourceExists(nameOrSource);
        validateNameIsUnique(name);
        View field = views.get(f);
        View action = new View(nextId(), name, null, ViewType.FIELD_ACTION, null, null);
        field.addItem(action.getId());
        views.put(action.getName(), action);
        return this;
    }

    //

    private String validateNameOrSourceExists(String nameOrSource) {
        if (!views.containsKey(nameOrSource)) {
            for (View view : views.values()) {
                if (nameOrSource.equals(view.getSource())) {
                    return view.getName();
                }
            }
            throw new IllegalArgumentException("not found '" + nameOrSource + "' in " + views.keySet());
        }
        return nameOrSource;
    }

    private void validateNameIsUnique(String name) {
        if (views.containsKey(name)) {
            throw new IllegalArgumentException("duplicate name '" + name + "'");
        }
    }

    public List<View> getViews() {
        return new ArrayList<>(views.values());
    }

    public void applyTo(ServiceResponse serviceResponse) {
        for (View view : views.values()) {
            serviceResponse.addView(view);
        }
    }
}
