package com.nerdyteo.show_booking.show;


import java.util.*;
import java.util.stream.IntStream;

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


    public boolean hasSeat(String seatNumber) {
        return this.seatsMap.containsKey(seatNumber);
    }
}


