package com.philips.dmis.swt.ui.toolkit.reflect;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DtoUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String getDefault(Class<?> dto) throws DtoException {
        return getDefault(dto, false);
    }

    public static String getDefault(Class<?> dto, boolean asArray) throws DtoException {
        Constructor<?> ctor = null;
        try {
            ctor = dto.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new DtoException(String.format("default constructor in DTO %s", dto.getName()), e);
        }
        Object obj = null;
        try {
            obj = ctor.newInstance();
        } catch (Exception e) {
            throw new DtoException(String.format("failed to instantiate DTO %s", dto.getName()), e);
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(asArray ? new Object[]{obj} : obj);
        } catch (JsonProcessingException e) {
            throw new DtoException(String.format("failed to serialize DTO %s", dto.getName()), e);
        }
    }

    public static String valueOf(Object dto) {
        try {
            return OBJECT_MAPPER.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new DtoException(String.format("failed to serialize DTO %s", dto.getClass().getName()), e);
        }
    }

    public static List<Member> getProperties(Class<?> dto) {
        List<Member> properties = new ArrayList<>();
        if (dto == null || dto == Object.class) {
            return properties;
        }
        Field[] fields = dto.getFields();
        for (Field field : fields) {
            properties.add(field);
        }
        Method[] methods = dto.getMethods();
        for (Method method : methods) {
            if (method.getDeclaringClass() == Object.class) {
                continue;
            }
            if (Modifier.isStatic(method.getModifiers())) {
                continue;
            }
            if (method.getReturnType() == void.class || method.getReturnType() == Void.class) {
                continue;
            }
            properties.add(method);
        }

        return sort(dto, properties);
    }

    public static List<Member> sort(Class<?> dto, List<Member> properties) {
        if (dto.isAnnotationPresent(JsonPropertyOrder.class)) {
            JsonPropertyOrder jsonPropertyOrder = dto.getAnnotation(JsonPropertyOrder.class);
            if (jsonPropertyOrder.alphabetic()) {
                return properties.stream().sorted().toList();
            }
            if (jsonPropertyOrder.value() != null) {
                List<String> order = new ArrayList<>(Arrays.asList(jsonPropertyOrder.value()));
                Member[] ordered = new Member[properties.size()];
                List<Member> remaining = new ArrayList<>();
                for (Member member : properties) {
                    String jsonKey = getJsonKey(member);
                    int i = order.indexOf(jsonKey);
                    if (i == -1) {
                        remaining.add(member);
                    } else {
                        ordered[i] = member;
                    }
                }
                List<Member> orderedList = Arrays.asList(ordered).stream().filter(m -> m != null).toList();
                if (!remaining.isEmpty()) {
                    orderedList.addAll(remaining);
                }
                return orderedList;
            }
        }
        return properties;
    }

    public static String getJsonKey(Member member) {
        return fixCamelCasing(getNameWithoutPrefixes(member));
    }

    public static String getNameWithoutPrefixes(Member member) {
        String name = member.getName();
        Class<?> returnType;
        if (member instanceof Field) {
            returnType = ((Field) member).getType();
        } else if (member instanceof Method) {
            returnType = ((Method) member).getReturnType();
        } else {
            throw new IllegalArgumentException("member not field or method");
        }
        if ((returnType == boolean.class || returnType == Boolean.class) && name.startsWith("is")) {
            return name.substring(2);
        }
        if ((returnType == boolean.class || returnType == Boolean.class) && name.startsWith("has")) {
            return name.substring(3);
        }
        if (name.startsWith("get") || name.startsWith("set")) {
            return name.substring(3);
        }
        return name;
    }

    public static String fixCamelCasing(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    public static String fixProperCasing(String name) {
        String n = name.substring(0, 1).toLowerCase() + name.substring(1);
        StringBuffer s = new StringBuffer();
        int lastWordBoundary = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (i == 0) {
                c = Character.toUpperCase(c);
            }
            if (i > 0 && Character.isUpperCase(c)) {
                if (i != lastWordBoundary + 1) {
                    s.append(" ");
                }
                lastWordBoundary = i;
            }
            s.append(c);
        }
        return s.toString();
    }

    public static String getProperName(Member member) {
        return fixProperCasing(getNameWithoutPrefixes(member));
    }
}
