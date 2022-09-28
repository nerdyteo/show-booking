package com.nerdyteo.show_booking.show;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.util.LoggingUtil;
import com.nerdyteo.show_booking.util.TicketUtil;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cinema {
    private static volatile Cinema instance;
    private final static int MAX_ROWS = 26;
    private final static int MAX_SEATS = 10;
    private final static int MAX_CANCELLATION_WINDOWS_MINUTES = 5;

    private final Predicate<Integer> rowValidation;
    private final Predicate<Integer> seatsValidation;
    private final Predicate<Integer> cancellationWindowsValidation;

    {
        rowValidation = row -> MAX_ROWS >= row && row >= 1;
        seatsValidation = seats -> MAX_SEATS >= seats && seats >= 1;
        cancellationWindowsValidation = window -> MAX_CANCELLATION_WINDOWS_MINUTES >= window && window >= 1;
    }

    private final HashMap<Long, ShowInformation> showMapping;

    private Cinema() {
        this.showMapping = new HashMap<>();
    }

    public void setup(final long showNumber, final int numberOfRows, final int numberOfSeats, final int cancellationWindowsMinutes) {
        // Validation
        if (!rowValidation.test(numberOfRows)) {
            LoggingUtil.error("Number of rows must be between 1 and " + MAX_ROWS + " (inclusive).");
            return;
        }

        if (!seatsValidation.test(numberOfSeats)) {
            LoggingUtil.error("Number of seats must be between 1 and " + MAX_SEATS + " (inclusive).");
            return;
        }

        if (!cancellationWindowsValidation.test(cancellationWindowsMinutes)) {
            LoggingUtil.error("Cancellation Window (Minutes) must be between 1 and " + MAX_CANCELLATION_WINDOWS_MINUTES + " (inclusive).");
            return;
        }

        if (this.showMapping.containsKey(showNumber)) {
            LoggingUtil.error("Show with Show number [" + showNumber + "] had been created.");
            return;
        }

        this.showMapping.put(showNumber, new ShowInformation(showNumber, numberOfRows, numberOfSeats, cancellationWindowsMinutes));
        LoggingUtil.info("Successfully added Show #" + showNumber + " that has " + numberOfRows + " rows with " + numberOfSeats + " seats and a cancellation window of " + cancellationWindowsMinutes + " minutes.");
    }

    public List<Seat> getSeats(final long showNumber) {
        if (!this.showMapping.containsKey(showNumber)) {
            LoggingUtil.error("Show #" + showNumber + " does not exist.");
            return null;
        }

        return this.showMapping.get(showNumber).getSeats();
    }

    public Set<Long> available() {
        return this.showMapping.keySet();
    }

    public List<Seat> available(final long showNumber) {
        if (!this.showMapping.containsKey(showNumber)) {
            LoggingUtil.error("Show #" + showNumber + " does not exist.");
            return null;
        }
        return this.showMapping.get(showNumber).available();
    }

    public String book(final long showNumber, final String phoneNumber, final List<String> seats) {
        if (!this.showMapping.containsKey(showNumber)) {
            LoggingUtil.error("Show #" + showNumber + " does not exist.");
            return null;
        }
        final DateTime now = DateTime.now();

        final ShowInformation showInformation = this.showMapping.get(showNumber);
        final List<String> validatedSeats = seats.stream()
                .filter(showInformation::hasAvailableSeat)
                .collect(Collectors.toList());

        if (validatedSeats.isEmpty()) {
            LoggingUtil.error("No valid seats had been entered");
            return null;
        }

        final String ticketNumber = TicketUtil.convert(showNumber, phoneNumber, seats);
        try {
            showInformation.book(ticketNumber, phoneNumber, seats, now);
            return ticketNumber;
        } catch (Exception error) {
            LoggingUtil.error("Failed to book ticket. Message: " + error.getMessage());
            return null;
        }
    }

    public static Cinema getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new Cinema();
                }
            }
        }
        return instance;
    }
}
