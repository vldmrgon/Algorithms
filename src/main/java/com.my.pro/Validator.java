package com.my.pro;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Map;

public class Validator {

    public static void validateObject(Object rootObject) throws IllegalAccessException {
        checkNullOrSerializable(rootObject);

        Deque<Object> stack = new ArrayDeque<>();
        stack.push(rootObject);

        while (!stack.isEmpty()) {
            Object object = stack.pop();

            if (object instanceof Collection) processCollection((Collection<?>) object, stack);
            else if (object instanceof Map) processMap((Map<?, ?>) object, stack);
            else if (object != null) processFields(object, stack);
        }
    }

    private static void processCollection(Collection<?> collection, Deque<Object> stack) throws IllegalAccessException {
        for (Object item : collection) {
            checkNullOrSerializable(item);
            stack.push(item);
        }
    }

    private static void processMap(Map<?, ?> map, Deque<Object> stack) throws IllegalAccessException {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            checkNullOrSerializable(entry.getKey());
            checkNullOrSerializable(entry.getValue());
            stack.push(entry.getKey());
            stack.push(entry.getValue());
        }
    }

    private static void processFields(Object object, Deque<Object> stack) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            checkNullOrSerializable(fieldValue);

            if (!(fieldValue instanceof String || fieldValue.getClass().isPrimitive())) {
                stack.push(fieldValue);
            }
        }
    }

    private static void checkNullOrSerializable(Object obj) throws IllegalAccessException {
        if (obj == null) {
            throw new NullPointerException("Object is null!");
        }
        if (!(obj instanceof Serializable)) {
            throw new IllegalAccessException("Object of class " + obj.getClass().getName() + " is not serializable");
        }
    }
}