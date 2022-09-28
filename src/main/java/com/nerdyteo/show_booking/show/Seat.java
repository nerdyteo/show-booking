package com.nerdyteo.show_booking.show;

import com.nerdyteo.show_booking.show.Ticket;
import com.nerdyteo.show_booking.util.LoggingUtil;

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
