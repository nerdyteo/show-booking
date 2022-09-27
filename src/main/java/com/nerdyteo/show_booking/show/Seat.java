package com.nerdyteo.show_booking.show;

import com.nerdyteo.show_booking.show.Ticket;

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

    public String getSeatNumber() {
        return seatNumber;
    }
}
