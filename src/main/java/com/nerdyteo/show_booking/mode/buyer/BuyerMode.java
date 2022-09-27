package com.nerdyteo.show_booking.mode.buyer;

import com.nerdyteo.show_booking.mode.Command;
import com.nerdyteo.show_booking.mode.Mode;
import com.nerdyteo.show_booking.mode.buyer.module.BuyerModule;
import com.nerdyteo.show_booking.mode.buyer.module.ViewAllShowModule;
import com.nerdyteo.show_booking.util.LoggingUtil;

public class BuyerMode extends Mode {

    private static volatile BuyerMode instance;

    @Override
    protected void execute(Command command) {
        final BuyerModule module;
        switch (command.getCommand()) {
            case "view":
                module = ViewAllShowModule.getInstance();
                break;
            default:
                LoggingUtil.error("Invalid command.");
                return;
        }
        module.execute(command.getParameters());
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
                "\t\t\t(To cancel a ticket.)\n";
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
