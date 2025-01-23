package com.mocicarazvan.dwoltp.utils;

import com.mocicarazvan.dwoltp.enums.EnumGetValue;

public class ParamsUtils {

    public static boolean isLongPositive(Long id) {
        return id != null && id > 0;
    }

    public static <E extends EnumGetValue> String mapEnumToString(E e) {
        if (e == null) {
            return "";
        }
        return e.getValue();
    }

    public static boolean isStringNotBlank(String s) {
        return s != null && !s.isBlank();
    }
}
