package com.nerdyteo.show_booking.util;

import com.nerdyteo.show_booking.show.ParsedTicketNumber;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.StringJoiner;

public class TicketUtil {
    private static final String DELIMITER = "_";

    public static String convert(final Long showNumber, final String phoneNumber, final List<String> validatedSeats) {
        final StringJoiner ticketNumberJoiner = new StringJoiner(DELIMITER);
        ticketNumberJoiner.add(String.valueOf(showNumber.hashCode()));
        ticketNumberJoiner.add(ConversionUtil.sha1(phoneNumber));
        ticketNumberJoiner.add(String.valueOf(validatedSeats.stream().reduce("", String::concat).hashCode()));
        return ticketNumberJoiner.toString();
    }

    public static ParsedTicketNumber convert(final String ticketNumber) {
        if (StringUtils.isBlank(ticketNumber))
            return null;
        final String[] parsed = ticketNumber.split(DELIMITER);
        if (parsed.length != 3)
            return null;

        return new ParsedTicketNumber(ticketNumber, parsed[0], parsed[1]);
    }
}
