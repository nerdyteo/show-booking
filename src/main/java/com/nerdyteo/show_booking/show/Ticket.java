package com.nerdyteo.show_booking.show;

public class Ticket {
    private final String ticketNumber;
    private final String phoneNumber;

    public Ticket(final String ticketNumber, final String phoneNumber) {
        this.ticketNumber = ticketNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

}
