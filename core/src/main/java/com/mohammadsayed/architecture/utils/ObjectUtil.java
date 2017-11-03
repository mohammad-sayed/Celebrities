package com.mohammadsayed.architecture.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by mohammad on 1/16/17.
 */

public class ObjectUtil {

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        return false;
    }

    public static <K> K createInstanceFromGenricClass(Class<K> genericClass, Class currentClass) {
        ParameterizedType superClass = (ParameterizedType) currentClass.getGenericSuperclass();
        try {
            Class<K> type = (Class<K>) superClass.getActualTypeArguments()[0];
            return type.newInstance();
        } catch (Exception e) {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }


}
