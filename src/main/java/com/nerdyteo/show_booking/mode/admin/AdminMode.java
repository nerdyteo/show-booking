package com.nerdyteo.show_booking.mode.admin;

import com.nerdyteo.show_booking.mode.Mode;

public class AdminMode extends Mode {

    private static volatile AdminMode instance;

    private AdminMode() {
    }

    @Override
    protected boolean execute(String command) {
        return false;
    }

    @Override
    protected void printHelp() {

    }


    public static AdminMode getInstance() {
        if (instance == null) {
            synchronized (AdminMode.class) {
                if (instance == null) {
                    instance = new AdminMode();
                }
            }
        }
        return instance;
    }
}
