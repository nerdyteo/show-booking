package com.nerdyteo.show_booking.mode.admin;

import com.nerdyteo.show_booking.mode.Command;
import com.nerdyteo.show_booking.mode.Mode;
import com.nerdyteo.show_booking.mode.admin.module.AdminModule;
import com.nerdyteo.show_booking.mode.admin.module.SetupShowModule;
import com.nerdyteo.show_booking.mode.admin.module.ViewShowModule;
import com.nerdyteo.show_booking.util.LoggingUtil;

public class AdminMode extends Mode {

    private static volatile AdminMode instance;

    private AdminMode() {
    }

    @Override
    protected void execute(final Command command) {
        final AdminModule module;
        switch (command.getCommand()) {
            case "setup":
                module = SetupShowModule.getInstance();
                break;
            case "view":
                module = ViewShowModule.getInstance();
                break;
            default:
                LoggingUtil.error("Invalid command.");
                return;
        }
        module.execute(command.getParameters());
    }

    @Override
    protected String getHelpMessage() {
        return "[Admin Mode]\n" +
                "\tAvailable Commands:\n" +
                "\t\t- Setup <Show Number> <Number of Rows> <Number of seats per row>  <Cancellation window in minutes>\n" +
                "\t\t\t(To setup the number of seats per show)\n" +
                "\t\t- View <Show Number>\n" +
                "\t\t\t(To display Show Number, Ticket#, Buyer Phone#, Seat Numbers allocated)\n";
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
