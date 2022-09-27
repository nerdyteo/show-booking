package com.nerdyteo.show_booking.mode;

import java.util.Arrays;

public class Command {
    private final String command;
    private final String[] parameters;

    public Command(final String raw) {
        final String[] parsed = raw.split(" ");
        if (parsed.length > 0) {
            this.command = parsed[0].toLowerCase();
            if (parsed.length > 1)
                this.parameters = Arrays.copyOfRange(parsed, 1, parsed.length);
            else
                this.parameters = new String[0];
        } else {
            command = null;
            parameters = new String[0];
        }
    }

    public boolean hasCommand() {
        return this.command != null;
    }

    public String getCommand() {
        return command;
    }

    public String[] getParameters() {
        return parameters;
    }
}
