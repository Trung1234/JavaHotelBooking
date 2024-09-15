package com.example.hotel.common;

import java.util.Objects;

public class StringUtils {
    public static boolean isNullorBlank(String input) {
        return Objects.isNull(input) ||  input.isBlank();
    }
}
