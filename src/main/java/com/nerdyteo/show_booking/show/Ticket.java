package com.nerdyteo.show_booking.show;

import org.joda.time.DateTime;


public class Ticket {
    private final String ticketNumber;
    private final String phoneNumber;
    private final DateTime cancellationWindow;

    public Ticket(final String ticketNumber, final String phoneNumber, final DateTime cancellationWindow) {
        this.ticketNumber = ticketNumber;
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
        final DateTime now = DateTime.now();
        return now.isBefore(this.cancellationWindow);
    }
}
