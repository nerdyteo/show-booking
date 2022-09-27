package com.nerdyteo.show_booking.show;

public class ShowNumber {
    private final long number;
    private final int hash;

    public ShowNumber(Long number) {
        this.number = number;
        this.hash = number.hashCode();
    }

    public long getNumber() {
        return number;
    }

    public int getHash() {
        return this.hash;
    }

    @Override
    public int hashCode() {
        return this.hash;
    }
}
