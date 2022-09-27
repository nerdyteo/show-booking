package com.nerdyteo.show_booking.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class LoggingUtil {
    private final static String MESSAGE_FORMAT;

    static {
        MESSAGE_FORMAT = "[%s] %s";
    }

    public static void print(String... messages) {
        System.out.print(concat(messages));
    }

    public static void println(String... messages) {
        System.out.println(concat(messages));
    }

    public static void info(String... messages) {
        System.out.println(String.format(MESSAGE_FORMAT, "INFO", concat(messages)));
    }

    public static void warn(String... messages) {
        System.out.println(String.format(MESSAGE_FORMAT, "WARN", concat(messages)));
    }

    public static void error(String... messages) {
        System.out.println(String.format(MESSAGE_FORMAT, "ERROR", concat(messages)));
    }

    private static String concat(String[] messages) {
        Objects.requireNonNull(messages, "Messages cannot be NULL");
        if (messages.length < 1)
            throw new IllegalArgumentException("Messages cannot be empty");

        if (messages.length == 1)
            return messages[0];

        final StringJoiner joiner = new StringJoiner(" ");
        Arrays.stream(messages)
                .forEachOrdered(joiner::add);
        return joiner.toString();
    }
}
