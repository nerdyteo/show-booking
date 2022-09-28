package com.nerdyteo.show_booking.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConversionUtil {

    private static final MessageDigest sha1;

    static {
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static String sha1(final String value) {
        return convertByteArrayToHexString(sha1.digest(value.getBytes(StandardCharsets.UTF_8)));
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}
