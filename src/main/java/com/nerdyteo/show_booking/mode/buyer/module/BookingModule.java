package com.nerdyteo.show_booking.mode.buyer.module;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.util.ConversionUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookingModule implements BuyerModule {
    private static volatile BookingModule instance;

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 3) {
            LoggingUtil.error("Invalid Parameters Detected. Parameters: " + Arrays.toString(parameters));
            return;
        }

        final Long showNumber = ConversionUtil.toLong(parameters[0]);
        if (showNumber == null) {
            LoggingUtil.error("Invalid Show #.");
            return;
        }

        final String phone = parameters[1];
        if (StringUtils.isBlank(phone)) {
            LoggingUtil.error("Please enter phone number");
            return;
        }

        final List<String> seatNumbers = ConversionUtil.toList(parameters[2], ",", value -> value)
                .stream()
                .filter(value -> value.matches("\\w\\d+"))
                .collect(Collectors.toList());

        if (seatNumbers.isEmpty()) {
            LoggingUtil.error("Please enter at least 1 valid seat numbers");
            return;
        }

        final String ticketNumber = Cinema.getInstance().book(showNumber, phone, seatNumbers);
        if (ticketNumber != null)
            LoggingUtil.info("Successfully booked " + seatNumbers + " for Show #" + showNumber + ".\n\t\tTicket # is " + ticketNumber);
    }

    public static BookingModule getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new BookingModule();
                }
            }
        }
        return instance;
    }
}
