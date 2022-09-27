package com.nerdyteo.show_booking.mode.admin.module;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;
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

        final long showNumber;
        try {
            showNumber = Long.parseLong(parameters[0]);
        } catch (Exception error) {
            LoggingUtil.error("Invalid Show Number Format. Please ensure Show Number numerical.");
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
