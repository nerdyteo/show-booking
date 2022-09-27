package com.nerdyteo.show_booking.mode.admin.module;

import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.Arrays;

public class SetupShowModule implements AdminModule {
    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 4) {
            LoggingUtil.error("Invalid parameters. Parameters: " + Arrays.toString(parameters));
            return;
        }

        final long showNumber;
        try {
            showNumber = Long.parseLong(parameters[0]);
        } catch (Exception error) {
            LoggingUtil.error("Invalid Show Number Format. Please ensure Show Number numerical.");
            return;
        }

        final int numberOfRows;
        try {
            numberOfRows = Integer.parseInt(parameters[1]);
        } catch (Exception error) {
            LoggingUtil.error("Invalid Number Of Rows Format. Please ensure Number of Rows are numerical.");
            return;
        }

        final int numberOfSeats;
        try {
            numberOfSeats = Integer.parseInt(parameters[2]);
        } catch (Exception error) {
            LoggingUtil.error("Invalid Number Of seats per row Format. Please ensure Number Of seats per row are numerical.");
            return;
        }

        final int cancellationWindowInMinutes;
        try {
            cancellationWindowInMinutes = Integer.parseInt(parameters[3]);
        } catch (Exception error) {
            LoggingUtil.error("Invalid Cancellation window in minutes Format. Please ensure Cancellation window in minutes are numerical.");
            return;
        }

        Cinema.getInstance().setup(showNumber, numberOfRows, numberOfSeats, cancellationWindowInMinutes);
    }
}
