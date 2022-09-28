package com.nerdyteo.show_booking.mode.buyer.module;


import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;
import com.nerdyteo.show_booking.util.LoggingUtil;

import java.util.List;
import java.util.Set;

public class ViewAllShowModule implements BuyerModule {

    private static volatile ViewAllShowModule instance;


    @Override
    public void execute(String[] parameters) {
        final Set<Long> showsNumbers = Cinema.getInstance().available();
        if (showsNumbers != null) {
            if (showsNumbers.size() > 0) {
                LoggingUtil.info("Displaying Show #:");
                showsNumbers.stream()
                        .forEachOrdered(showNumber -> LoggingUtil.println("* ", String.valueOf(showNumber)));
            } else
                LoggingUtil.println("[No shows are currently available]");
        }
    }

    public static ViewAllShowModule getInstance() {
        if (instance == null) {
            synchronized (BuyerMode.class) {
                if (instance == null) {
                    instance = new ViewAllShowModule();
                }
            }
        }
        return instance;
    }
}
