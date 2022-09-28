package com.nerdyteo.show_booking.show;


import com.nerdyteo.show_booking.util.LoggingUtil;
import org.joda.time.DateTime;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShowInformation {

    private final long number;
    private final int cancellationWindowsMinutes;
    private final Map<String, Seat> seatsMap;
    private final HashMap<String, DateTime> ticketWindowMapping;

    public ShowInformation(long number, int numberOfRows, int numberOfSeats, int cancellationWindowMinutes) {
        this.number = number;
        this.cancellationWindowsMinutes = cancellationWindowMinutes;
        this.ticketWindowMapping = new HashMap<>();

        final int alphabetA = 'A';
        final HashMap<String, Seat> seatMapBuffer = new HashMap<>();
        IntStream.range(0, numberOfRows)
                .mapToObj(value -> (char) (alphabetA + value))
                .forEachOrdered(row -> {
                    final StringBuilder seatNumberBuilder = new StringBuilder();
                    seatNumberBuilder.append(row);
                    IntStream.rangeClosed(1, numberOfSeats)
                            .forEachOrdered(seat -> {
                                seatNumberBuilder.append(seat);
                                final String seatNumber = seatNumberBuilder.toString();
                                seatMapBuffer.put(seatNumber, new Seat(seatNumber));
                                seatNumberBuilder.delete(1, seatNumberBuilder.length());
                            });
                });
        this.seatsMap = Collections.unmodifiableMap(seatMapBuffer);
    }

    public List<Seat> getSeats() {
        return sortedSeatKeys()
                .map(seatsMap::get)
                .collect(Collectors.toList());
    }

    public List<Seat> available() {
        return sortedSeatKeys()
                .map(seatsMap::get)
                .filter(seat -> !seat.isBooked())
                .collect(Collectors.toList());
    }

    public boolean hasSeat(String seatNumber) {
        return this.seatsMap.containsKey(seatNumber);
    }

    public boolean hasAvailableSeat(String seatNumber) {
        final Seat seat = this.seatsMap.get(seatNumber);
        if (seat == null)
            return false;

        return !seat.isBooked();
    }

    public void book(final String ticketNumber, final String phoneNumber, final List<String> seats, final DateTime now) {
        final DateTime cancellableWindow = now.plusMinutes(this.cancellationWindowsMinutes);
        seats.stream()
                .map(seatsMap::get)
                .forEachOrdered(seat -> seat.book(ticketNumber, phoneNumber));
        this.ticketWindowMapping.put(ticketNumber, cancellableWindow);
    }

    public List<String> cancel(final String ticketNumber) {
        if (!this.ticketWindowMapping.containsKey(ticketNumber)) {
            LoggingUtil.info("Ticket cancellation window had expired.");
            return null;
        }

        final DateTime now = DateTime.now();
        if (now.isAfter(this.ticketWindowMapping.get(ticketNumber))) {
            this.ticketWindowMapping.remove(ticketNumber);
            LoggingUtil.info("Ticket cancellation window had expired.");
            return null;
        }

        return this.sortedSeatKeys()
                .map(seatsMap::get)
                .filter(Seat::isBooked)
                .filter(seat -> seat.cancel(ticketNumber))
                .map(Seat::getSeatNumber)
                .collect(Collectors.toList());
    }

    private Stream<String> sortedSeatKeys() {
        return this.seatsMap.keySet()
                .stream()
                .sorted((o1, o2) -> {
                    final char[] first = o1.toCharArray();
                    final char[] second = o2.toCharArray();
                    if (first[0] == second[0] && first.length != second.length)
                        return first.length - second.length;

                    return o1.compareTo(o2);
                });
    }
}


