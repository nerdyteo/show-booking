package com.nerdyteo.show_booking.util;

public class ConversionUtil {

    public static Long toLong(final String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception error) {
            return null;
        }
    }
}
