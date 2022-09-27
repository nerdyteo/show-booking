package com.nerdyteo.show_booking.mode;

import com.nerdyteo.show_booking.util.CommandLineUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;

public abstract class Mode {
    protected abstract void execute(String command);

    protected abstract String getHelpMessage();

    private void print() {
        final StringBuilder message = new StringBuilder(getHelpMessage());
        message.append("\t\t- Exit\n" +
                "\t\t\t(To exit Admin Mode and return to Mode Selection Window)\n");
        LoggingUtil.print(message.toString());
    }

    public void execute() {
        while (true) {
            print();
            final String command = CommandLineUtil.getInput();
            if (command != null) {
                if (command.equals("exit"))
                    return;
                execute(command);
            }
        }
    }
}
