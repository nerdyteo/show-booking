package com.nerdyteo.show_booking.mode.buyer;

import com.nerdyteo.show_booking.mode.Command;
import com.nerdyteo.show_booking.mode.Mode;

public class BuyerMode extends Mode {

    private static volatile BuyerMode instance;

    @Override
    protected void execute(Command command) {
    }

    @Override
    protected String getHelpMessage() {
        return "[Buyer Mode]\n" +
                "\tAvailable Commands:\n" +
                "\t\t- View\n" +
                "\t\t\t(To list all available shows)\n" +
                "\t\t- Availability <Show Number>\n" +
                "\t\t\t(To list all available seat numbers for a show)\n" +
                "\t\t- Book <Show Number> <Phone#> <Comma separated list of seats>\n" +
                "\t\t\t(To book a seat in a show. Ticket # will be displayed if successful)\n" +
                "\t\t- Cancel <Ticket#>  <Phone#>\n" +
                "\t\t\t(To cancel a ticket.)\n" +
                "\t\t- Exit\n" +
                "\t\t\t(To exit Admin Mode and return to Mode Selection Window)";
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
