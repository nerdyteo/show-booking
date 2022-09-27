package com.nerdyteo.show_booking.mode.admin.module;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.util.ConversionUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.Arrays;

public class SetupShowModule implements AdminModule {
    private static volatile SetupShowModule instance;

    private SetupShowModule() {
    }

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 4) {
            LoggingUtil.error("Invalid parameters. Parameters: " + Arrays.toString(parameters));
            return;
        }

        final Long showNumber = ConversionUtil.toLong(parameters[0]);
        if (showNumber == null) {
            LoggingUtil.error("Invalid Show Number");
            return;
        }


        final Integer numberOfRows = ConversionUtil.toInt(parameters[1]);
        if (numberOfRows == null) {
            LoggingUtil.error("Invalid Number of Rows");
            return;
        }


        final Integer numberOfSeats = ConversionUtil.toInt(parameters[2]);
        if (numberOfSeats == null) {
            LoggingUtil.error("Invalid Number of Seats");
            return;
        }

        final Integer cancellationWindowInMinutes = ConversionUtil.toInt(parameters[3]);
        if (cancellationWindowInMinutes == null) {
            LoggingUtil.error("Invalid Cancellation window in minutes");
            return;
        }

        Cinema.getInstance().setup(showNumber, numberOfRows, numberOfSeats, cancellationWindowInMinutes);
    }

    public static SetupShowModule getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new SetupShowModule();
                }
            }
        }
        return instance;
    }
}
