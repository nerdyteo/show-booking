package com.nerdyteo.show_booking.mode.admin;

import com.nerdyteo.show_booking.mode.Mode;
import com.nerdyteo.show_booking.mode.admin.module.AdminModule;
import com.nerdyteo.show_booking.mode.admin.module.SetupShowModule;
import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.Arrays;

public class AdminMode extends Mode {

    private static volatile AdminMode instance;

    private AdminMode() {
    }

    @Override
    protected boolean execute(final String rawCommand) {
        if (rawCommand == null)
            return true;

        final String[] parsedCommands = rawCommand.split(" ");

        if (parsedCommands.length < 1) {
            LoggingUtil.error("Please enter a command.");
            return true;
        }

        final String command = parsedCommands[0].toLowerCase();
        final AdminModule module;
        switch (command) {
            case "setup":
                module = new SetupShowModule();
                break;
            case "view":
                module = null;
                break;
            case "exit":
                return false;
            default:
                LoggingUtil.error("Invalid command.");
                module = null;
                break;
        }
        if (module != null)
            module.execute(Arrays.copyOfRange(parsedCommands, 1, parsedCommands.length));
        return true;
    }

    @Override
    protected void printHelp() {
        LoggingUtil.println("[ADMIN MODE]\n" +
                "\tAvailable Commands:\n" +
                "\t\t- Setup <Show Number> <Number of Rows> <Number of seats per row>  <Cancellation window in minutes>\n" +
                "\t\t\t(To setup the number of seats per show)\n" +
                "\t\t- View <Show Number>\n" +
                "\t\t\t(To display Show Number, Ticket#, Buyer Phone#, Seat Numbers allocated)\n" +
                "\t\t- Exit\n" +
                "\t\t\t(To exit Admin Mode and return to Mode Selection Window)");
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
