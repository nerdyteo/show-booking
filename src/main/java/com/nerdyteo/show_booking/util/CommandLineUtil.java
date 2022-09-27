package com.nerdyteo.show_booking.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineUtil {
    private final static BufferedReader systemInReader = new BufferedReader(new InputStreamReader(System.in));

    private final static int DEFAULT_MAX_CHARACTERS_PER_LINE = 500;

    public static String getInput() {
        return getInput(DEFAULT_MAX_CHARACTERS_PER_LINE);
    }

    public static String getInput(int maxCharactersPerLine) {
        try {
            final StringBuilder inputBuilder = new StringBuilder();
            int buffer;
            LoggingUtil.print("Please enter your input:");
            while ((buffer = systemInReader.read()) != '\n') {
                inputBuilder.append((char) buffer);
                if (inputBuilder.length() > maxCharactersPerLine) {
                    LoggingUtil.error("Number of input characters had exceeded security threshold. Security Threshold: " + maxCharactersPerLine);
                    return null;
                }
            }
            return inputBuilder.toString().trim();
        } catch (IOException e) {
            LoggingUtil.error("Failed to acquire input from user. Message: " + e.getMessage());
            return null;
        }
    }
}
