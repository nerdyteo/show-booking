package com.nerdyteo.show_booking.mode;

import com.nerdyteo.show_booking.util.CommandLineUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;

public abstract class Mode {
    protected abstract void execute(Command command);

    protected abstract String getHelpMessage();

    private void print() {
        final StringBuilder message = new StringBuilder(getHelpMessage());
        message.append("\t\t- Exit\n" +
                "\t\t\t(Return to Mode Selection Window)\n");
        LoggingUtil.print(message.toString());
    }

    public void execute() {
        while (true) {
            print();
            final String raw = CommandLineUtil.getInput();
            if (raw != null) {
                if (raw.equals("exit"))
                    return;
                final Command command = new Command(raw);
                if (!command.hasCommand()) {
                    LoggingUtil.error("Please enter a command.");
                    continue;
                }
                execute(command);
            }
        }
    }
}
