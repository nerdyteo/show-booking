package com.nerdyteo.show_booking.show;

import com.nerdyteo.show_booking.util.ConversionUtil;

public class ParsedTicketNumber {

    private final String ticketNumber;
    private final String showNumberHash;
    private final String phoneNumberHash;

    public ParsedTicketNumber(String ticketNumber, String showNumberHash, String phoneNumberHash) {
        this.ticketNumber = ticketNumber;
        this.showNumberHash = showNumberHash;
        this.phoneNumberHash = phoneNumberHash;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getShowNumberHash() {
        return showNumberHash;
    }

    public boolean isValidPhoneNumber(final String phoneNumber) {
        final String givenHash = ConversionUtil.sha1(phoneNumber);
        return this.phoneNumberHash.equals(givenHash);
    }

}
