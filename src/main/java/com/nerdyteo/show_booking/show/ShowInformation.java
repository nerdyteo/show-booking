package com.nerdyteo.show_booking.show;


import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShowInformation {

    private final long number;
    private final int numberOfSeats;
    private final int cancellationWindowsMinutes;
    private final Map<String, Seat> seatsMap;

    public ShowInformation(long number, int numberOfRows, int numberOfSeats, int cancellationWindowMinutes) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.cancellationWindowsMinutes = cancellationWindowMinutes;

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

    public void viewAll() {
        LoggingUtil.info("Viewing all seats information for Show #" + this.number);
        sortedSeatKeys()
                .map(seatsMap::get)
                .forEachOrdered(Seat::view);
        LoggingUtil.info("Successfully displayed all seats information for Show #" + this.number);
    }

    public void available() {
        LoggingUtil.info("Available seats for Show #" + this.number + ":");
        sortedSeatKeys()
                .map(seatsMap::get)
                .filter(seat -> !seat.isBooked())
                .forEachOrdered(seat -> LoggingUtil.println("* ", seat.getSeatNumber()));
    }

    public boolean hasSeat(String seatNumber) {
        return this.seatsMap.containsKey(seatNumber);
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


