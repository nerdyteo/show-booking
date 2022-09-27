package com.nerdyteo.show_booking.mode;

public abstract class Mode {
    protected abstract boolean commands();

    protected abstract void printHelp();

    public void execute() {
        boolean status = true;
        while (status) {
            printHelp();
            status = commands();

        }
    }
}
