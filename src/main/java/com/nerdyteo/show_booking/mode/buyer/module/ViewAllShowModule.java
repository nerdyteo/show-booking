package com.nerdyteo.show_booking.mode.buyer.module;


import com.nerdyteo.show_booking.mode.buyer.BuyerMode;
import com.nerdyteo.show_booking.show.Cinema;

public class ViewAllShowModule implements BuyerModule {

    private static volatile ViewAllShowModule instance;


    @Override
    public void execute(String[] parameters) {
        Cinema.getInstance().available();
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
