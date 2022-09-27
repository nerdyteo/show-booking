package com.nerdyteo.show_booking.show;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Ticket {
    public static final String TICKET_NUMBER_DELIMITER = "-";
    private final String ticketNumber;
    private final String phoneNumber;
    private final LocalDateTime cancellationWindow;

    public Ticket(final Long showNumber, final String seatNumber, String phoneNumber, final LocalDateTime cancellationWindow) {
        final StringJoiner ticketNumberBuilder = new StringJoiner(TICKET_NUMBER_DELIMITER);
        ticketNumberBuilder.add(Integer.toString(showNumber.hashCode()));
        ticketNumberBuilder.add(seatNumber);
        ticketNumberBuilder.add(Integer.toString(phoneNumber.hashCode()));
        this.ticketNumber = ticketNumberBuilder.toString();
        this.phoneNumber = phoneNumber;
        this.cancellationWindow = cancellationWindow;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public boolean isCancellable() {
        final LocalDateTime now = LocalDateTime.now();
        return now.isBefore(this.cancellationWindow);
    }
}
