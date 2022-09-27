package com.nerdyteo.show_booking.mode;

import com.nerdyteo.show_booking.util.CommandLineUtil;

public abstract class Mode {
    protected abstract boolean execute(String command);

    protected abstract void printHelp();

    public void execute() {
        boolean status = true;
        while (status) {
            printHelp();
            final String command = CommandLineUtil.getInput();
            status = execute(command);
        }
    }
}
