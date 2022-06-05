package com.lcl.myspring.homework.utils;

public class ValueNameEnumUtils {
    private ValueNameEnumUtils() {
    }

    public static <E extends ValueNameEnum> E valueOf(Class<E> enumClass, int value) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }

}
