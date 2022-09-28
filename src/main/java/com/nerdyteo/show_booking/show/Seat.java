package com.nerdyteo.show_booking.show;

import com.nerdyteo.show_booking.show.Ticket;
import com.nerdyteo.show_booking.util.LoggingUtil;
import org.joda.time.DateTime;

public class Seat {

    private final String seatNumber;
    private Ticket booking;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
        this.booking = null;
    }

    public boolean isBooked() {
        return this.booking != null;
    }

    public void book(final String ticketNumber, final String phoneNumber) {
        this.booking = new Ticket(ticketNumber, phoneNumber);
    }

    public boolean cancel(final String ticketNumber) {
        if (this.booking != null && booking.getTicketNumber().equals(ticketNumber)) {
            this.booking = null;
            return true;
        }
        return false;
    }

    public void view() {
        if (isBooked()) {
            LoggingUtil.println("\t* ", this.seatNumber, " [BOOKED]");
            LoggingUtil.println("\t\t\tTicket Information:");
            LoggingUtil.println("\t\t\t\tTicket #: " + this.booking.getTicketNumber());
            LoggingUtil.println("\t\t\t\tPhone Number #:" + this.booking.getPhoneNumber());
        } else
            LoggingUtil.println("\t* ", this.seatNumber, " [NOT BOOKED]");
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
