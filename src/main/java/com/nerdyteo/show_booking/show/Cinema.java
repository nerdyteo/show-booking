package com.nerdyteo.show_booking.show;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.HashMap;
import java.util.function.Predicate;

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
