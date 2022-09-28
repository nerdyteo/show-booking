package com.nerdyteo.show_booking.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConversionUtil {

    public static Long toLong(final String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception error) {
            return null;
        }
    }

    public static Integer toInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception error) {
            return null;
        }
    }

    public static <T> List<T> toList(final String value, final String delimiter, final Function<String, T> convert) {
        return Arrays.stream(value.split(delimiter))
                .map(convert)
                .collect(Collectors.toList());
    }
}
