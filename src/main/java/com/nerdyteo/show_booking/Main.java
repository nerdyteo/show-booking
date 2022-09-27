package com.nerdyteo.show_booking;

import com.nerdyteo.show_booking.mode.admin.AdminMode;
import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.util.CommandLineUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;

public class Main {

    public static void main(String[] args) {
        boolean status = true;
        while (status) {
            printHelp();
            final String input = CommandLineUtil.getInput();
            if (input != null) {
                switch (input) {
                    case "1":
                        LoggingUtil.info("Entering Admin Mode");
                        AdminMode.getInstance().execute();
                        break;
                    case "2":
                        LoggingUtil.info("Entering Buyer Mode");
                        BuyerMode.getInstance().execute();
                        break;
                    case "3":
                        LoggingUtil.info("Exiting Show Booking Program");
                        status = false;
                        break;
                }
            }
        }
    }

    private static void printHelp() {
        LoggingUtil.println("[Mode Selection]\n" +
                "\t\t 1. Admin Mode\n" +
                "\t\t 2. Buyer Mode\n" +
                "\t\t 3. Exit Program\n");
    }
}
