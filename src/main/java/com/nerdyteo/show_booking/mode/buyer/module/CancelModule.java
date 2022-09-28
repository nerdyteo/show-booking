package com.nerdyteo.show_booking.mode.buyer.module;

import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.show.ParsedTicketNumber;
import com.nerdyteo.show_booking.util.ConversionUtil;
import com.nerdyteo.show_booking.util.LoggingUtil;
import com.nerdyteo.show_booking.util.TicketUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class CancelModule implements BuyerModule {
    private static volatile CancelModule instance;

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 2) {
            LoggingUtil.error("Invalid Parameters Detected. Parameters: " + Arrays.toString(parameters));
            return;
        }

        final ParsedTicketNumber ticketNumber = TicketUtil.convert(parameters[0]);
        if (ticketNumber == null) {
            LoggingUtil.error("Please enter ticket number");
            return;
        }

        final String phone = parameters[1];
        if (StringUtils.isBlank(phone)) {
            LoggingUtil.error("Please enter phone number");
            return;
        }

        if (!ticketNumber.isValidPhoneNumber(phone)) {
            LoggingUtil.error("Phone number does not match ticket number");
            return;
        }

        final List<String> cancelledSeats = Cinema.getInstance().cancel(ticketNumber.getTicketNumber(), ticketNumber.getShowNumberHash());
        if (cancelledSeats != null && cancelledSeats.size() > 0)
            LoggingUtil.info("Cancellation of Ticket #" + ticketNumber.getTicketNumber() + " is successful. Cancelled Seats: " + cancelledSeats);
    }

    public static CancelModule getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new CancelModule();
                }
            }
        }
        return instance;
    }
}
