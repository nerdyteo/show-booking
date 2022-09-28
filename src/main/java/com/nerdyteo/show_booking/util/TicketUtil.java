package com.nerdyteo.show_booking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.StringJoiner;

public class TicketUtil {

    private static final String DELIMITER = "-";

    public static String convert(final Long showNumber, final String phoneNumber, final List<String> validatedSeats) {
        final StringJoiner ticketNumberJoiner = new StringJoiner(DELIMITER);
        ticketNumberJoiner.add(String.valueOf(showNumber.hashCode()));
        ticketNumberJoiner.add(ConversionUtil.sha1(phoneNumber));
        ticketNumberJoiner.add(String.valueOf(validatedSeats.stream().reduce("", String::concat).hashCode()));
        return ticketNumberJoiner.toString();
    }
}
