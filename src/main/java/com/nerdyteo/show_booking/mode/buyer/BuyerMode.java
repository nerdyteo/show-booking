package com.nerdyteo.show_booking.mode.buyer;

import com.nerdyteo.show_booking.mode.Mode;

public class BuyerMode extends Mode {

    private static volatile BuyerMode instance;

    @Override
    protected void execute(String command) {
    }

    @Override
    protected String getHelpMessage() {
        return "";
    }

    public static BuyerMode getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new BuyerMode();
                }
            }
        }
        return instance;
    }
}
