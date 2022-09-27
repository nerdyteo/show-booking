package com.nerdyteo.show_booking.mode.admin.module;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.util.ConversionUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.Arrays;

public class ViewShowModule implements AdminModule {
    private static volatile ViewShowModule instance;

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 1) {
            LoggingUtil.error("Invalid Parameters Detected. Parameters: " + Arrays.toString(parameters));
            return;
        }

        final Long showNumber = ConversionUtil.toLong(parameters[0]);
        if (showNumber == null) {
            LoggingUtil.error("Invalid Show Number");
            return;
        }

        Cinema.getInstance().view(showNumber);
    }

    public static ViewShowModule getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new ViewShowModule();
                }
            }
        }
        return instance;
    }
}
