package com.nerdyteo.show_booking.mode.buyer.module;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.util.ConversionUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.Arrays;

public class AvailableSeatsModule implements BuyerModule {

    private static volatile AvailableSeatsModule instance;

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 1) {
            LoggingUtil.error("Invalid Parameters Detected. Parameters: " + Arrays.toString(parameters));
            return;
        }

        final Long showNumber = ConversionUtil.toLong(parameters[0]);
        if (showNumber == null) {
            LoggingUtil.error("Invalid Show #.");
            return;
        }

        Cinema.getInstance().available(showNumber);
    }

    public static AvailableSeatsModule getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new AvailableSeatsModule();
                }
            }
        }
        return instance;
    }
}
